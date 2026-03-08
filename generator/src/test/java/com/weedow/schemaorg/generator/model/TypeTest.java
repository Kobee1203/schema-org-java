package com.weedow.schemaorg.generator.model;

import com.weedow.schemaorg.generator.core.GeneratorOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TypeTest {

    private Type type;
    private Property prop1;
    private Property prop2;
    private Property prop3;

    @BeforeEach
    void setUp() {
        type = new Type("schema:Thing");
        
        prop1 = mock(Property.class);
        when(prop1.getId()).thenReturn("schema:name");
        
        prop2 = mock(Property.class);
        when(prop2.getId()).thenReturn("schema:description");
        
        prop3 = mock(Property.class);
        when(prop3.getId()).thenReturn("schema:url");

        type.addProperty(prop1);
        type.addProperty(prop2);
        type.addProperty(prop3);
    }

    @Test
    void filterProperties_EXCLUDE_with_prefix() {
        type.filterProperties(GeneratorOptions.FilterMode.EXCLUDE, List.of("schema:name", "schema:url"));
        assertThat(type.getProperties()).containsExactly(prop2);
    }

    @Test
    void filterProperties_EXCLUDE_without_prefix() {
        type.filterProperties(GeneratorOptions.FilterMode.EXCLUDE, List.of("name", "url"));
        assertThat(type.getProperties()).containsExactly(prop2);
    }

    @Test
    void filterProperties_INCLUDE_with_prefix() {
        type.filterProperties(GeneratorOptions.FilterMode.INCLUDE, List.of("schema:name", "schema:url"));
        assertThat(type.getProperties()).containsExactly(prop1, prop3);
    }

    @Test
    void filterProperties_INCLUDE_without_prefix() {
        type.filterProperties(GeneratorOptions.FilterMode.INCLUDE, List.of("name", "url"));
        assertThat(type.getProperties()).containsExactly(prop1, prop3);
    }

    @Test
    void filterProperties_empty_list_should_not_change_anything() {
        type.filterProperties(GeneratorOptions.FilterMode.EXCLUDE, List.of());
        assertThat(type.getProperties()).hasSize(3).containsExactly(prop2, prop1, prop3); // Ordered by ID: description, name, url
    }

    @Test
    void filterProperties_null_list_should_not_change_anything() {
        type.filterProperties(GeneratorOptions.FilterMode.EXCLUDE, null);
        assertThat(type.getProperties()).hasSize(3).containsExactly(prop2, prop1, prop3);
    }

    @ParameterizedTest
    @MethodSource("provideWildcardTestData")
    void filterProperties_with_wildcards(GeneratorOptions.FilterMode mode, List<String> patterns, List<String> expectedIds) {
        type.filterProperties(mode, patterns);
        assertThat(type.getProperties())
                .extracting(Property::getId)
                .containsExactlyInAnyOrderElementsOf(expectedIds);
    }

    private static Stream<Arguments> provideWildcardTestData() {
        return Stream.of(
                // EXCLUDE mode
                Arguments.of(GeneratorOptions.FilterMode.EXCLUDE, List.of("schema:nam*"), List.of("schema:description", "schema:url")),
                Arguments.of(GeneratorOptions.FilterMode.EXCLUDE, List.of("*url"), List.of("schema:name", "schema:description")),
                Arguments.of(GeneratorOptions.FilterMode.EXCLUDE, List.of("schema:*me"), List.of("schema:description", "schema:url")),
                Arguments.of(GeneratorOptions.FilterMode.EXCLUDE, List.of("*n*e*"), List.of("schema:description", "schema:url")),
                Arguments.of(GeneratorOptions.FilterMode.EXCLUDE, List.of("*"), List.of()),
                Arguments.of(GeneratorOptions.FilterMode.EXCLUDE, List.of("nam*"), List.of("schema:name", "schema:description", "schema:url")),

                // INCLUDE mode
                Arguments.of(GeneratorOptions.FilterMode.INCLUDE, List.of("schema:nam*"), List.of("schema:name")),
                Arguments.of(GeneratorOptions.FilterMode.INCLUDE, List.of("*url"), List.of("schema:url")),
                Arguments.of(GeneratorOptions.FilterMode.INCLUDE, List.of("schema:*me"), List.of("schema:name")),
                Arguments.of(GeneratorOptions.FilterMode.INCLUDE, List.of("*n*e*"), List.of("schema:name")),
                Arguments.of(GeneratorOptions.FilterMode.INCLUDE, List.of("*"), List.of("schema:name", "schema:description", "schema:url")),
                Arguments.of(GeneratorOptions.FilterMode.INCLUDE, List.of("nam*"), List.of())
        );
    }

}
