package com.weedow.schemaorg.generator.model.utils;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
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
class ModelUtilsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "schema:DataType, null",
            "schema:Boolean, java.lang.Boolean",
            "schema:Text, java.lang.String",
            "schema:URL, java.net.URL",
            "schema:Number, java.lang.Number",
            "schema:Integer, java.lang.Integer",
            "schema:Float, java.lang.Float",
            "schema:Date, java.time.LocalDate",
            "schema:Time, java.time.LocalTime",
            "schema:DateTime, java.time.LocalDateTime",
            "unknown, null"
    }, nullValues = "null")
    void getJavaType(String typeId, String expected) {
        Assertions.assertThat(ModelUtils.getJavaType(typeId, null)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "schema:DataType, true",
            "schema:Boolean, true",
            "schema:Text, true",
            "schema:URL, true",
            "schema:Number, true",
            "schema:Integer, true",
            "schema:Float, true",
            "schema:Date, true",
            "schema:Time, true",
            "schema:DateTime, true",
            "unknown, false"
    }, nullValues = "null")
    void isDataType(String typeId, boolean expected) {
        Assertions.assertThat(ModelUtils.isDataType(typeId)).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource
    void isSubDataType(List<Type> parents, boolean expected) {
        Type type = mock(Type.class);
        when(type.getParents()).thenReturn(parents);
        Assertions.assertThat(ModelUtils.isSubDataType(type)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "true, true",
            "false, false"
    }, nullValues = "null")
    void isEnumeration(boolean enumerationType, boolean expected) {
        Type type = mock(Type.class);
        when(type.isEnumerationType()).thenReturn(enumerationType);

        Assertions.assertThat(ModelUtils.isEnumeration(type)).isEqualTo(expected);
    }

    @Test
    void getPropertyTypes() {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getRangeIncludes()).thenReturn(List.of(rangeInclude("id1"), rangeInclude("id2")));

        Assertions.assertThat(ModelUtils.getPropertyTypes(schemaDefinitions, graphItem))
                .extracting("id")
                .containsExactly("id1", "id2");
    }

    @Test
    void getPropertyTypes_when_rangeIncludes_is_null() {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getRangeIncludes()).thenReturn(null);

        Assertions.assertThat(ModelUtils.getPropertyTypes(schemaDefinitions, graphItem)).isEmpty();
    }

    @Test
    void getType() {
        Map<String, Type> schemaDefinitions = new HashMap<>();
        Type type = ModelUtils.getType(schemaDefinitions, "schema:Example");

        Assertions.assertThat(type).isNotNull().extracting("id").isEqualTo("schema:Example");
        Assertions.assertThat(ModelUtils.getType(schemaDefinitions, "schema:Example")).isSameAs(type);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "rdfs:Class, schema:Class",
            "schema:Class, schema:Class",
            "schema:Text, schema:Text",
            "unknown, unknown"
    }, nullValues = "null")
    void getTypeId(String typeId, String expected) {
        Assertions.assertThat(ModelUtils.getTypeId(typeId)).isEqualTo(expected);
    }

    @Test
    void getSource() {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getSource()).thenReturn(List.of(source("source1"), source("source2")));

        Assertions.assertThat(ModelUtils.getSource(graphItem)).containsExactly("source1", "source2");
    }

    @Test
    void getSource_when_source_is_null() {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getSource()).thenReturn(null);

        Assertions.assertThat(ModelUtils.getSource(graphItem)).isEmpty();
    }

    @Test
    void getPartOf() {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getPartOf()).thenReturn(List.of(partOf("partOf1"), partOf("partOf2")));

        Assertions.assertThat(ModelUtils.getPartOf(graphItem)).containsExactly("partOf1", "partOf2");
    }

    @Test
    void getPartOf_when_partOf_is_null() {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getPartOf()).thenReturn(null);

        Assertions.assertThat(ModelUtils.getPartOf(graphItem)).isEmpty();
    }

    @Test
    void getSubPropertyOf() {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getSubPropertyOf()).thenReturn(List.of(subPropertyOf("subPropertyOf1"), subPropertyOf("subPropertyOf2")));

        Assertions.assertThat(ModelUtils.getSubPropertyOf(graphItem)).containsExactly("subPropertyOf1", "subPropertyOf2");
    }

    @Test
    void getSubPropertyOf_when_partOf_is_null() {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getSubPropertyOf()).thenReturn(null);

        Assertions.assertThat(ModelUtils.getSubPropertyOf(graphItem)).isEmpty();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "myField, myField",
            "abstract, abstract_",
            "'', ''",
            "null, null"
    }, nullValues = "null")
    void getFieldName(String name, String expected) {
        Assertions.assertThat(ModelUtils.getFieldName(name)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, pre, '', prenull",
            "null, '', Suf, nullSuf",
            "null, pre, Suf, prenullSuf",
            "'', pre, '', pre",
            "'', '', Suf, Suf",
            "'', pre, Suf, preSuf",
            "a, pre, '', prea",
            "a, '', Suf, aSuf",
            "a, pre, Suf, preaSuf",
            "mytext, pre, '', preMytext",
            "mytext, '', 'Suf', mytextSuf",
            "mytext, pre, Suf, preMytextSuf",
            "MYTEXT, pre, '', preMYTEXT",
            "MYTEXT, '', Suf, MYTEXTSuf",
            "MYTEXT, pre, Suf, preMYTEXTSuf",
            "myText, pre, '', preMyText",
            "myText, '', Suf, myTextSuf",
            "myText, pre, Suf, preMyTextSuf",
    }, nullValues = "null")
    void getMethodName(String value, String prefix, String suffix, String expected) {
        Assertions.assertThat(ModelUtils.getMethodName(value, prefix, suffix)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void getSplitDescription(String description, String[] expected) {
        Assertions.assertThat(ModelUtils.getSplitDescription(description)).isEqualTo(expected);
    }

    private static Stream<Arguments> isSubDataType() {
        return Stream.of(
                Arguments.of(List.of(new Type("schema:DataType")), true),
                Arguments.of(List.of(new Type("schema:Boolean")), true),
                Arguments.of(List.of(new Type("schema:Text")), true),
                Arguments.of(List.of(new Type("schema:URL")), true),
                Arguments.of(List.of(new Type("schema:Number")), true),
                Arguments.of(List.of(new Type("schema:Integer")), true),
                Arguments.of(List.of(new Type("schema:Float")), true),
                Arguments.of(List.of(new Type("schema:Date")), true),
                Arguments.of(List.of(new Type("schema:Time")), true),
                Arguments.of(List.of(new Type("schema:DateTime")), true),
                Arguments.of(List.of(new Type("unknown")), false),
                Arguments.of(Collections.emptyList(), false),
                Arguments.of(List.of(new Type("schema:Boolean"), new Type("schema:Text")), false)
        );
    }

    private static Stream<Arguments> getSplitDescription() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of("", new String[]{""}),
                Arguments.of(
                        "This is my description.\nHere is the content\\nto read.",
                        new String[]{"This is my description.", "Here is the content<br/>to read."}
                )
        );
    }
}