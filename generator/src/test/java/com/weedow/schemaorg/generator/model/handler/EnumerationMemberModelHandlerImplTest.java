package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.weedow.schemaorg.generator.model.handler.ModelHandlerTestUtils.label;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnumerationMemberModelHandlerImplTest {

    @InjectMocks
    private EnumerationMemberModelHandlerImpl modelHandler;

    @ParameterizedTest
    @MethodSource
    void supports(List<String> types, boolean expected) {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getTypes()).thenReturn(types);

        ParserOptions options = mock(ParserOptions.class);

        boolean result = modelHandler.supports(graphItem, options);

        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void handle() {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getTypes()).thenReturn(List.of("schema:MedicalImagingTechnique"));
        when(graphItem.getLabel()).thenReturn(label("en", "Radiography"));

        ParserOptions options = mock(ParserOptions.class);

        modelHandler.handle(schemaDefinitions, graphItem, options);

        Assertions.assertThat(schemaDefinitions).isNotEmpty().containsOnlyKeys("schema:MedicalImagingTechnique");
        Assertions.assertThat(schemaDefinitions.get("schema:MedicalImagingTechnique"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:MedicalImagingTechnique", null, null, null,
                        Collections.emptySet(), Collections.emptySet(),
                        Collections.emptyList(),
                        false /* Parent is not added */, List.of("Radiography"),
                        Collections.emptyList(), Collections.emptyList()
                );
    }

    @Test
    void handle_with_multiple_enums() {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getTypes()).thenReturn(List.of("schema:MedicalImagingTechnique", "schema:MedicalSpecialty"));
        when(graphItem.getLabel()).thenReturn(label("en", "Radiography"));

        ParserOptions options = mock(ParserOptions.class);

        modelHandler.handle(schemaDefinitions, graphItem, options);

        Assertions.assertThat(schemaDefinitions).isNotEmpty().containsOnlyKeys("schema:MedicalImagingTechnique", "schema:MedicalSpecialty");
        Assertions.assertThat(schemaDefinitions.get("schema:MedicalImagingTechnique"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:MedicalImagingTechnique", null, null, null,
                        Collections.emptySet(), Collections.emptySet(),
                        Collections.emptyList(),
                        false /* Parent is not added */, List.of("Radiography"),
                        Collections.emptyList(), Collections.emptyList()
                );
        Assertions.assertThat(schemaDefinitions.get("schema:MedicalSpecialty"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:MedicalSpecialty", null, null, null,
                        Collections.emptySet(), Collections.emptySet(),
                        Collections.emptyList(),
                        false /* Parent is not added */, List.of("Radiography"),
                        Collections.emptyList(), Collections.emptyList()
                );
    }

    private static Stream<Arguments> supports() {
        return Stream.of(
                Arguments.of(List.of("schema:MedicalImagingTechnique"), true),
                Arguments.of(List.of("schema:MedicalImagingTechnique", "schema:MedicalSpecialty"), true),
                Arguments.of(List.of("schema:MedicalImagingTechnique", "rdfs:Class"), false), // types don't contain only 'schema:*'
                Arguments.of(List.of("rdfs:MedicalImagingTechnique"), false), // type not started with "schema:"
                Arguments.of(List.of("schema:DataType"), false) // "schema:DataType" not allowed
        );
    }
}