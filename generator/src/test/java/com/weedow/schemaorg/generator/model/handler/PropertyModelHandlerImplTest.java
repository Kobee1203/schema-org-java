package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
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
class PropertyModelHandlerImplTest {

    @InjectMocks
    private PropertyModelHandlerImpl modelHandler;

    @ParameterizedTest
    @MethodSource
    void supports(List<String> types, boolean expected) {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getTypes()).thenReturn(types);
        boolean result = modelHandler.supports(graphItem);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void handle() {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getId()).thenReturn("schema:MyProperty");
        when(graphItem.getRangeIncludes()).thenReturn(List.of(rangeInclude("schema:Text")));
        when(graphItem.getLabel()).thenReturn(label("MyProperty"));
        when(graphItem.getComment()).thenReturn(comment("This is my Property"));
        when(graphItem.getPartOf()).thenReturn(List.of(partOf("https://pending.schema.org")));
        when(graphItem.getSource()).thenReturn(List.of(source("https://github.com/schemaorg/schemaorg/issues/2373")));
        when(graphItem.getDomainIncludes()).thenReturn(List.of(domainInclude("schema:MyType")));

        modelHandler.handle(schemaDefinitions, graphItem);
        Assertions.assertThat(schemaDefinitions).isNotEmpty().containsOnlyKeys("schema:Text", "schema:MyType");

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

        Type myType = schemaDefinitions.get("schema:MyType");
        Assertions.assertThat(myType)
                .extracting(
                        "id", "javaType", "name", "description",
                        /*"properties", "allProperties",*/
                        "parents",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:MyType", null, null, null,
                        /*Collections.emptySet(), Collections.emptySet(),*/
                        Collections.emptyList(),
                        false, Collections.emptyList(),
                        Collections.emptyList(), Collections.emptyList()
                );
        Assertions.assertThat(myType.getProperties())
                .extracting(
                        "id", "name", "fieldName", "description", "types",
                        "partOf", "source"
                )
                .containsExactly(
                        Tuple.tuple(
                                "schema:MyProperty", "MyProperty", "fMyProperty", "This is my Property", List.of(schemaDefinitions.get("schema:Text")),
                                List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373")
                        )
                );
        Assertions.assertThat(myType.getAllProperties())
                .extracting(
                        "id", "name", "fieldName", "description", "types",
                        "partOf", "source"
                )
                .containsExactly(
                        Tuple.tuple(
                                "schema:MyProperty", "MyProperty", "fMyProperty", "This is my Property", List.of(schemaDefinitions.get("schema:Text")),
                                List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373")
                        )
                );
    }

    @Test
    void handle_with_deprecated_type() {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getRangeIncludes()).thenReturn(Collections.emptyList());
        when(graphItem.getSupersededBy()).thenReturn(supersededBy("NewProperty"));

        modelHandler.handle(schemaDefinitions, graphItem);
        Assertions.assertThat(schemaDefinitions).isEmpty();
    }

    private static Stream<Arguments> supports() {
        return Stream.of(
                Arguments.of(List.of("rdf:Property"), true),
                Arguments.of(List.of("rdf:Property", "rdfs:Class"), true),
                Arguments.of(List.of("rdfs:Class"), false)
        );
    }
}