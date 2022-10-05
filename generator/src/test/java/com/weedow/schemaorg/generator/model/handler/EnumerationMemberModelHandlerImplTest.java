package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
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

import static com.weedow.schemaorg.generator.model.handler.ModelHandlerTestUtils.label;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnumerationMemberModelHandlerImplTest {

    @InjectMocks
    private EnumerationMemberModelHandlerImpl modelHandler;

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
        when(graphItem.getTypes()).thenReturn(List.of("schema:ActionStatusType"));
        when(graphItem.getLabel()).thenReturn(label("en", "PotentialActionStatus"));

        modelHandler.handle(schemaDefinitions, graphItem);

        Assertions.assertThat(schemaDefinitions).isNotEmpty().containsOnlyKeys("schema:ActionStatusType");
        Assertions.assertThat(schemaDefinitions.get("schema:ActionStatusType"))
                .extracting(
                        "id", "javaType", "name", "description",
                        "properties", "allProperties",
                        "parents",
                        "enumerationType", "enumerationMembers",
                        "partOf", "source"
                )
                .containsExactly(
                        "schema:ActionStatusType", null, null, null,
                        Collections.emptySet(), Collections.emptySet(),
                        Collections.emptyList(),
                        false /* Parent is not added */, List.of("PotentialActionStatus"),
                        Collections.emptyList(), Collections.emptyList()
                );
    }

    private static Stream<Arguments> supports() {
        return Stream.of(
                Arguments.of(List.of("schema:ActionStatusType"), true),
                Arguments.of(List.of("schema:ActionStatusType", "rdfs:Class"), false), // types size not equal to 1
                Arguments.of(List.of("rdfs:ActionStatusType"), false), // type not started with "schema:"
                Arguments.of(List.of("schema:DataType"), false) // "schema:DataType" not allowed
        );
    }
}