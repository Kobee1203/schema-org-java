package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.generator.core.handler.ErrorHandler;
import com.weedow.schemaorg.generator.core.handler.SuccessHandler;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.template.TemplateService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchemaModelGeneratorImplTest {

    @Spy
    private GeneratorOptions options = new GeneratorOptions();

    @Mock
    private TemplateService templateService;

    @Mock
    private Map<String, Type> schemaDefinitions;

    @InjectMocks
    private SchemaModelGeneratorImpl schemaModelGenerator;

    @AfterEach
    public void tearDown() {
        SchemaGeneratorUtils.clearCache();
    }

    @Test
    void generate_without_schema_definitions() throws IOException {
        when(schemaDefinitions.entrySet()).thenReturn(Collections.emptySet());

        schemaModelGenerator.generate();

        verifyCommonTemplates(options, templateService);
    }

    @Test
    void generate_abstract_data_type() throws IOException {
        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:DataType");
        when(type.getName()).thenReturn("DataType");
        when(schemaDefinitions.entrySet()).thenReturn(Set.of(new AbstractMap.SimpleEntry<>("schema:DataType", type)));

        schemaModelGenerator.generate();

        verifyCommonTemplates(options, templateService);
        verify(templateService).apply(
                "templates/abstract_data_type",
                new File(options.getDataTypeFolder(), "DataType.java"),
                new Context(type, options.getDataTypePackage(), Collections.emptySet())
        );
    }

    @Test
    void generate_data_type() throws IOException {
        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:Boolean");
        when(type.getName()).thenReturn("Boolean");
        when(type.getProperties()).thenReturn(Collections.emptySet());
        when(schemaDefinitions.entrySet()).thenReturn(Set.of(new AbstractMap.SimpleEntry<>("schema:Boolean", type)));

        schemaModelGenerator.generate();

        verifyCommonTemplates(options, templateService);
        verify(templateService).apply(
                "templates/data_type",
                new File(options.getDataTypeFolder(), "Boolean.java"),
                new Context(type, options.getDataTypePackage(), Set.of("org.schema.model.JsonLdTypeName"))
        );
    }

    @Test
    void generate_sub_data_type() throws IOException {
        final Type parent = mock(Type.class);
        when(parent.getId()).thenReturn("schema:Text");

        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:XPathType");
        when(type.getName()).thenReturn("XPathType");
        when(type.getParents()).thenReturn(List.of(parent));
        when(type.getProperties()).thenReturn(Collections.emptySet());
        when(schemaDefinitions.entrySet()).thenReturn(Set.of(new AbstractMap.SimpleEntry<>("schema:XPathType", type)));

        schemaModelGenerator.generate();

        verifyCommonTemplates(options, templateService);
        verify(templateService).apply(
                "templates/data_type",
                new File(options.getDataTypeFolder(), "XPathType.java"),
                new Context(type, options.getDataTypePackage(), Set.of("org.schema.model.JsonLdTypeName"))
        );
    }

    @Test
    void generate_enumeration() throws IOException {
        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:ActionStatusType");
        when(type.getName()).thenReturn("ActionStatusType");
        when(type.isEnumerationType()).thenReturn(true);
        when(type.getEnumerationMembers()).thenReturn(List.of("PotentialActionStatus", "ActiveActionStatus", "FailedActionStatus", "CompletedActionStatus"));
        when(type.getProperties()).thenReturn(Collections.emptySet());
        when(schemaDefinitions.entrySet()).thenReturn(Set.of(new AbstractMap.SimpleEntry<>("schema:ActionStatusType", type)));

        schemaModelGenerator.generate();

        verifyCommonTemplates(options, templateService);
        verify(templateService).apply(
                "templates/type_interface",
                new File(options.getModelFolder(), "ActionStatusType.java"),
                new Context(type, options.getModelPackage(), Collections.emptySet())
        );
        verify(templateService).apply(
                "templates/type_enumeration",
                new File(options.getModelImplFolder(), "ActionStatusTypeEnum.java"),
                new Context(type, options.getModelImplPackage(), Set.of("org.schema.model.ActionStatusType", "org.schema.model.JsonLdTypeName"))
        );
    }

    @Test
    void generate_type() throws IOException {
        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:Thing");
        when(type.getName()).thenReturn("Thing");
        when(type.getProperties()).thenReturn(Collections.emptySet());
        when(schemaDefinitions.entrySet()).thenReturn(Set.of(new AbstractMap.SimpleEntry<>("schema:Thing", type)));

        schemaModelGenerator.generate();

        verifyCommonTemplates(options, templateService);
        verify(templateService).apply(
                "templates/type_interface",
                new File(options.getModelFolder(), "Thing.java"),
                new Context(type, options.getModelPackage(), Collections.emptySet())
        );
        verify(templateService).apply(
                "templates/type_implementation",
                new File(options.getModelImplFolder(), "ThingImpl.java"),
                new Context(type, options.getModelImplPackage(), Set.of("org.schema.model.Thing", "org.schema.model.JsonLdTypeName"))
        );
    }

    @Test
    void generate_with_success_handler() throws IOException {
        final SuccessHandler successHandler = mock(SuccessHandler.class);
        options.addSuccessHandler(successHandler);
        final ErrorHandler errorHandler = mock(ErrorHandler.class);
        options.addErrorHandler(errorHandler);

        when(schemaDefinitions.entrySet()).thenReturn(Collections.emptySet());

        schemaModelGenerator.generate();

        verifyCommonTemplates(options, templateService, successHandler);
        verifyNoInteractions(errorHandler);
    }

    @Test
    void generate_with_error_handler() throws IOException {
        final SuccessHandler successHandler = mock(SuccessHandler.class);
        options.addSuccessHandler(successHandler);
        final ErrorHandler errorHandler = mock(ErrorHandler.class);
        options.addErrorHandler(errorHandler);

        when(schemaDefinitions.entrySet()).thenReturn(Collections.emptySet());

        final File modelFolder = options.getModelFolder();
        final File modelImplFolder = options.getModelImplFolder();
        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();
        final String dataTypePackage = options.getDataTypePackage();

        final IOException ioException1 = new IOException();
        doThrow(ioException1).when(templateService).apply(
                "templates/jsonld_typename",
                new File(modelFolder, "JsonLdTypeName.java"),
                new Context(null, modelPackage, Collections.emptySet())
        );
        final IOException ioException2 = new IOException();
        doThrow(ioException2).when(templateService).apply(
                "templates/jsonld_node",
                new File(modelFolder, "JsonLdNode.java"),
                new Context(null, modelPackage, Collections.emptySet())
        );
        final IOException ioException3 = new IOException();
        doThrow(ioException3).when(templateService).apply(
                "templates/jsonld_node_impl",
                new File(modelImplFolder, "JsonLdNodeImpl.java"),
                new Context(null, modelImplPackage, Set.of(
                        SchemaGeneratorUtils.resolveClassName(modelPackage, dataTypePackage, SchemaGeneratorUtils.JSON_LD_NODE),
                        SchemaGeneratorUtils.resolveClassName(modelPackage, dataTypePackage, SchemaGeneratorUtils.JSON_LD_TYPE_NAME)
                ))
        );

        schemaModelGenerator.generate();

        verify(errorHandler).onError("templates/jsonld_typename",
                new File(modelFolder, "JsonLdTypeName.java"),
                new Context(null, modelPackage, Collections.emptySet()),
                ioException1
        );
        verify(errorHandler).onError(
                "templates/jsonld_node",
                new File(modelFolder, "JsonLdNode.java"),
                new Context(null, modelPackage, Collections.emptySet()),
                ioException2
        );
        verify(errorHandler).onError(
                "templates/jsonld_node_impl",
                new File(modelImplFolder, "JsonLdNodeImpl.java"),
                new Context(null, modelImplPackage, Set.of(
                        SchemaGeneratorUtils.resolveClassName(modelPackage, dataTypePackage, SchemaGeneratorUtils.JSON_LD_NODE),
                        SchemaGeneratorUtils.resolveClassName(modelPackage, dataTypePackage, SchemaGeneratorUtils.JSON_LD_TYPE_NAME)
                )),
                ioException3
        );
        verifyNoInteractions(successHandler);
    }

    private static void verifyCommonTemplates(GeneratorOptions options, TemplateService templateService) throws IOException {
        final File modelFolder = options.getModelFolder();
        final File modelImplFolder = options.getModelImplFolder();
        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();
        final String dataTypePackage = options.getDataTypePackage();

        verify(templateService).apply(
                "templates/jsonld_typename",
                new File(modelFolder, "JsonLdTypeName.java"),
                new Context(null, modelPackage, Collections.emptySet())
        );
        verify(templateService).apply(
                "templates/jsonld_node",
                new File(modelFolder, "JsonLdNode.java"),
                new Context(null, modelPackage, Collections.emptySet())
        );
        verify(templateService).apply(
                "templates/jsonld_node_impl",
                new File(modelImplFolder, "JsonLdNodeImpl.java"),
                new Context(null, modelImplPackage, Set.of(
                        SchemaGeneratorUtils.resolveClassName(modelPackage, dataTypePackage, SchemaGeneratorUtils.JSON_LD_NODE),
                        SchemaGeneratorUtils.resolveClassName(modelPackage, dataTypePackage, SchemaGeneratorUtils.JSON_LD_TYPE_NAME)
                ))
        );
    }

    private static void verifyCommonTemplates(GeneratorOptions options, TemplateService templateService, SuccessHandler successHandler) throws IOException {
        verifyCommonTemplates(options, templateService);

        final File modelFolder = options.getModelFolder();
        final File modelImplFolder = options.getModelImplFolder();
        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();
        final String dataTypePackage = options.getDataTypePackage();

        verify(successHandler).onSuccess("templates/jsonld_typename",
                new File(modelFolder, "JsonLdTypeName.java"),
                new Context(null, modelPackage, Collections.emptySet())
        );
        verify(successHandler).onSuccess(
                "templates/jsonld_node",
                new File(modelFolder, "JsonLdNode.java"),
                new Context(null, modelPackage, Collections.emptySet())
        );
        verify(successHandler).onSuccess(
                "templates/jsonld_node_impl",
                new File(modelImplFolder, "JsonLdNodeImpl.java"),
                new Context(null, modelImplPackage, Set.of(
                        SchemaGeneratorUtils.resolveClassName(modelPackage, dataTypePackage, SchemaGeneratorUtils.JSON_LD_NODE),
                        SchemaGeneratorUtils.resolveClassName(modelPackage, dataTypePackage, SchemaGeneratorUtils.JSON_LD_TYPE_NAME)
                ))
        );
    }
}