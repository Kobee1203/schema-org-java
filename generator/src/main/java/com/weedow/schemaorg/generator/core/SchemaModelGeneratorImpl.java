package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.commons.generator.GeneratorConstants;
import com.weedow.schemaorg.commons.model.*;
import com.weedow.schemaorg.generator.core.copy.CopyService;
import com.weedow.schemaorg.generator.core.filter.SchemaDefinitionFilter;
import com.weedow.schemaorg.generator.core.stream.StreamService;
import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import com.weedow.schemaorg.generator.template.TemplateService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class SchemaModelGeneratorImpl implements SchemaModelGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorImpl.class);

    private static final String JAVA_EXTENSION = ".java";

    private final GeneratorOptions options;
    private final TemplateService templateService;
    private final SchemaDefinitionFilter schemaDefinitionFilter;
    private final Map<String, Type> schemaDefinitions;
    private final CopyService copyService;
    private final StreamService streamService;

    public SchemaModelGeneratorImpl(
            GeneratorOptions options,
            TemplateService templateService,
            SchemaDefinitionFilter schemaDefinitionFilter,
            Map<String, Type> schemaDefinitions,
            CopyService copyService,
            StreamService streamService
    ) {
        this.options = options;
        this.templateService = templateService;
        this.schemaDefinitionFilter = schemaDefinitionFilter;
        this.schemaDefinitions = schemaDefinitions;
        this.copyService = copyService;
        this.streamService = streamService;
    }

    @Override
    public void generate() {
        final Path modelFolder = options.getModelFolder();
        final Path modelImplFolder = options.getModelImplFolder();
        final Path dataTypeFolder = options.getDataTypeFolder();

        if (!createFolderIfNotExists(modelFolder)) {
            LOG.error("Model directory does not exist and could not be created: {}", modelFolder);
            return;
        }
        if (!createFolderIfNotExists(modelImplFolder)) {
            LOG.error("Model Implementation directory does not exist and could not be created: {}", modelFolder);
            return;
        }
        if (!createFolderIfNotExists(dataTypeFolder)) {
            LOG.error("DataType directory does not exist and could not be created: {}", modelFolder);
            return;
        }

        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();
        final String dataTypePackage = options.getDataTypePackage();
        final List<String> models = options.getModels();

        copyPropertyFile(options.getOutputFolder(), modelPackage, modelImplPackage, dataTypePackage);

        Map<String, Type> filteredSchemaDefinitions = schemaDefinitionFilter.filter(schemaDefinitions, models);
        if (filteredSchemaDefinitions.isEmpty()) {
            LOG.info("No schema model found to generate");
            return;
        }

        if (options.isCopyCommonModels()) {
            LOG.info("Copying common models...");
            copyJavaFile(JsonLdTypeName.class);
            copyJavaFile(JsonLdFieldTypes.class);
            copyJavaFile(JsonLdSubTypes.class);
            copyJavaFile(JsonLdNode.class);
            copyJavaFile(JsonLdNodeImpl.class);
            copyJavaFile(JsonLdDataType.class);
        }

        LOG.info("Generating models...");

        Stream<Type> stream = streamService.stream(filteredSchemaDefinitions);

        stream.forEach(type -> {
            if(!type.isUsedJavaType()) {
                if (type.getId().equals("schema:DataType")) {
                    generateAbstractDataType(dataTypeFolder, dataTypePackage, type);
                } else if (ModelUtils.isDataType(type.getId())) {
                    generateDataType(dataTypeFolder, dataTypePackage, modelPackage, type);
                } else if (ModelUtils.isSubDataType(type)) {
                    generateDataType(dataTypeFolder, dataTypePackage, modelPackage, type);
                } else if (ModelUtils.isEnumeration(type)) {
                    generateEnumerationType(modelFolder, modelImplFolder, modelPackage, modelImplPackage, dataTypePackage, type);
                } else {
                    generateType(modelFolder, modelImplFolder, modelPackage, modelImplPackage, dataTypePackage, type);
                }
            }
        });
        LOG.info("Model generation completed.");
    }

    private void copyPropertyFile(Path outputFolder, String modelPackage, String modelImplPackage, String dataTypePackage) {
        try (OutputStream outputStream = new FileOutputStream(outputFolder.resolve(GeneratorConstants.SCHEMA_ORG_PROP_FILENAME).toFile())) {
            Properties properties = new Properties();

            properties.put(GeneratorConstants.SCHEMA_ORG_PROP_COMMONS, JsonLdNode.class.getPackageName());
            properties.put(GeneratorConstants.SCHEMA_ORG_PROP_MODEL, modelPackage);
            properties.put(GeneratorConstants.SCHEMA_ORG_PROP_MODEL_IMPL, modelImplPackage);
            properties.put(GeneratorConstants.SCHEMA_ORG_PROP_DATA_TYPE, dataTypePackage);

            properties.store(outputStream, "Schema.org Java properties");
        } catch (IOException e) {
            LOG.warn("Could not write the properties file to directory {}", outputFolder);
        }
    }

    private void copyJavaFile(Class<?> clazz) {
        copyService.copy(clazz, options.resolvePath(clazz.getPackageName()));
    }

    private void generateAbstractDataType(Path dataTypeFolder, String dataTypePackage, Type type) {
        applyTemplate(
                "templates/abstract_data_type",
                dataTypeFolder.resolve(type.getName() + JAVA_EXTENSION),
                new Context(type, dataTypePackage, Collections.emptySet())
        );
    }

    private void generateDataType(Path dataTypeFolder, String dataTypePackage, String modelPackage, Type type) {
        final List<String> additionalImports = Collections.singletonList(JsonLdTypeName.class.getName());
        applyTemplate(
                "templates/data_type",
                dataTypeFolder.resolve(type.getName() + JAVA_EXTENSION),
                new Context(type, dataTypePackage, SchemaGeneratorUtils.getImports(modelPackage, dataTypePackage, type, additionalImports))
        );
    }

    private void generateEnumerationType(Path modelFolder, Path modelImplFolder, String modelPackage, String modelImplPackage, String dataTypePackage, Type type) {
        applyTemplate(
                "templates/type_interface",
                modelFolder.resolve(type.getName() + JAVA_EXTENSION),
                new Context(type, modelPackage, SchemaGeneratorUtils.getImports(modelPackage, dataTypePackage, type, Collections.singletonList(java.util.List.class.getName())))
        );

        Set<String> allImports = SchemaGeneratorUtils.getAllImports(modelPackage, dataTypePackage, type);
        if (!type.getSubTypes().isEmpty()) {
            // Add JsonLdSubTypes as import but not cached in SchemaGeneratorUtils.getAllImports method to prevent to be added for enumerations that extend this current Enumeration
            allImports.add(JsonLdSubTypes.class.getName());
        }
        applyTemplate(
                "templates/type_enumeration",
                modelImplFolder.resolve(type.getName() + "Enum" + JAVA_EXTENSION),
                new Context(type, modelImplPackage, allImports)
        );
    }

    private void generateType(Path modelFolder, Path modelImplFolder, String modelPackage, String modelImplPackage, String dataTypePackage, Type type) {
        applyTemplate(
                "templates/type_interface",
                modelFolder.resolve(type.getName() + JAVA_EXTENSION),
                new Context(type, modelPackage, SchemaGeneratorUtils.getImports(modelPackage, dataTypePackage, type, Collections.singletonList(java.util.List.class.getName())))
        );

        applyTemplate(
                "templates/type_implementation",
                modelImplFolder.resolve(type.getName() + "Impl" + JAVA_EXTENSION),
                new Context(type, modelImplPackage, SchemaGeneratorUtils.getAllImports(modelPackage, dataTypePackage, type))
        );
    }

    private void applyTemplate(String templateLocation, Path outputFile, Context context) {
        try {
            templateService.apply(templateLocation, outputFile, context);
            options.getSuccessHandlers().forEach(successHandler -> successHandler.onSuccess(templateLocation, outputFile, context));
        } catch (IOException e) {
            LOG.warn("Could not write output file {} from template '{}': {}", outputFile, templateLocation, e.getMessage());
            options.getErrorHandlers().forEach(errorHandler -> errorHandler.onError(templateLocation, outputFile, context, e));
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean createFolderIfNotExists(Path folder) {
        try {
            return Files.exists(Files.createDirectories(folder));
        } catch (IOException e) {
            LOG.warn("Could not create directory {}: {}", folder, e.getMessage());
            return false;
        }
    }
}
