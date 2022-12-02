package com.weedow.schemaorg.generator.model.utils;

import com.weedow.schemaorg.generator.model.Type;
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
    @MethodSource
    void isEnumeration(boolean enumerationType, List<String> enumerationMembers, boolean expected) {
        Type type = mock(Type.class);
        when(type.isEnumerationType()).thenReturn(enumerationType);
        if (enumerationType) {
            // Method not called if enumerationType is false
            when(type.getEnumerationMembers()).thenReturn(enumerationMembers);
        }

        Assertions.assertThat(ModelUtils.isEnumeration(type)).isEqualTo(expected);
    }

    @Test
    void getPropertyTypes() {
        // TODO
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
        // TODO
    }

    @Test
    void getPartOf() {
        // TODO
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, null",
            "'', ''",
            "a, a",
            "mytext, Mytext",
            "MYTEXT, MYTEXT",
            "myText, MyText",
    }, nullValues = "null")
    void capitalize(String value, String expected) {
        Assertions.assertThat(ModelUtils.capitalize(value)).isEqualTo(expected);
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

    private static Stream<Arguments> isEnumeration() {
        return Stream.of(
                Arguments.of(true, List.of("enum"), true),
                Arguments.of(true, Collections.emptyList(), false),
                Arguments.of(false, List.of("enum"), false)
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