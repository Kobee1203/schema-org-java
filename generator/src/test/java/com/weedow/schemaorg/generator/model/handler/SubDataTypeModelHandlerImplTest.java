package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.SubClassOf;
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

import static com.weedow.schemaorg.generator.model.handler.ModelHandlerTestUtils.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubDataTypeModelHandlerImplTest {

    @InjectMocks
    private SubDataTypeModelHandlerImpl modelHandler;

    @ParameterizedTest
    @MethodSource
    void supports(List<String> types, List<SubClassOf> subClasses, boolean expected) {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getTypes()).thenReturn(types);
        when(graphItem.getSubClassOf()).thenReturn(subClasses);
        boolean result = modelHandler.supports(graphItem);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void handle() {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getId()).thenReturn("schema:XPathType");
        when(graphItem.getLabel()).thenReturn(label("XPathType"));
        when(graphItem.getComment()).thenReturn(comment("This is XPathType"));
        when(graphItem.getPartOf()).thenReturn(List.of(partOf("https://pending.schema.org")));
        when(graphItem.getSource()).thenReturn(List.of(source("https://github.com/schemaorg/schemaorg/issues/1672")));
        when(graphItem.getSubClassOf()).thenReturn(List.of(subClassOf("schema:Text")));

        modelHandler.handle(schemaDefinitions, graphItem);
        Assertions.assertThat(schemaDefinitions).isNotEmpty().containsOnlyKeys("schema:Text", "schema:XPathType");

        Assertions.assertThat(schemaDefinitions.get("schema:Text"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:Text", null, null, null,
                        Collections.emptySet(), Collections.emptySet(),
                        Collections.emptyList(),
                        false, Collections.emptyList(),
                        Collections.emptyList(), Collections.emptyList()
                );

        Assertions.assertThat(schemaDefinitions.get("schema:XPathType"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:XPathType", "java.lang.String", "XPathType", "This is XPathType",
                        Collections.emptySet(), Collections.emptySet(),
                        List.of(schemaDefinitions.get("schema:Text")),
                        false, Collections.emptyList(),
                        List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/1672")
                );
    }

    private static Stream<Arguments> supports() {
        return Stream.of(
                Arguments.of(List.of("rdfs:Class"), List.of(subClassOf("schema:Text")), true),
                Arguments.of(List.of("rdfs:Class"), null, false),
                Arguments.of(List.of("rdfs:Class", "schema:DataType"), List.of(subClassOf("schema:Text")), false),
                Arguments.of(List.of("rdfs:Class"), List.of(subClassOf("schema:OtherType")), false),
                Arguments.of(List.of("schema:OtherType"), List.of(subClassOf("schema:Text")), false)
        );
    }
}