package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.commons.model.JsonLdTypeName;
import com.weedow.schemaorg.generator.core.copy.CopyService;
import com.weedow.schemaorg.generator.core.filter.SchemaDefinitionFilter;
import com.weedow.schemaorg.generator.core.handler.ErrorHandler;
import com.weedow.schemaorg.generator.core.handler.SuccessHandler;
import com.weedow.schemaorg.generator.core.stream.StreamService;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.template.TemplateService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchemaModelGeneratorImplTest {

    @Spy
    private GeneratorOptions options = new GeneratorOptions();

    @Mock
    private TemplateService templateService;

    @Mock
    private SchemaDefinitionFilter schemaDefinitionFilter;

    @Mock
    private Map<String, Type> schemaDefinitions;

    @Mock
    private CopyService copyService;

    @Mock
    private StreamService streamService;

    @InjectMocks
    private SchemaModelGeneratorImpl schemaModelGenerator;

    @AfterEach
    public void tearDown() {
        SchemaGeneratorUtils.clearCache();
    }

    @Test
    void generate_without_schema_definitions() {
        when(schemaDefinitionFilter.filter(schemaDefinitions, null)).thenReturn(Collections.emptyMap());

        schemaModelGenerator.generate();

        verifyNoInteractions(streamService);
        verifyNoInteractions(copyService);
        verifyNoInteractions(templateService);
    }

    @Test
    void generate_abstract_data_type() throws IOException {
        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:DataType");
        when(type.getName()).thenReturn("DataType");

        Map<String, Type> filteredSchemaDefinitions = Map.of("schema:DataType", type);
        when(schemaDefinitionFilter.filter(schemaDefinitions, null)).thenReturn(filteredSchemaDefinitions);
        when(streamService.stream(filteredSchemaDefinitions)).thenReturn(filteredSchemaDefinitions.values().stream());

        schemaModelGenerator.generate();

        Path targetDirectory = Paths.get("target", "generated-sources", "schemaorg", "com", "weedow", "schemaorg", "commons", "model");
        verify(copyService).copy(JsonLdTypeName.class, targetDirectory);
        verify(copyService).copy(JsonLdNode.class, targetDirectory);
        verify(copyService).copy(JsonLdNodeImpl.class, targetDirectory);

        verify(templateService).apply(
                "templates/abstract_data_type",
                options.getDataTypeFolder().resolve("DataType.java"),
                new Context(type, options.getDataTypePackage(), Collections.emptySet())
        );
    }

    @Test
    void generate_data_type() throws IOException {
        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:Boolean");
        when(type.getName()).thenReturn("Boolean");
        when(type.getProperties()).thenReturn(Collections.emptySet());

        Map<String, Type> filteredSchemaDefinitions = Map.of("schema:Boolean", type);
        when(schemaDefinitionFilter.filter(schemaDefinitions, null)).thenReturn(filteredSchemaDefinitions);
        when(streamService.stream(filteredSchemaDefinitions)).thenReturn(filteredSchemaDefinitions.values().stream());

        schemaModelGenerator.generate();

        Path targetDirectory = Paths.get("target", "generated-sources", "schemaorg", "com", "weedow", "schemaorg", "commons", "model");
        verify(copyService).copy(JsonLdTypeName.class, targetDirectory);
        verify(copyService).copy(JsonLdNode.class, targetDirectory);
        verify(copyService).copy(JsonLdNodeImpl.class, targetDirectory);

        verify(templateService).apply(
                "templates/data_type",
                options.getDataTypeFolder().resolve("Boolean.java"),
                new Context(type, options.getDataTypePackage(), Set.of(JsonLdTypeName.class.getName()))
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

        Map<String, Type> filteredSchemaDefinitions = Map.of("schema:XPathType", type);
        when(schemaDefinitionFilter.filter(schemaDefinitions, null)).thenReturn(filteredSchemaDefinitions);
        when(streamService.stream(filteredSchemaDefinitions)).thenReturn(filteredSchemaDefinitions.values().stream());

        schemaModelGenerator.generate();

        Path targetDirectory = Paths.get("target", "generated-sources", "schemaorg", "com", "weedow", "schemaorg", "commons", "model");
        verify(copyService).copy(JsonLdTypeName.class, targetDirectory);
        verify(copyService).copy(JsonLdNode.class, targetDirectory);
        verify(copyService).copy(JsonLdNodeImpl.class, targetDirectory);

        verify(templateService).apply(
                "templates/data_type",
                options.getDataTypeFolder().resolve("XPathType.java"),
                new Context(type, options.getDataTypePackage(), Set.of(JsonLdTypeName.class.getName()))
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

        Map<String, Type> filteredSchemaDefinitions = Map.of("schema:ActionStatusType", type);
        when(schemaDefinitionFilter.filter(schemaDefinitions, null)).thenReturn(filteredSchemaDefinitions);
        when(streamService.stream(filteredSchemaDefinitions)).thenReturn(filteredSchemaDefinitions.values().stream());

        schemaModelGenerator.generate();

        Path targetDirectory = Paths.get("target", "generated-sources", "schemaorg", "com", "weedow", "schemaorg", "commons", "model");
        verify(copyService).copy(JsonLdTypeName.class, targetDirectory);
        verify(copyService).copy(JsonLdNode.class, targetDirectory);
        verify(copyService).copy(JsonLdNodeImpl.class, targetDirectory);

        verify(templateService).apply(
                "templates/type_interface",
                options.getModelFolder().resolve("ActionStatusType.java"),
                new Context(type, options.getModelPackage(), Collections.emptySet())
        );
        verify(templateService).apply(
                "templates/type_enumeration",
                options.getModelImplFolder().resolve("ActionStatusTypeEnum.java"),
                new Context(type, options.getModelImplPackage(), Set.of("org.schema.model.ActionStatusType", JsonLdTypeName.class.getName()))
        );
    }

    @Test
    void generate_type() throws IOException {
        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:Thing");
        when(type.getName()).thenReturn("Thing");
        when(type.getProperties()).thenReturn(Collections.emptySet());

        Map<String, Type> filteredSchemaDefinitions = Map.of("schema:Thing", type);
        when(schemaDefinitionFilter.filter(schemaDefinitions, null)).thenReturn(filteredSchemaDefinitions);
        when(streamService.stream(filteredSchemaDefinitions)).thenReturn(filteredSchemaDefinitions.values().stream());

        schemaModelGenerator.generate();

        verify(templateService).apply(
                "templates/type_interface",
                options.getModelFolder().resolve("Thing.java"),
                new Context(type, options.getModelPackage(), Collections.emptySet())
        );
        verify(templateService).apply(
                "templates/type_implementation",
                options.getModelImplFolder().resolve("ThingImpl.java"),
                new Context(type, options.getModelImplPackage(), Set.of("org.schema.model.Thing", JsonLdTypeName.class.getName()))
        );
    }

    @Test
    void generate_with_success_handler() {
        final SuccessHandler successHandler = mock(SuccessHandler.class);
        options.addSuccessHandler(successHandler);
        final ErrorHandler errorHandler = mock(ErrorHandler.class);
        options.addErrorHandler(errorHandler);

        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:Thing");
        when(type.getName()).thenReturn("Thing");
        when(type.getProperties()).thenReturn(Collections.emptySet());

        final Path modelFolder = options.getModelFolder();
        final Path modelImplFolder = options.getModelImplFolder();
        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();

        Map<String, Type> filteredSchemaDefinitions = Map.of("schema:Thing", type);
        when(schemaDefinitionFilter.filter(schemaDefinitions, null)).thenReturn(filteredSchemaDefinitions);
        when(streamService.stream(filteredSchemaDefinitions)).thenReturn(filteredSchemaDefinitions.values().stream());

        schemaModelGenerator.generate();

        verify(successHandler).onSuccess(
                "templates/type_interface",
                modelFolder.resolve("Thing.java"),
                new Context(type, modelPackage, Collections.emptySet())
        );
        verify(successHandler).onSuccess(
                "templates/type_implementation",
                modelImplFolder.resolve("ThingImpl.java"),
                new Context(type, modelImplPackage, Set.of("org.schema.model.Thing", JsonLdTypeName.class.getName()))
        );

        verifyNoInteractions(errorHandler);
    }

    @Test
    void generate_with_error_handler() throws IOException {
        final SuccessHandler successHandler = mock(SuccessHandler.class);
        options.addSuccessHandler(successHandler);
        final ErrorHandler errorHandler = mock(ErrorHandler.class);
        options.addErrorHandler(errorHandler);

        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:Thing");
        when(type.getName()).thenReturn("Thing");
        when(type.getProperties()).thenReturn(Collections.emptySet());

        Map<String, Type> filteredSchemaDefinitions = Map.of("schema:Thing", type);
        when(schemaDefinitionFilter.filter(schemaDefinitions, null)).thenReturn(filteredSchemaDefinitions);
        when(streamService.stream(filteredSchemaDefinitions)).thenReturn(filteredSchemaDefinitions.values().stream());

        final Path modelFolder = options.getModelFolder();
        final Path modelImplFolder = options.getModelImplFolder();
        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();

        final IOException ioException1 = new IOException();
        doThrow(ioException1).when(templateService).apply(
                "templates/type_interface",
                modelFolder.resolve("Thing.java"),
                new Context(type, modelPackage, Collections.emptySet())
        );
        final IOException ioException2 = new IOException();
        doThrow(ioException2).when(templateService).apply(
                "templates/type_implementation",
                modelImplFolder.resolve("ThingImpl.java"),
                new Context(type, modelImplPackage, Set.of("org.schema.model.Thing", JsonLdTypeName.class.getName()))
        );

        schemaModelGenerator.generate();

        verify(errorHandler).onError(
                "templates/type_interface",
                modelFolder.resolve("Thing.java"),
                new Context(type, modelPackage, Collections.emptySet()),
                ioException1
        );
        verify(errorHandler).onError(
                "templates/type_implementation",
                modelImplFolder.resolve("ThingImpl.java"),
                new Context(type, modelImplPackage, Set.of("org.schema.model.Thing", JsonLdTypeName.class.getName())),
                ioException2
        );

        verifyNoInteractions(successHandler);
    }

    @Test
    void cannot_generate_when_modelFolder_is_not_created() {
        final Path modelFolder = options.getModelFolder();

        try (MockedStatic<Files> files = Mockito.mockStatic(Files.class)) {
            files.when(() -> Files.createDirectories(modelFolder)).thenThrow(new IOException("Could not create the directory"));

            schemaModelGenerator.generate();

            verifyNoInteractions(templateService);
            verifyNoInteractions(schemaDefinitionFilter);
            verifyNoInteractions(schemaDefinitions);
        }
    }

    @Test
    void cannot_generate_when_modelImplFolder_is_not_created() {
        final Path modelFolder = options.getModelFolder();
        final Path modelImplFolder = options.getModelImplFolder();

        try (MockedStatic<Files> files = Mockito.mockStatic(Files.class)) {
            files.when(() -> Files.createDirectories(modelFolder)).thenReturn(modelFolder);
            files.when(() -> Files.exists(modelFolder)).thenReturn(true);
            files.when(() -> Files.createDirectories(modelImplFolder)).thenThrow(new IOException("Could not create the directory"));

            schemaModelGenerator.generate();

            verifyNoInteractions(templateService);
            verifyNoInteractions(schemaDefinitionFilter);
            verifyNoInteractions(schemaDefinitions);
        }
    }

    @Test
    void cannot_generate_when_dataTypeFolder_is_not_created() {
        final Path modelFolder = options.getModelFolder();
        final Path modelImplFolder = options.getModelImplFolder();
        final Path dataTypeFolder = options.getDataTypeFolder();

        try (MockedStatic<Files> files = Mockito.mockStatic(Files.class)) {
            files.when(() -> Files.createDirectories(modelFolder)).thenReturn(modelFolder);
            files.when(() -> Files.exists(modelFolder)).thenReturn(true);
            files.when(() -> Files.createDirectories(modelImplFolder)).thenReturn(modelImplFolder);
            files.when(() -> Files.exists(modelImplFolder)).thenReturn(true);
            files.when(() -> Files.createDirectories(dataTypeFolder)).thenThrow(new IOException("Could not create the directory"));

            schemaModelGenerator.generate();

            verifyNoInteractions(templateService);
            verifyNoInteractions(schemaDefinitionFilter);
            verifyNoInteractions(schemaDefinitions);
        }
    }

    @Test
    void generate_without_common_models_copy() throws IOException {
        options.setCopyCommonModels(false);

        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:Boolean");
        when(type.getName()).thenReturn("Boolean");
        when(type.getProperties()).thenReturn(Collections.emptySet());

        Map<String, Type> filteredSchemaDefinitions = Map.of("schema:Boolean", type);
        when(schemaDefinitionFilter.filter(schemaDefinitions, null)).thenReturn(filteredSchemaDefinitions);
        when(streamService.stream(filteredSchemaDefinitions)).thenReturn(filteredSchemaDefinitions.values().stream());

        schemaModelGenerator.generate();

        verifyNoInteractions(copyService);

        verify(templateService).apply(
                "templates/data_type",
                options.getDataTypeFolder().resolve("Boolean.java"),
                new Context(type, options.getDataTypePackage(), Set.of(JsonLdTypeName.class.getName()))
        );
    }
}