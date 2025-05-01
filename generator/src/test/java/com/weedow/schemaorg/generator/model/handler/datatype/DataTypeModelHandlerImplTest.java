package com.weedow.schemaorg.generator.model.handler.datatype;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.assertj.core.api.Assertions;
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
class DataTypeModelHandlerImplTest {

    @InjectMocks
    private DataTypeModelHandlerImpl modelHandler;

    @ParameterizedTest
    @MethodSource
    void supports(List<String> types, boolean expected) {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getTypes()).thenReturn(types);

        ParserOptions options = mock(ParserOptions.class);

        boolean result = modelHandler.supports(graphItem, options);

        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void handle(String id, String label, String expectedJavaType) {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getId()).thenReturn(id);
        when(graphItem.getLabel()).thenReturn(label("en", label));
        when(graphItem.getComment()).thenReturn(comment("en", "This is " + label));
        when(graphItem.getPartOf()).thenReturn(List.of(partOf("https://pending.schema.org")));
        when(graphItem.getSource()).thenReturn(List.of(source("https://github.com/schemaorg/schemaorg/issues/2373")));
        when(graphItem.getSubClassOf()).thenReturn(List.of(subClassOf("rdfs:Class"), subClassOf("schema:Parent")));

        ParserOptions options = mock(ParserOptions.class);
        when(options.getCustomDataTypes()).thenReturn(Map.of("schema:DateTime", "java.time.ZonedDateTime"));

        modelHandler.handle(schemaDefinitions, graphItem, options);

        Assertions.assertThat(schemaDefinitions).isNotEmpty().containsOnlyKeys(id, "schema:Parent", "schema:DataType");
        Assertions.assertThat(schemaDefinitions.get(id))
                .extracting(
                        "id", "javaType", "usedJavaType", "name", "description",
                        "properties", "allProperties",
                        "parents", "baseParent",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        id, expectedJavaType, false, label, "This is " + label,
                        Collections.emptySet(), Collections.emptySet(),
                        List.of(schemaDefinitions.get("schema:Parent"), schemaDefinitions.get("schema:DataType")), null,
                        false, Collections.emptyList(),
                        List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373")
                );
        Assertions.assertThat(schemaDefinitions.get("schema:Parent"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents", "baseParent",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:Parent", null, null, null,
                        Collections.emptySet(), Collections.emptySet(),
                        Collections.emptyList(), null,
                        false, Collections.emptyList(),
                        Collections.emptyList(), Collections.emptyList()
                );
        Assertions.assertThat(schemaDefinitions.get("schema:DataType"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:DataType", null, null, null,
                        Collections.emptySet(), Collections.emptySet(),
                        Collections.emptyList(),
                        false, Collections.emptyList(),
                        Collections.emptyList(), Collections.emptyList()
                );
    }

    @ParameterizedTest
    @MethodSource
    void handle_with_java_types(String id, String label, String expectedJavaType) {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getId()).thenReturn(id);
        when(graphItem.getLabel()).thenReturn(label("en", label));
        when(graphItem.getComment()).thenReturn(comment("en", "This is " + label));
        when(graphItem.getPartOf()).thenReturn(List.of(partOf("https://pending.schema.org")));
        when(graphItem.getSource()).thenReturn(List.of(source("https://github.com/schemaorg/schemaorg/issues/2373")));
        when(graphItem.getSubClassOf()).thenReturn(List.of(subClassOf("rdfs:Class"), subClassOf("schema:Parent")));

        ParserOptions options = mock(ParserOptions.class);
        when(options.isUsedJavaTypes()).thenReturn(true);
        when(options.getCustomDataTypes()).thenReturn(Map.of("schema:DateTime", "java.time.ZonedDateTime"));

        modelHandler.handle(schemaDefinitions, graphItem, options);

        Assertions.assertThat(schemaDefinitions).isNotEmpty().containsOnlyKeys(id, "schema:Parent");
        Assertions.assertThat(schemaDefinitions.get(id))
                .extracting(
                        "id", "javaType", "usedJavaType", "name", "description",
                        "properties", "allProperties",
                        "parents", "baseParent",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        id, null, true, expectedJavaType, "This is " + label,
                        Collections.emptySet(), Collections.emptySet(),
                        List.of(schemaDefinitions.get("schema:Parent")), null,
                        false, Collections.emptyList(),
                        List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373")
                );
        Assertions.assertThat(schemaDefinitions.get("schema:Parent"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents", "baseParent",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:Parent", null, null, null,
                        Collections.emptySet(), Collections.emptySet(),
                        Collections.emptyList(), null,
                        false, Collections.emptyList(),
                        Collections.emptyList(), Collections.emptyList()
                );
    }

    private static Stream<Arguments> supports() {
        return Stream.of(
                Arguments.of(List.of("rdfs:Class", "schema:DataType"), true),
                Arguments.of(List.of("rdfs:Class", "rdfs:OtherType"), false),
                Arguments.of(List.of("rdfs:OtherClass", "rdfs:DataType"), false)
        );
    }

    private static Stream<Arguments> handle() {
        return Stream.of(
                Arguments.of("schema:MyType", "MyType", null),
                Arguments.of("schema:Text", "Text", "java.lang.String"),
                Arguments.of("schema:DateTime", "DateTime", "java.time.ZonedDateTime")
        );
    }

    private static Stream<Arguments> handle_with_java_types() {
        return Stream.of(
                Arguments.of("schema:MyType", "MyType", null),
                Arguments.of("schema:Text", "Text", "java.lang.String"),
                Arguments.of("schema:DateTime", "DateTime", "java.time.ZonedDateTime")
        );
    }
}