package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.generator.core.filter.SchemaDefinitionFilter;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.handler.ModelHandlerUtils;
import com.weedow.schemaorg.generator.template.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class SchemaModelGeneratorImpl implements SchemaModelGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorImpl.class);

    private static final String JAVA_EXTENSION = ".java";

    private final GeneratorOptions options;
    private final TemplateService templateService;
    private final SchemaDefinitionFilter schemaDefinitionFilter;
    private final Map<String, Type> schemaDefinitions;

    public SchemaModelGeneratorImpl(GeneratorOptions options, TemplateService templateService, SchemaDefinitionFilter schemaDefinitionFilter, Map<String, Type> schemaDefinitions) {
        this.options = options;
        this.templateService = templateService;
        this.schemaDefinitionFilter = schemaDefinitionFilter;
        this.schemaDefinitions = schemaDefinitions;
    }

    @Override
    public void generate() {
        final File modelFolder = options.getModelFolder();
        final File modelImplFolder = options.getModelImplFolder();
        final File dataTypeFolder = options.getDataTypeFolder();

        if (!isFolderExists(modelFolder)) {
            LOG.error("Model directory does not exist and could not be created: {}", modelFolder);
            return;
        }
        if (!isFolderExists(modelImplFolder)) {
            LOG.error("Model Implementation directory does not exist and could not be created: {}", modelFolder);
            return;
        }
        if (!isFolderExists(dataTypeFolder)) {
            LOG.error("DataType directory does not exist and could not be created: {}", modelFolder);
            return;
        }

        LOG.info("Generating models...");

        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();
        final String dataTypePackage = options.getDataTypePackage();
        final List<String> models = options.getModels();

        applyTemplate(
                "templates/jsonld_typename",
                new File(modelFolder, "JsonLdTypeName.java"),
                new Context(null, modelPackage, Collections.emptySet())
        );

        applyTemplate(
                "templates/jsonld_node",
                new File(modelFolder, "JsonLdNode.java"),
                new Context(null, modelPackage, Collections.emptySet())
        );

        applyTemplate(
                "templates/jsonld_node_impl",
                new File(modelImplFolder, "JsonLdNodeImpl.java"),
                new Context(null, modelImplPackage, new LinkedHashSet<>(Arrays.asList(
                        SchemaGeneratorUtils.resolveClassName(modelPackage, dataTypePackage, SchemaGeneratorUtils.JSON_LD_NODE),
                        SchemaGeneratorUtils.resolveClassName(modelPackage, dataTypePackage, SchemaGeneratorUtils.JSON_LD_TYPE_NAME)
                )))
        );

        Map<String, Type> filteredSchemaDefinitions = schemaDefinitionFilter.filter(schemaDefinitions, models);
        if (filteredSchemaDefinitions.isEmpty()) {
            LOG.info("No schema model found to generate");
            return;
        }

        Stream<Type> stream;
        if(LOG.isDebugEnabled()) { // verbose mode
            stream = filteredSchemaDefinitions.values().stream();
        } else {
            stream = filteredSchemaDefinitions.values().parallelStream();
        }

        stream.forEach(type -> {
            if (type.getId().equals("schema:DataType")) {
                generateAbstractDataType(dataTypeFolder, dataTypePackage, type);
            } else if (ModelHandlerUtils.isDataType(type.getId())) {
                generateDataType(dataTypeFolder, dataTypePackage, modelPackage, type);
            } else if (ModelHandlerUtils.isSubDataType(type)) {
                generateDataType(dataTypeFolder, dataTypePackage, modelPackage, type);
            } else if (ModelHandlerUtils.isEnumeration(type)) {
                generateEnumerationType(modelFolder, modelImplFolder, modelPackage, modelImplPackage, dataTypePackage, type);
            } else {
                generateType(modelFolder, modelImplFolder, modelPackage, modelImplPackage, dataTypePackage, type);
            }
        });
        LOG.info("Model generation completed.");
    }

    private void generateAbstractDataType(File dataTypeFolder, String dataTypePackage, Type type) {
        applyTemplate(
                "templates/abstract_data_type",
                new File(dataTypeFolder, type.getName() + JAVA_EXTENSION),
                new Context(type, dataTypePackage, Collections.emptySet())
        );
    }

    private void generateDataType(File dataTypeFolder, String dataTypePackage, String modelPackage, Type type) {
        final List<String> additionalImports = Collections.singletonList(SchemaGeneratorUtils.resolveClassName(modelPackage, dataTypePackage, SchemaGeneratorUtils.JSON_LD_TYPE_NAME));
        applyTemplate(
                "templates/data_type",
                new File(dataTypeFolder, type.getName() + JAVA_EXTENSION),
                new Context(type, dataTypePackage, SchemaGeneratorUtils.getImports(modelPackage, dataTypePackage, type, additionalImports))
        );
    }

    private void generateEnumerationType(File modelFolder, File modelImplFolder, String modelPackage, String modelImplPackage, String dataTypePackage, Type type) {
        applyTemplate(
                "templates/type_interface",
                new File(modelFolder, type.getName() + JAVA_EXTENSION),
                new Context(type, modelPackage, SchemaGeneratorUtils.getImports(modelPackage, dataTypePackage, type, Collections.emptyList()))
        );

        applyTemplate(
                "templates/type_enumeration",
                new File(modelImplFolder, type.getName() + "Enum" + JAVA_EXTENSION),
                new Context(type, modelImplPackage, SchemaGeneratorUtils.getAllImports(modelPackage, dataTypePackage, type))
        );
    }

    private void generateType(File modelFolder, File modelImplFolder, String modelPackage, String modelImplPackage, String dataTypePackage, Type type) {
        applyTemplate(
                "templates/type_interface",
                new File(modelFolder, type.getName() + JAVA_EXTENSION),
                new Context(type, modelPackage, SchemaGeneratorUtils.getImports(modelPackage, dataTypePackage, type, Collections.emptyList()))
        );

        applyTemplate(
                "templates/type_implementation",
                new File(modelImplFolder, type.getName() + "Impl" + JAVA_EXTENSION),
                new Context(type, modelImplPackage, SchemaGeneratorUtils.getAllImports(modelPackage, dataTypePackage, type))
        );
    }

    private void applyTemplate(String templateLocation, File outputFile, Context context) {
        try {
            templateService.apply(templateLocation, outputFile, context);
            options.getSuccessHandlers().forEach(successHandler -> successHandler.onSuccess(templateLocation, outputFile, context));
        } catch (IOException e) {
            LOG.warn("Could not write output file {} from template '{}': {}", outputFile, templateLocation, e.getMessage());
            options.getErrorHandlers().forEach(errorHandler -> errorHandler.onError(templateLocation, outputFile, context, e));
        }
    }

    private static boolean isFolderExists(File folder) {
        return folder.exists() || folder.mkdirs();
    }
}
