package com.weedow.schemaorg.generator.core.filter;

import com.weedow.schemaorg.generator.model.Property;
import com.weedow.schemaorg.generator.model.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchemaDefinitionFilterImplTest {

    private final SchemaDefinitionFilter schemaDefinitionFilter = new SchemaDefinitionFilterImpl();

    @Test
    void filter() {
        Type dataType = type("schema:DataType", Collections.emptySet(), Collections.emptyList());

        Property property1 = mock(Property.class);
        when(property1.getTypes()).thenReturn(Collections.emptyList());

        Property property2 = mock(Property.class);
        Type stringType = type("schema:String", Collections.emptySet(), List.of(dataType));
        when(property2.getTypes()).thenReturn(List.of(stringType));

        Type thingType = type("schema:Thing", Set.of(property1, property2), Collections.emptyList());

        Type unknownType = mock(Type.class);

        Map<String, Type> schemaDefinitions = new HashMap<>();
        schemaDefinitions.put("DataType", dataType);
        schemaDefinitions.put("schema:Thing", thingType);
        schemaDefinitions.put("unknown", unknownType);

        List<String> modelIds = List.of("schema:DataType", "schema:Thing");

        Map<String, Type> result = schemaDefinitionFilter.filter(schemaDefinitions, modelIds);

        Assertions.assertThat(result).containsOnly(
                entry("schema:String", stringType),
                entry("schema:DataType", dataType),
                entry("schema:Thing", thingType)
        );
    }

    @Test
    void filter_should_exclude_types_with_null_name() {
        Type typeWithNullName = mock(Type.class);
        when(typeWithNullName.getId()).thenReturn("schema:ArchivedType");
        when(typeWithNullName.getName()).thenReturn(null);
        when(typeWithNullName.getParents()).thenReturn(Collections.emptyList());
        when(typeWithNullName.getAllProperties()).thenReturn(Collections.emptySet());

        Type validType = mock(Type.class);
        when(validType.getId()).thenReturn("schema:ValidType");
        when(validType.getName()).thenReturn("ValidType");
        when(validType.getParents()).thenReturn(Collections.emptyList());
        when(validType.getAllProperties()).thenReturn(Collections.emptySet());

        Map<String, Type> schemaDefinitions = new HashMap<>();
        schemaDefinitions.put("schema:ArchivedType", typeWithNullName);
        schemaDefinitions.put("schema:ValidType", validType);

        Map<String, Type> result = schemaDefinitionFilter.filter(schemaDefinitions, null);

        Assertions.assertThat(result)
                .doesNotContainKey("schema:ArchivedType")
                .containsKey("schema:ValidType");
    }

    @Test
    void filter_should_exclude_types_with_empty_name() {
        Type typeWithEmptyName = mock(Type.class);
        when(typeWithEmptyName.getId()).thenReturn("schema:ArchivedType");
        when(typeWithEmptyName.getName()).thenReturn("");
        when(typeWithEmptyName.getParents()).thenReturn(Collections.emptyList());
        when(typeWithEmptyName.getAllProperties()).thenReturn(Collections.emptySet());

        Type validType = mock(Type.class);
        when(validType.getId()).thenReturn("schema:ValidType");
        when(validType.getName()).thenReturn("ValidType");
        when(validType.getParents()).thenReturn(Collections.emptyList());
        when(validType.getAllProperties()).thenReturn(Collections.emptySet());

        Map<String, Type> schemaDefinitions = new HashMap<>();
        schemaDefinitions.put("schema:ArchivedType", typeWithEmptyName);
        schemaDefinitions.put("schema:ValidType", validType);

        Map<String, Type> result = schemaDefinitionFilter.filter(schemaDefinitions, null);

        Assertions.assertThat(result)
                .doesNotContainKey("schema:ArchivedType")
                .containsKey("schema:ValidType");
    }

    @Test
    void filter_should_keep_types_with_valid_name() {
        Type validType1 = mock(Type.class);
        when(validType1.getId()).thenReturn("schema:Thing");
        when(validType1.getName()).thenReturn("Thing");
        when(validType1.getParents()).thenReturn(Collections.emptyList());
        when(validType1.getAllProperties()).thenReturn(Collections.emptySet());

        Type validType2 = mock(Type.class);
        when(validType2.getId()).thenReturn("schema:Person");
        when(validType2.getName()).thenReturn("Person");
        when(validType2.getParents()).thenReturn(Collections.emptyList());
        when(validType2.getAllProperties()).thenReturn(Collections.emptySet());

        Map<String, Type> schemaDefinitions = new HashMap<>();
        schemaDefinitions.put("schema:Thing", validType1);
        schemaDefinitions.put("schema:Person", validType2);

        Map<String, Type> result = schemaDefinitionFilter.filter(schemaDefinitions, null);

        Assertions.assertThat(result)
                .containsKey("schema:Thing")
                .containsKey("schema:Person")
                .hasSize(2);
    }

    private static Type type(String typeId, Set<Property> properties, List<Type> parents) {
        Type type = mock(Type.class);
        when(type.getId()).thenReturn(typeId);
        when(type.getName()).thenReturn(typeId.replace("schema:", ""));
        when(type.getParents()).thenReturn(parents);
        when(type.getAllProperties()).thenReturn(properties);
        return type;
    }

}