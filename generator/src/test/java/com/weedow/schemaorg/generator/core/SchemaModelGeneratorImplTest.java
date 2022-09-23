package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.template.TemplateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchemaModelGeneratorImplTest {

    @Mock
    private TemplateService templateService;

    @Mock
    private Map<String, Type> schemaDefinitions;

    @InjectMocks
    private SchemaModelGeneratorImpl schemaModelGenerator;

    @Test
    void generate_without_schema_definitions() throws IOException {
        when(schemaDefinitions.entrySet()).thenReturn(Collections.emptySet());

        GeneratorOptions options = new GeneratorOptions();

        schemaModelGenerator.generate(options);

        final File modelFolder = options.getModelFolder();
        final File modelImplFolder = options.getModelImplFolder();
        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();
        final String dataTypePackage = options.getDataTypePackage();

        verifyCommonTemplates(modelFolder, modelImplFolder, modelPackage, modelImplPackage, dataTypePackage);
    }

    @Test
    void generate_abstract_data_type() throws IOException {
        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:DataType");
        when(type.getName()).thenReturn("DataType");
        when(schemaDefinitions.entrySet()).thenReturn(Set.of(new AbstractMap.SimpleEntry<>("schema:DataType", type)));

        GeneratorOptions options = new GeneratorOptions();

        schemaModelGenerator.generate(options);

        final File modelFolder = options.getModelFolder();
        final File modelImplFolder = options.getModelImplFolder();
        final File dataTypeFolder = options.getDataTypeFolder();
        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();
        final String dataTypePackage = options.getDataTypePackage();

        verifyCommonTemplates(modelFolder, modelImplFolder, modelPackage, modelImplPackage, dataTypePackage);
        verify(templateService).apply(
                "templates/abstract_data_type",
                new File(dataTypeFolder, "DataType.java"),
                new Context(type, dataTypePackage, Collections.emptySet())
        );
    }

    @Test
    void generate_data_type() throws IOException {
        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:Boolean");
        when(type.getName()).thenReturn("Boolean");
        when(type.getProperties()).thenReturn(Collections.emptySet());
        when(schemaDefinitions.entrySet()).thenReturn(Set.of(new AbstractMap.SimpleEntry<>("schema:Boolean", type)));

        GeneratorOptions options = new GeneratorOptions();

        schemaModelGenerator.generate(options);

        final File modelFolder = options.getModelFolder();
        final File modelImplFolder = options.getModelImplFolder();
        final File dataTypeFolder = options.getDataTypeFolder();
        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();
        final String dataTypePackage = options.getDataTypePackage();

        verifyCommonTemplates(modelFolder, modelImplFolder, modelPackage, modelImplPackage, dataTypePackage);
        verify(templateService).apply(
                "templates/data_type",
                new File(dataTypeFolder, "Boolean.java"),
                new Context(type, dataTypePackage, Set.of("org.schema.model.JsonLdTypeName"))
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

        GeneratorOptions options = new GeneratorOptions();

        schemaModelGenerator.generate(options);

        final File modelFolder = options.getModelFolder();
        final File modelImplFolder = options.getModelImplFolder();
        final File dataTypeFolder = options.getDataTypeFolder();
        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();
        final String dataTypePackage = options.getDataTypePackage();

        verifyCommonTemplates(modelFolder, modelImplFolder, modelPackage, modelImplPackage, dataTypePackage);
        verify(templateService).apply(
                "templates/data_type",
                new File(dataTypeFolder, "XPathType.java"),
                new Context(type, dataTypePackage, Set.of("org.schema.model.JsonLdTypeName"))
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

        GeneratorOptions options = new GeneratorOptions();

        schemaModelGenerator.generate(options);

        final File modelFolder = options.getModelFolder();
        final File modelImplFolder = options.getModelImplFolder();
        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();
        final String dataTypePackage = options.getDataTypePackage();

        verifyCommonTemplates(modelFolder, modelImplFolder, modelPackage, modelImplPackage, dataTypePackage);
        verify(templateService).apply(
                "templates/type_interface",
                new File(modelFolder, "ActionStatusType.java"),
                new Context(type, modelPackage, Collections.emptySet())
        );
        verify(templateService).apply(
                "templates/type_enumeration",
                new File(modelImplFolder, "ActionStatusTypeEnum.java"),
                new Context(type, modelImplPackage, Set.of("org.schema.model.ActionStatusType", "org.schema.model.JsonLdTypeName"))
        );
    }

    @Test
    void generate_type() throws IOException {
        final Type type = mock(Type.class);
        when(type.getId()).thenReturn("schema:Thing");
        when(type.getName()).thenReturn("Thing");
        when(type.getProperties()).thenReturn(Collections.emptySet());
        when(schemaDefinitions.entrySet()).thenReturn(Set.of(new AbstractMap.SimpleEntry<>("schema:Thing", type)));

        GeneratorOptions options = new GeneratorOptions();

        schemaModelGenerator.generate(options);

        final File modelFolder = options.getModelFolder();
        final File modelImplFolder = options.getModelImplFolder();
        final String modelPackage = options.getModelPackage();
        final String modelImplPackage = options.getModelImplPackage();
        final String dataTypePackage = options.getDataTypePackage();

        verifyCommonTemplates(modelFolder, modelImplFolder, modelPackage, modelImplPackage, dataTypePackage);
        verify(templateService).apply(
                "templates/type_interface",
                new File(modelFolder, "Thing.java"),
                new Context(type, modelPackage, Collections.emptySet())
        );
        verify(templateService).apply(
                "templates/type_implementation",
                new File(modelImplFolder, "ThingImpl.java"),
                new Context(type, modelImplPackage, Set.of("org.schema.model.Thing", "org.schema.model.JsonLdTypeName"))
        );
    }

    private void verifyCommonTemplates(File modelFolder, File modelImplFolder, String modelPackage, String modelImplPackage, String dataTypePackage) throws IOException {
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
}