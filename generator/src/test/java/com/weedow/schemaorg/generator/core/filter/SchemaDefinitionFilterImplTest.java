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

    private static Type type(String typeId, Set<Property> properties, List<Type> parents) {
        Type type = mock(Type.class);
        when(type.getId()).thenReturn(typeId);
        when(type.getParents()).thenReturn(parents);
        when(type.getAllProperties()).thenReturn(properties);
        return type;
    }

}