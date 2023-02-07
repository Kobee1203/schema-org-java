package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.generator.model.Property;
import com.weedow.schemaorg.generator.model.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

class SchemaGeneratorUtilsTest {

    @AfterEach
    public void tearDown() {
        SchemaGeneratorUtils.clearCache();
    }

    @Test
    void resolveClassName_with_datatype() {
        String dataTypePackage = "datatype";
        Type type = type("schema:URL", "MyType");
        String result = SchemaGeneratorUtils.resolveClassName(null, dataTypePackage, type);
        Assertions.assertThat(result).isEqualTo("datatype.MyType");
    }

    @Test
    void resolveClassName_with_subdatatype() {
        String dataTypePackage = "datatype";
        Type type = type("schema:MyDataType", "MyDataType");
        type.addParent(type("schema:Text", "Parent"));
        String result = SchemaGeneratorUtils.resolveClassName(null, dataTypePackage, type);
        Assertions.assertThat(result).isEqualTo("datatype.MyDataType");
    }

    @Test
    void resolveClassName_when_type_is_not_datatype() {
        String modelPackage = "model";
        Type type = type("schema:MyType", "MyType");
        String result = SchemaGeneratorUtils.resolveClassName(modelPackage, null, type);
        Assertions.assertThat(result).isEqualTo("model.MyType");
    }

    @Test
    void getImports_without_properties_and_additional_imports() {
        String dataTypePackage = "datatype";
        Type type = type("schema:Text", "MyType");
        List<String> additionalImports = List.of("OtherImport");
        Set<String> result = SchemaGeneratorUtils.getImports(null, dataTypePackage, type, additionalImports);
        Assertions.assertThat(result).containsExactly("OtherImport");
    }

    @Test
    void getImports_with_properties_and_additional_imports() {
        String dataTypePackage = "datatype";
        Type type = type("schema:Text", "MyType");
        type.addProperty(new Property("schema:Boolean", null, null, null, Arrays.asList(type("schema:Text", "Type1"), type("schema:URL", "Type2"))));
        List<String> additionalImports = List.of("OtherImport");
        Set<String> result = SchemaGeneratorUtils.getImports(null, dataTypePackage, type, additionalImports);
        Assertions.assertThat(result).containsExactly("OtherImport", "datatype.Type1", "datatype.Type2");
    }

    @Test
    void getImports_with_properties_and_without_additional_imports() {
        String dataTypePackage = "datatype";
        Type type = type("schema:Text", "MyType");
        type.addProperty(new Property("schema:Boolean", null, null, null, Arrays.asList(type("schema:Text", "Type1"), type("schema:URL", "Type2"))));
        Set<String> result = SchemaGeneratorUtils.getImports(null, dataTypePackage, type, Collections.emptyList());
        Assertions.assertThat(result).containsExactly("datatype.Type1", "datatype.Type2");
    }

    @Test
    void getAllImports_with_parent() {
        String modelPackage = "model";
        String dataTypePackage = "datatype";
        Type type = type("schema:MyType", "MyType");
        type.addParent(type("schema:Text", "Parent"));
        Set<String> result = SchemaGeneratorUtils.getAllImports(modelPackage, dataTypePackage, type);
        Assertions.assertThat(result).containsExactly(
                "datatype.Parent",
                "com.weedow.schemaorg.commons.model.JsonLdTypeName",
                "java.util.List",
                "datatype.MyType"
        );

        // Test cache: pass null for parameters not required to read cache
        Set<String> result2 = SchemaGeneratorUtils.getAllImports(null, null, type);
        Assertions.assertThat(result2).containsExactly(
                "datatype.Parent",
                "com.weedow.schemaorg.commons.model.JsonLdTypeName",
                "java.util.List",
                "datatype.MyType"
        );
    }

    @Test
    void getAllImports_without_parent() {
        String modelPackage = "model";
        String dataTypePackage = "datatype";
        Type type = type("schema:NewType", "NewType");
        Set<String> result = SchemaGeneratorUtils.getAllImports(modelPackage, dataTypePackage, type);
        Assertions.assertThat(result).containsExactly("model.NewType", "com.weedow.schemaorg.commons.model.JsonLdTypeName", "java.util.List");

        // Test cache: pass null for parameters not required to read cache
        Set<String> result2 = SchemaGeneratorUtils.getAllImports(null, null, type);
        Assertions.assertThat(result2).containsExactly("model.NewType", "com.weedow.schemaorg.commons.model.JsonLdTypeName", "java.util.List");
    }

    @Test
    void getAllImports_with_field_with_multiple_types() {
        String modelPackage = "model";
        String dataTypePackage = "datatype";
        Type type = type("schema:NewType", "NewType");
        type.addProperty(new Property("schema:property", null, null, null, List.of(type("schema:Type1", "Type1"), type("schema:Type2", "Type2"))));
        Set<String> result = SchemaGeneratorUtils.getAllImports(modelPackage, dataTypePackage, type);
        Assertions.assertThat(result).containsExactly(
                "model.Type1",
                "model.Type2",
                "model.NewType",
                "com.weedow.schemaorg.commons.model.JsonLdTypeName",
                "com.weedow.schemaorg.commons.model.JsonLdFieldTypes",
                "java.util.List"
        );

        // Test cache: pass null for parameters not required to read cache
        Set<String> result2 = SchemaGeneratorUtils.getAllImports(null, null, type);
        Assertions.assertThat(result2).containsExactly(
                "model.Type1",
                "model.Type2",
                "model.NewType",
                "com.weedow.schemaorg.commons.model.JsonLdTypeName",
                "com.weedow.schemaorg.commons.model.JsonLdFieldTypes",
                "java.util.List"
        );
    }

    private static Type type(String id, String name) {
        Type type = new Type(id);
        type.setName(name);
        return type;
    }
}