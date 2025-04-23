package com.weedow.schemaorg.generator.model.handler.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
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

import static com.weedow.schemaorg.generator.model.handler.ModelHandlerTestUtils.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseDataTypeModelHandlerImplTest {

    @InjectMocks
    private BaseDataTypeModelHandlerImpl modelHandler;

    @ParameterizedTest
    @MethodSource
    void supports(String id, ParserOptions options, boolean expected) {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getId()).thenReturn(id);

        boolean result = modelHandler.supports(graphItem, options);

        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void handle() {
        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getId()).thenReturn("schema:MyType");
        when(graphItem.getLabel()).thenReturn(label("en", "MyType"));
        when(graphItem.getComment()).thenReturn(comment("en", "This is my Type"));
        when(graphItem.getPartOf()).thenReturn(List.of(partOf("https://pending.schema.org")));
        when(graphItem.getSource()).thenReturn(List.of(source("https://github.com/schemaorg/schemaorg/issues/2373")));
        when(graphItem.getSubClassOf()).thenReturn(List.of(subClassOf("rdfs:Class"), subClassOf("schema:Parent")));

        ParserOptions options = mockParserOptions(null);

        modelHandler.handle(schemaDefinitions, graphItem, options);

        Assertions.assertThat(schemaDefinitions).isNotEmpty().containsOnlyKeys("schema:MyType", "schema:Parent");
        Assertions.assertThat(schemaDefinitions.get("schema:MyType"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents",
                        "baseParent.id", "baseParent.interfaceClass", "baseParent.implementationClass",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:MyType", null, "MyType", "This is my Type",
                        Collections.emptySet(), Collections.emptySet(),
                        List.of(schemaDefinitions.get("schema:Parent")),
                        "java:JsonLdDataType", JsonLdDataType.class, null,
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
                Arguments.of("schema:DataType", mockParserOptions(false), true),
                Arguments.of("schema:DataType", mockParserOptions(true), false),
                Arguments.of(null, mockParserOptions(null), false),
                Arguments.of("rdfs:OtherClass", mockParserOptions(null), false)
        );
    }

    private static ParserOptions mockParserOptions(Boolean usedJavaTypes) {
        ParserOptions options = mock(ParserOptions.class);
        if (usedJavaTypes != null) {
            when(options.isUsedJavaTypes()).thenReturn(usedJavaTypes);
        }
        return options;
    }
}