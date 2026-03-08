package com.weedow.schemaorg.generator.core;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GeneratorOptionsTest {

    @ParameterizedTest
    @MethodSource("provideFilterOptionTestData")
    void testParseFilterOption(String input, String expectedTypeName, GeneratorOptions.FilterMode expectedMode, List<String> expectedProperties) {
        GeneratorOptions.FilterOption result = GeneratorOptions.FilterOption.parse(input);

        assertThat(result.getTypeName()).isEqualTo(expectedTypeName);
        assertThat(result.getMode()).isEqualTo(expectedMode);
        assertThat(result.getProperties()).containsExactlyElementsOf(expectedProperties);
    }

    private static Stream<Arguments> provideFilterOptionTestData() {
        return Stream.of(
                // Full format: typeName:mode:prop1,prop2
                Arguments.of("Thing:INCLUDE:name,url", "Thing", GeneratorOptions.FilterMode.INCLUDE, List.of("name", "url")),
                Arguments.of("Thing:EXCLUDE:description", "Thing", GeneratorOptions.FilterMode.EXCLUDE, List.of("description")),
                
                // Format: typeName:prop1,prop2 (default mode EXCLUDE)
                Arguments.of("Thing:name,url", "Thing", GeneratorOptions.FilterMode.EXCLUDE, List.of("name", "url")),
                
                // Format: typeName:mode
                Arguments.of("Thing:INCLUDE", "Thing", GeneratorOptions.FilterMode.INCLUDE, List.of()),
                Arguments.of("Thing:EXCLUDE", "Thing", GeneratorOptions.FilterMode.EXCLUDE, List.of()),
                
                // Format: typeName
                Arguments.of("Thing", "Thing", GeneratorOptions.FilterMode.EXCLUDE, List.of()),
                
                // Format: typeName:* (wildcard property)
                Arguments.of("Thing:*", "Thing", GeneratorOptions.FilterMode.EXCLUDE, List.of("*")),
                
                // Wildcard in properties list
                Arguments.of("Thing:INCLUDE:name,*description*,schema:url", "Thing", GeneratorOptions.FilterMode.INCLUDE, List.of("name", "*description*", "schema:url")),
                
                // Case insensitivity for mode
                Arguments.of("Thing:include:name", "Thing", GeneratorOptions.FilterMode.INCLUDE, List.of("name")),
                Arguments.of("Thing:Exclude:name", "Thing", GeneratorOptions.FilterMode.EXCLUDE, List.of("name"))
        );
    }
}
