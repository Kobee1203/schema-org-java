package com.weedow.schemaorg.generator.model.handler.datatype;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.SubClassOf;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import lombok.Builder;
import lombok.Value;
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
class SubDataTypeModelHandlerImplTest {

    @InjectMocks
    private SubDataTypeModelHandlerImpl modelHandler;

    @ParameterizedTest
    @MethodSource
    void supports(List<String> types, List<SubClassOf> subClasses, boolean expected) {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getTypes()).thenReturn(types);
        when(graphItem.getSubClassOf()).thenReturn(subClasses);

        ParserOptions options = mock(ParserOptions.class);

        boolean result = modelHandler.supports(graphItem, options);

        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void handle(boolean usedJavaTypes, String expectedJavaType, String expectedName) {
        ParserOptions options = mock(ParserOptions.class);
        when(options.isUsedJavaTypes()).thenReturn(usedJavaTypes);

        Map<String, Type> schemaDefinitions = new HashMap<>();

        String itemName = "XPathType";
        GraphItem graphItem = mockGraphItem(itemName);

        modelHandler.handle(schemaDefinitions, graphItem, options);

        ExpectedData expectedData = ExpectedData.builder()
                .itemName(itemName)
                .usedJavaTypes(usedJavaTypes)
                .expectedJavaType(expectedJavaType)
                .expectedName(expectedName)
                .build();
        verify(schemaDefinitions, expectedData);
    }

    @ParameterizedTest
    @MethodSource
    void handle_parent_item(
            boolean usedJavaTypes,
            boolean stringifiable,
            String itemName,
            String expectedJavaType,
            String expectedName,
            String expectedParentJavaType,
            String expectedParentName
    ) {
        ParserOptions options = mock(ParserOptions.class);
        when(options.isUsedJavaTypes()).thenReturn(usedJavaTypes);

        Map<String, Type> schemaDefinitions = new HashMap<>();

        GraphItem textGraphItem = mockTextGraphItem();
        GraphItem xPathTypeGraphItem = mockGraphItem(itemName);

        modelHandler.handle(schemaDefinitions, textGraphItem, options);
        modelHandler.handle(schemaDefinitions, xPathTypeGraphItem, options);

        ExpectedData expectedData = ExpectedData.builder()
                .itemName(itemName)
                .usedJavaTypes(usedJavaTypes)
                .stringifiable(stringifiable)
                .expectedJavaType(expectedJavaType)
                .expectedName(expectedName)
                .parentUsedJavaTypes(usedJavaTypes)
                .expectedParentJavaType(expectedParentJavaType)
                .expectedParentName(expectedParentName)
                .expectParentComment("This is Text")
                .build();
        verify(schemaDefinitions, expectedData);
    }

    @ParameterizedTest
    @MethodSource
    void handle_with_customDataTypes(boolean usedJavaTypes, String expectedJavaType, String expectedName) {
        ParserOptions options = mock(ParserOptions.class);
        when(options.isUsedJavaTypes()).thenReturn(usedJavaTypes);
        when(options.getCustomDataTypes()).thenReturn(Map.of(
                "schema:XPathType", "javax.xml.xpath.XPath",
                "schema:Text", "java.lang.CharSequence"
        ));

        Map<String, Type> schemaDefinitions = new HashMap<>();

        String itemName = "XPathType";
        GraphItem graphItem = mockGraphItem(itemName);

        modelHandler.handle(schemaDefinitions, graphItem, options);

        ExpectedData expectedData = ExpectedData.builder()
                .itemName(itemName)
                .usedJavaTypes(usedJavaTypes)
                .expectedJavaType(expectedJavaType)
                .expectedName(expectedName)
                .build();
        verify(schemaDefinitions, expectedData);
    }

    private void verify(Map<String, Type> schemaDefinitions, ExpectedData expectedData) {
        Assertions.assertThat(schemaDefinitions).isNotEmpty().containsOnlyKeys("schema:Text", "schema:" + expectedData.itemName);
        Assertions.assertThat(schemaDefinitions.get("schema:Text"))
                .extracting(
                        "id", "javaType", "usedJavaType", "name", "description",
                        "properties", "allProperties",
                        "parents", "baseParent",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:Text", expectedData.expectedParentJavaType, expectedData.parentUsedJavaTypes, expectedData.expectedParentName, expectedData.expectParentComment,
                        Collections.emptySet(), Collections.emptySet(),
                        Collections.emptyList(), null,
                        false, Collections.emptyList(),
                        Collections.emptyList(), Collections.emptyList()
                );

        Assertions.assertThat(schemaDefinitions.get("schema:" + expectedData.itemName))
                .extracting(
                        "id", "javaType", "usedJavaType", "stringifiable", "name", "description",
                        "properties", "allProperties",
                        "parents", "baseParent",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:" + expectedData.itemName, expectedData.expectedJavaType, expectedData.usedJavaTypes, expectedData.stringifiable, expectedData.expectedName, "This is " + expectedData.itemName,
                        Collections.emptySet(), Collections.emptySet(),
                        List.of(schemaDefinitions.get("schema:Text")), null,
                        false, Collections.emptyList(),
                        List.of("https://pending.schema.org"), List.of("https://github.com/schemaorg/schemaorg/issues/1672")
                );
    }

    private static GraphItem mockGraphItem(String itemName) {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getId()).thenReturn("schema:" + itemName);
        when(graphItem.getLabel()).thenReturn(label("en", itemName));
        when(graphItem.getComment()).thenReturn(comment("en", "This is " + itemName));
        when(graphItem.getPartOf()).thenReturn(List.of(partOf("https://pending.schema.org")));
        when(graphItem.getSource()).thenReturn(List.of(source("https://github.com/schemaorg/schemaorg/issues/1672")));
        when(graphItem.getSubClassOf()).thenReturn(List.of(subClassOf("schema:Text")));
        return graphItem;
    }

    private static GraphItem mockTextGraphItem() {
        GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getId()).thenReturn("schema:Text");
        when(graphItem.getLabel()).thenReturn(label("en", "Text"));
        when(graphItem.getComment()).thenReturn(comment("en", "This is Text"));
        return graphItem;
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

    private static Stream<Arguments> handle() {
        return Stream.of(
                Arguments.of(true, "java.lang.String", "java.lang.String"),
                Arguments.of(false, "java.lang.String", "XPathType")
        );
    }

    private static Stream<Arguments> handle_parent_item() {
        return Stream.of(
                Arguments.of(true, false, "XPathType", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String"),
                Arguments.of(true, true, "URL", "java.net.URL", "java.net.URL", "java.lang.String", "java.lang.String"),
                Arguments.of(false, false, "XPathType", "java.lang.String", "XPathType", "java.lang.String", "Text"),
                Arguments.of(false, true, "URL", "java.net.URL", "URL", "java.lang.String", "Text")
        );
    }

    private static Stream<Arguments> handle_with_customDataTypes() {
        return Stream.of(
                Arguments.of(true, "javax.xml.xpath.XPath", "javax.xml.xpath.XPath"),
                Arguments.of(false, "javax.xml.xpath.XPath", "XPathType")
        );
    }

    @Value
    @Builder
    private static class ExpectedData {
        String itemName;
        boolean usedJavaTypes;
        boolean stringifiable;

        String expectedJavaType;
        String expectedName;

        boolean parentUsedJavaTypes;
        String expectedParentJavaType;
        String expectedParentName;
        String expectParentComment;
    }
}