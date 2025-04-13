package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Property;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
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

        ParserOptions options = mock(ParserOptions.class);

        boolean result = modelHandler.supports(graphItem, options);

        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void handle(String typeName1, boolean usedJavaTypes1, String typeName2, boolean usedJavaTypes2) {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = createGraphItem();

        ParserOptions options = mock(ParserOptions.class);
        when(options.isUsedJavaTypes()).thenReturn(true);

        modelHandler.handle(schemaDefinitions, graphItem, options);

        Type type = schemaDefinitions.get("schema:Text");
        type.setUsedJavaType(usedJavaTypes1);
        type.setName(typeName1);
        Type type2 = schemaDefinitions.get("schema:PronounceableText");
        type.setUsedJavaType(usedJavaTypes2);
        type2.setName(typeName2);
        Type type3 = schemaDefinitions.get("schema:OtherType");
        type3.setUsedJavaType(false);
        type3.setName("OtherType");

        Assertions.assertThat(schemaDefinitions).isNotEmpty().containsOnlyKeys("schema:Text", "schema:PronounceableText", "schema:OtherType", "schema:MyType");
        Assertions.assertThat(schemaDefinitions.get("schema:Text"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:Text", null, typeName1, null,
                        Collections.emptySet(), Collections.emptySet(),
                        Collections.emptyList(),
                        false, Collections.emptyList(),
                        Collections.emptyList(), Collections.emptyList()
                );
        Assertions.assertThat(schemaDefinitions.get("schema:PronounceableText"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:PronounceableText", null, typeName2, null,
                        Collections.emptySet(), Collections.emptySet(),
                        Collections.emptyList(),
                        false, Collections.emptyList(),
                        Collections.emptyList(), Collections.emptyList()
                );
        Assertions.assertThat(schemaDefinitions.get("schema:OtherType"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:OtherType", null, "OtherType", null,
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

        Set<Property> properties = myType.getProperties();
        Assertions.assertThat(properties)
                .extracting("id", "field.name", "field.fieldName", "types")
                .containsExactly(
                        Tuple.tuple("schema:MyProperty", "myProperty", "myProperty", List.of(schemaDefinitions.get("schema:Text"), schemaDefinitions.get("schema:PronounceableText"), schemaDefinitions.get("schema:OtherType")))
                );
        Assertions.assertThat(properties)
                .extracting("accessor")
                .extracting("name", "fieldName", "getterMethod", "description", "partOf", "source", "fieldTypeLinks", "returnFieldType", "cast")
                .containsExactly(
                        Tuple.tuple("myProperty", "myProperty", "getMyPropertyList", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), "{@link " + typeName1 + "} or {@link " + typeName2 + "} or {@link OtherType}", "<T> T", "(T)")
                );
        Assertions.assertThat(properties)
                .flatExtracting("mutators")
                .extracting("name", "fieldName", "setterMethod", "description", "partOf", "source", "paramType", "paramValue")
                .containsExactly(
                        Tuple.tuple("myProperty", "myProperty", "setMyProperty", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), typeName1, "myProperty"),
                        Tuple.tuple("myProperty", "myProperty", "setMyProperty", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), typeName2, "myProperty"),
                        Tuple.tuple("myProperty", "myProperty", "setMyProperty", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), "OtherType", "myProperty")
                );

        Set<Property> allProperties = myType.getAllProperties();
        Assertions.assertThat(allProperties)
                .extracting("id", "field.name", "field.fieldName", "types")
                .containsExactly(
                        Tuple.tuple("schema:MyProperty", "myProperty", "myProperty", List.of(schemaDefinitions.get("schema:Text"), schemaDefinitions.get("schema:PronounceableText"), schemaDefinitions.get("schema:OtherType")))
                );
        Assertions.assertThat(allProperties)
                .extracting("accessor")
                .extracting("name", "fieldName", "getterMethod", "description", "partOf", "source", "fieldTypeLinks", "returnFieldType", "cast")
                .containsExactly(
                        Tuple.tuple("myProperty", "myProperty", "getMyPropertyList", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), "{@link " + typeName1 + "} or {@link " + typeName2 + "} or {@link OtherType}", "<T> T", "(T)")
                );
        Assertions.assertThat(allProperties)
                .flatExtracting("mutators")
                .extracting("name", "fieldName", "setterMethod", "description", "partOf", "source", "paramType", "paramValue")
                .containsExactly(
                        Tuple.tuple("myProperty", "myProperty", "setMyProperty", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), typeName1, "myProperty"),
                        Tuple.tuple("myProperty", "myProperty", "setMyProperty", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), typeName2, "myProperty"),
                        Tuple.tuple("myProperty", "myProperty", "setMyProperty", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), "OtherType", "myProperty")
                );
    }

    @Test
    void handle_with_java_types_disabled() {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = createGraphItem();

        ParserOptions options = mock(ParserOptions.class);
        when(options.isUsedJavaTypes()).thenReturn(false);

        modelHandler.handle(schemaDefinitions, graphItem, options);

        Assertions.assertThat(schemaDefinitions).isNotEmpty().containsOnlyKeys("schema:Text", "schema:PronounceableText", "schema:OtherType", "schema:MyType");
        Assertions.assertThat(schemaDefinitions.get("schema:Text"))
                .extracting("id", "javaType")
                .containsExactly("schema:Text", null);
        Assertions.assertThat(schemaDefinitions.get("schema:PronounceableText"))
                .extracting("id", "javaType")
                .containsExactly("schema:PronounceableText", null);
        Assertions.assertThat(schemaDefinitions.get("schema:OtherType"))
                .extracting("id", "javaType")
                .containsExactly("schema:OtherType", null);

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

        Set<Property> properties = myType.getProperties();
        Assertions.assertThat(properties)
                .extracting("id", "field.name", "field.fieldName", "types")
                .containsExactly(
                        Tuple.tuple("schema:MyProperty", "myProperty", "myProperty", List.of(schemaDefinitions.get("schema:Text"), schemaDefinitions.get("schema:PronounceableText"), schemaDefinitions.get("schema:OtherType")))
                );
        Assertions.assertThat(properties)
                .extracting("accessor")
                .extracting("name", "fieldName", "getterMethod", "description", "partOf", "source", "fieldTypeLinks", "returnFieldType", "cast")
                .containsExactly(
                        Tuple.tuple("myProperty", "myProperty", "getMyPropertyList", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), "{@link null} or {@link null} or {@link null}", "<T> T", "(T)")
                );
        Assertions.assertThat(properties)
                .flatExtracting("mutators")
                .extracting("name", "fieldName", "setterMethod", "description", "partOf", "source", "paramType", "paramValue")
                .containsExactly(
                        Tuple.tuple("myProperty", "myProperty", "setMyProperty", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), null, "myProperty"),
                        Tuple.tuple("myProperty", "myProperty", "setMyProperty", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), null, "myProperty"),
                        Tuple.tuple("myProperty", "myProperty", "setMyProperty", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), null, "myProperty")
                );

        Set<Property> allProperties = myType.getAllProperties();
        Assertions.assertThat(allProperties)
                .extracting("id", "field.name", "field.fieldName", "types")
                .containsExactly(
                        Tuple.tuple("schema:MyProperty", "myProperty", "myProperty", List.of(schemaDefinitions.get("schema:Text"), schemaDefinitions.get("schema:PronounceableText"), schemaDefinitions.get("schema:OtherType")))
                );
        Assertions.assertThat(allProperties)
                .extracting("accessor")
                .extracting("name", "fieldName", "getterMethod", "description", "partOf", "source", "fieldTypeLinks", "returnFieldType", "cast")
                .containsExactly(
                        Tuple.tuple("myProperty", "myProperty", "getMyPropertyList", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), "{@link null} or {@link null} or {@link null}", "<T> T", "(T)")
                );
        Assertions.assertThat(allProperties)
                .flatExtracting("mutators")
                .extracting("name", "fieldName", "setterMethod", "description", "partOf", "source", "paramType", "paramValue")
                .containsExactly(
                        Tuple.tuple("myProperty", "myProperty", "setMyProperty", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), null, "myProperty"),
                        Tuple.tuple("myProperty", "myProperty", "setMyProperty", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), null, "myProperty"),
                        Tuple.tuple("myProperty", "myProperty", "setMyProperty", "This is my Property", List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/2373"), null, "myProperty")
                );
    }

    @Test
    void handle_with_deprecated_type() {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getRangeIncludes()).thenReturn(Collections.emptyList());
        when(graphItem.getSupersededBy()).thenReturn(supersededBy("NewProperty"));

        ParserOptions options = mock(ParserOptions.class);

        modelHandler.handle(schemaDefinitions, graphItem, options);

        Assertions.assertThat(schemaDefinitions).isEmpty();
    }

    private static GraphItem createGraphItem() {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getId()).thenReturn("schema:MyProperty");
        when(graphItem.getRangeIncludes()).thenReturn(List.of(rangeInclude("schema:Text"), rangeInclude("schema:PronounceableText"), rangeInclude("schema:OtherType")));
        when(graphItem.getLabel()).thenReturn(label("en", "myProperty"));
        when(graphItem.getComment()).thenReturn(comment("en", "This is my Property"));
        when(graphItem.getPartOf()).thenReturn(List.of(partOf("https://pending.schema.org")));
        when(graphItem.getSource()).thenReturn(List.of(source("https://github.com/schemaorg/schemaorg/issues/2373")));
        when(graphItem.getDomainIncludes()).thenReturn(List.of(domainInclude("schema:MyType")));
        return graphItem;
    }

    private static Stream<Arguments> supports() {
        return Stream.of(
                Arguments.of(List.of("rdf:Property"), true),
                Arguments.of(List.of("rdf:Property", "rdfs:Class"), true),
                Arguments.of(List.of("rdfs:Class"), false)
        );
    }

    private static Stream<Arguments> handle() {
        return Stream.of(
                Arguments.of("Text", false, "PronounceableText", false),
                Arguments.of("java.lang.string", true, "java.lang.string", true)
        );
    }
}