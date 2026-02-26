package com.weedow.schemaorg.generator.core.filter;

import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;
import com.weedow.schemaorg.generator.model.Property;
import com.weedow.schemaorg.generator.model.Type;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.weedow.schemaorg.generator.logging.Emojis.ARCHIVED;
import static org.assertj.core.api.Assertions.assertThat;
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

        assertThat(result).containsOnly(
                entry("schema:String", stringType),
                entry("schema:DataType", dataType),
                entry("schema:Thing", thingType)
        );
    }

    @Test
    void filter_should_exclude_types_with_null_name() {
        try (LogCaptor logCaptor = LogCaptor.forClass(SchemaDefinitionFilterImpl.class)) {
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

            assertThat(result)
                    .doesNotContainKey("schema:ArchivedType")
                    .containsKey("schema:ValidType");
            assertThat(logCaptor.getLogs()).containsExactly(ARCHIVED.value() + " ** ARCHIVED ** schema:ArchivedType has been retired from the vocabulary (see https://schema.org/docs/attic.home.html)");
        }
    }

    @Test
    void filter_should_exclude_types_with_empty_name() {
        try (LogCaptor logCaptor = LogCaptor.forClass(SchemaDefinitionFilterImpl.class)) {
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

            assertThat(result)
                    .doesNotContainKey("schema:ArchivedType")
                    .containsKey("schema:ValidType");
            assertThat(logCaptor.getInfoLogs()).containsExactly(ARCHIVED.value() + " ** ARCHIVED ** schema:ArchivedType has been retired from the vocabulary (see https://schema.org/docs/attic.home.html)");
        }
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

        assertThat(result)
                .containsKey("schema:Thing")
                .containsKey("schema:Person")
                .hasSize(2);
    }

    @Test
    void filter_should_include_subtypes_when_type_is_enumeration() {
        Type enumerationSubType1 = mock(Type.class);
        when(enumerationSubType1.getId()).thenReturn("schema:EnumValue1");
        when(enumerationSubType1.getName()).thenReturn("EnumValue1");
        when(enumerationSubType1.getParents()).thenReturn(Collections.emptyList());
        when(enumerationSubType1.getAllProperties()).thenReturn(Collections.emptySet());
        when(enumerationSubType1.isEnumerationType()).thenReturn(false);

        Type enumerationSubType2 = mock(Type.class);
        when(enumerationSubType2.getId()).thenReturn("schema:EnumValue2");
        when(enumerationSubType2.getName()).thenReturn("EnumValue2");
        when(enumerationSubType2.getParents()).thenReturn(Collections.emptyList());
        when(enumerationSubType2.getAllProperties()).thenReturn(Collections.emptySet());
        when(enumerationSubType2.isEnumerationType()).thenReturn(false);

        // Create enumeration type that includes the subtypes
        Type enumerationType = mock(Type.class);
        when(enumerationType.getId()).thenReturn("schema:TestEnumeration");
        when(enumerationType.getName()).thenReturn("TestEnumeration");
        when(enumerationType.getParents()).thenReturn(Collections.emptyList());
        when(enumerationType.getAllProperties()).thenReturn(Collections.emptySet());
        when(enumerationType.isEnumerationType()).thenReturn(true);
        when(enumerationType.getSubTypes()).thenReturn(List.of(enumerationSubType1, enumerationSubType2));

        Map<String, Type> schemaDefinitions = new HashMap<>();
        schemaDefinitions.put("schema:TestEnumeration", enumerationType);
        schemaDefinitions.put("schema:EnumValue1", enumerationSubType1);
        schemaDefinitions.put("schema:EnumValue2", enumerationSubType2);

        List<String> modelIds = List.of("schema:TestEnumeration");

        Map<String, Type> result = schemaDefinitionFilter.filter(schemaDefinitions, modelIds);

        assertThat(result).containsOnly(
                entry("schema:TestEnumeration", enumerationType),
                entry("schema:EnumValue1", enumerationSubType1),
                entry("schema:EnumValue2", enumerationSubType2)
        );
    }

    @Test
    void filter_should_log_verbose_when_adding_types() {
        boolean backupVerbose = SchemaModelGeneratorConstants.isVerbose();
        SchemaModelGeneratorConstants.setVerbose(true);

        try (LogCaptor logCaptor = LogCaptor.forClass(SchemaDefinitionFilterImpl.class)) {
            Type parentType = mock(Type.class);
            when(parentType.getId()).thenReturn("schema:Thing");
            when(parentType.getName()).thenReturn("Thing");
            when(parentType.getParents()).thenReturn(Collections.emptyList());
            when(parentType.getAllProperties()).thenReturn(Collections.emptySet());
            when(parentType.isEnumerationType()).thenReturn(false);

            Type childType = mock(Type.class);
            when(childType.getId()).thenReturn("schema:Person");
            when(childType.getName()).thenReturn("Person");
            when(childType.getParents()).thenReturn(List.of(parentType));
            when(childType.getAllProperties()).thenReturn(Collections.emptySet());
            when(childType.isEnumerationType()).thenReturn(false);

            Map<String, Type> schemaDefinitions = new HashMap<>();
            schemaDefinitions.put("schema:Thing", parentType);
            schemaDefinitions.put("schema:Person", childType);

            List<String> modelIds = List.of("schema:Person");

            schemaDefinitionFilter.filter(schemaDefinitions, modelIds);

            assertThat(logCaptor.getInfoLogs()).containsExactly(
                    "adding type: schema:Person",
                    "adding type: schema:Thing"
            );
        } finally {
            SchemaModelGeneratorConstants.setVerbose(backupVerbose);
        }
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