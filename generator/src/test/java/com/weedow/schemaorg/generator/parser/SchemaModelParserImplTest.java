package com.weedow.schemaorg.generator.parser;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.handler.ModelHandler;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.Label;
import com.weedow.schemaorg.generator.model.jsonld.SchemaDefinition;
import com.weedow.schemaorg.generator.reader.SchemaDefinitionReader;
import com.weedow.schemaorg.generator.reader.SchemaDefinitionReaderException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Map.entry;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchemaModelParserImplTest {

    @Mock
    private SchemaDefinitionReader schemaDefinitionReader;

    @Mock
    private List<ModelHandler> modelHandlers;

    @InjectMocks
    private SchemaModelParserImpl schemaModelParser;

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"latest", "13.0"})
    void parse(String version) throws SchemaDefinitionReaderException {
        ParserOptions options = new ParserOptions();
        options.setSchemaVersion(version);

        final String graphItemId = "schema:Thing";
        final Label label = new Label();
        label.setValue("Thing");

        final GraphItem graphItem = mock(GraphItem.class);
        when(graphItem.getId()).thenReturn(graphItemId);
        when(graphItem.getTypes()).thenReturn(List.of("rdfs:Class"));
        when(graphItem.getLabel()).thenReturn(label);

        final SchemaDefinition schemaDefinition = mock(SchemaDefinition.class);
        when(schemaDefinition.getGraph()).thenReturn(List.of(graphItem));

        when(schemaDefinitionReader.read(any(InputStream.class))).thenReturn(schemaDefinition);

        final Map<String, Type> schemaDefinitions = new HashMap<>();

        final Type type = mock(Type.class);

        final ModelHandler modelHandler = mock(ModelHandler.class);
        when(modelHandler.supports(graphItem, options)).thenReturn(true);
        doAnswer(invocation -> {
            final Map<String, Type> sd = invocation.getArgument(0);
            final GraphItem gi = invocation.getArgument(1);

            Assertions.assertThat(sd).isEqualTo(schemaDefinitions);
            Assertions.assertThat(gi).isEqualTo(graphItem);

            sd.put(graphItemId, type);
            return null;
        }).when(modelHandler).handle(schemaDefinitions, graphItem, options);

        final Stream<ModelHandler> stream = Stream.of(modelHandler);
        when(this.modelHandlers.stream()).thenReturn(stream);

        final Map<String, Type> result = schemaModelParser.parse(options);
        Assertions.assertThat(result)
                .isNotNull()
                .hasSize(1)
                .contains(entry(graphItemId, type));
    }
}