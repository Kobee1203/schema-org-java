package com.weedow.schemaorg.generator.parser;

import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.handler.ModelHandler;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.Label;
import com.weedow.schemaorg.generator.model.jsonld.SchemaDefinition;
import com.weedow.schemaorg.generator.reader.SchemaDefinitionReader;
import com.weedow.schemaorg.generator.reader.SchemaDefinitionReaderException;
import nl.altindag.log.LogCaptor;
import nl.altindag.log.model.LogEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.weedow.schemaorg.generator.logging.Emojis.*;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
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
        try (LogCaptor logCaptor = LogCaptor.forClass(SchemaModelParserImpl.class)) {
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

                assertThat(sd).isEqualTo(schemaDefinitions);
                assertThat(gi).isEqualTo(graphItem);

                sd.put(graphItemId, type);
                return null;
            }).when(modelHandler).handle(schemaDefinitions, graphItem, options);

            final Stream<ModelHandler> stream = Stream.of(modelHandler);
            when(this.modelHandlers.stream()).thenReturn(stream);

            final Map<String, Type> result = schemaModelParser.parse(options);
            assertThat(result)
                    .isNotNull()
                    .hasSize(1)
                    .contains(entry(graphItemId, type));

            // Verify logs
            List<LogEvent> logEvents = logCaptor.getLogEvents();
            assertThat(logEvents).hasSizeGreaterThanOrEqualTo(2);

            // Verify resource loading log
            if (version == null) {
                assertThat(logEvents).anySatisfy(logEvent -> {
                    assertThat(logEvent.getLevel()).isEqualTo("INFO");
                    assertThat(logEvent.getMessage()).isEqualTo(PACKAGE.value() + " Loading local default resource '{}'");
                    assertThat(logEvent.getArguments()).containsExactly("classpath:schemaorg-current-https.jsonld");
                });
            } else {
                assertThat(logEvents).anySatisfy(logEvent -> {
                    assertThat(logEvent.getLevel()).isEqualTo("INFO");
                    assertThat(logEvent.getMessage()).isEqualTo(DOWNLOAD.value() + " Downloading version '{}'");
                    assertThat(logEvent.getArguments()).containsExactly(version);
                });
            }

            // Verify parsing started log
            assertThat(logEvents).anySatisfy(logEvent -> {
                assertThat(logEvent.getLevel()).isEqualTo("INFO");
                assertThat(logEvent.getMessage()).isEqualTo(SEARCH.value() + " Parsing the schema definitions...");
            });

            // Verify parsing completed log
            assertThat(logEvents).anySatisfy(logEvent -> {
                assertThat(logEvent.getLevel()).isEqualTo("INFO");
                assertThat(logEvent.getMessage()).isEqualTo(FLAG.value() + " Parsing completed.");
            });
        }
    }

    @Test
    void parse_with_custom_data_types_should_log_configuration() throws SchemaDefinitionReaderException {
        try (LogCaptor logCaptor = LogCaptor.forClass(SchemaModelParserImpl.class)) {
            ParserOptions options = new ParserOptions();
            Map<String, String> customDataTypes = new HashMap<>();
            customDataTypes.put("Text", "java.lang.String");
            customDataTypes.put("Integer", "java.lang.Integer");
            options.setCustomDataTypes(customDataTypes);

            final GraphItem graphItem = mock(GraphItem.class);
            when(graphItem.getId()).thenReturn("schema:Thing");
            when(graphItem.getTypes()).thenReturn(List.of("rdfs:Class"));
            when(graphItem.getLabel()).thenReturn(createLabel("Thing"));

            final SchemaDefinition schemaDefinition = mock(SchemaDefinition.class);
            when(schemaDefinition.getGraph()).thenReturn(List.of(graphItem));

            when(schemaDefinitionReader.read(any(InputStream.class))).thenReturn(schemaDefinition);

            final ModelHandler modelHandler = mock(ModelHandler.class);
            when(modelHandler.supports(any(), any())).thenReturn(false);
            when(this.modelHandlers.stream()).thenReturn(Stream.of(modelHandler));

            schemaModelParser.parse(options);

            // Verify custom data types log
            List<LogEvent> logEvents = logCaptor.getLogEvents();
            assertThat(logEvents).anySatisfy(logEvent -> {
                assertThat(logEvent.getLevel()).isEqualTo("INFO");
                assertThat(logEvent.getMessage()).isEqualTo(LABEL.value() + " Custom data Types configured: {}");
                String logArg = (String) logEvent.getArguments().get(0);
                assertThat(logArg).contains("Text=java.lang.String");
                assertThat(logArg).contains("Integer=java.lang.Integer");
            });
        }
    }

    @Test
    void parse_with_used_java_types_should_log_info() throws SchemaDefinitionReaderException {
        try (LogCaptor logCaptor = LogCaptor.forClass(SchemaModelParserImpl.class)) {
            ParserOptions options = new ParserOptions();
            options.setUsedJavaTypes(true);

            final GraphItem graphItem = mock(GraphItem.class);
            when(graphItem.getId()).thenReturn("schema:Thing");
            when(graphItem.getTypes()).thenReturn(List.of("rdfs:Class"));
            when(graphItem.getLabel()).thenReturn(createLabel("Thing"));

            final SchemaDefinition schemaDefinition = mock(SchemaDefinition.class);
            when(schemaDefinition.getGraph()).thenReturn(List.of(graphItem));

            when(schemaDefinitionReader.read(any(InputStream.class))).thenReturn(schemaDefinition);

            final ModelHandler modelHandler = mock(ModelHandler.class);
            when(modelHandler.supports(any(), any())).thenReturn(false);
            when(this.modelHandlers.stream()).thenReturn(Stream.of(modelHandler));

            schemaModelParser.parse(options);

            // Verify java types log
            List<LogEvent> logEvents = logCaptor.getLogEvents();
            assertThat(logEvents).anySatisfy(logEvent -> {
                assertThat(logEvent.getLevel()).isEqualTo("INFO");
                assertThat(logEvent.getMessage()).isEqualTo(JAVA.value() + " Java types are used instead of Schema.org Data Types.");
            });
        }
    }

    @Test
    void parse_in_verbose_mode_should_log_each_graph_item() throws SchemaDefinitionReaderException {
        boolean backupVerbose = SchemaModelGeneratorConstants.isVerbose();
        SchemaModelGeneratorConstants.setVerbose(true);

        try (LogCaptor logCaptor = LogCaptor.forClass(SchemaModelParserImpl.class)) {
            ParserOptions options = new ParserOptions();

            final String graphItemId = "schema:Person";
            final Label label = createLabel("Person");

            final GraphItem graphItem = mock(GraphItem.class);
            when(graphItem.getId()).thenReturn(graphItemId);
            when(graphItem.getTypes()).thenReturn(List.of("rdfs:Class"));
            when(graphItem.getLabel()).thenReturn(label);

            final SchemaDefinition schemaDefinition = mock(SchemaDefinition.class);
            when(schemaDefinition.getGraph()).thenReturn(List.of(graphItem));

            when(schemaDefinitionReader.read(any(InputStream.class))).thenReturn(schemaDefinition);

            final ModelHandler modelHandler = mock(ModelHandler.class);
            when(modelHandler.supports(any(), any())).thenReturn(false);
            when(this.modelHandlers.stream()).thenReturn(Stream.of(modelHandler));

            schemaModelParser.parse(options);

            // Verify verbose log for graph item
            List<LogEvent> logEvents = logCaptor.getLogEvents();
            assertThat(logEvents).anySatisfy(logEvent -> {
                assertThat(logEvent.getLevel()).isEqualTo("INFO");
                assertThat(logEvent.getMessage()).isEqualTo("id={}, types={}, label={}");
                assertThat(logEvent.getArguments()).containsExactly(graphItemId, List.of("rdfs:Class"), label);
            });
        } finally {
            SchemaModelGeneratorConstants.setVerbose(backupVerbose);
        }
    }

    @Test
    void parse_with_exception_should_log_error() throws SchemaDefinitionReaderException {
        try (LogCaptor logCaptor = LogCaptor.forClass(SchemaModelParserImpl.class)) {
            ParserOptions options = new ParserOptions();

            SchemaDefinitionReaderException exception = new SchemaDefinitionReaderException("Failed to read schema", new IOException());
            when(schemaDefinitionReader.read(any(InputStream.class))).thenThrow(exception);

            schemaModelParser.parse(options);

            // Verify error log
            List<LogEvent> logEvents = logCaptor.getLogEvents();
            assertThat(logEvents).anySatisfy(logEvent -> {
                assertThat(logEvent.getLevel()).isEqualTo("ERROR");
                assertThat(logEvent.getMessage()).isEqualTo(ERROR.value() + " Could not generate the schema models: {}");
                assertThat(logEvent.getArguments()).containsExactly("Failed to read schema");
                assertThat(logEvent.getThrowable()).isPresent();
                assertThat(logEvent.getThrowable()).contains(exception);
            });
        }
    }

    @Test
    void parse_with_custom_resource_should_log_loading_resource() {
        try (LogCaptor logCaptor = LogCaptor.forClass(SchemaModelParserImpl.class)) {
            ParserOptions options = new ParserOptions();
            String customResource = "classpath:schemaorg-current-https.jsonld";  // Use existing resource
            options.setSchemaResource(customResource);

            final GraphItem graphItem = mock(GraphItem.class);
            when(graphItem.getId()).thenReturn("schema:Thing");
            when(graphItem.getTypes()).thenReturn(List.of("rdfs:Class"));
            when(graphItem.getLabel()).thenReturn(createLabel("Thing"));

            final SchemaDefinition schemaDefinition = mock(SchemaDefinition.class);
            when(schemaDefinition.getGraph()).thenReturn(List.of(graphItem));

            try {
                when(schemaDefinitionReader.read(any(InputStream.class))).thenReturn(schemaDefinition);
            } catch (SchemaDefinitionReaderException e) {
                throw new RuntimeException(e);
            }

            final ModelHandler modelHandler = mock(ModelHandler.class);
            when(modelHandler.supports(any(), any())).thenReturn(false);
            when(this.modelHandlers.stream()).thenReturn(Stream.of(modelHandler));

            schemaModelParser.parse(options);

            // Verify custom resource loading log
            List<LogEvent> logEvents = logCaptor.getLogEvents();
            assertThat(logEvents).anySatisfy(logEvent -> {
                assertThat(logEvent.getLevel()).isEqualTo("INFO");
                assertThat(logEvent.getMessage()).isEqualTo(RESOURCE.value() + " Loading resource '{}'");
                assertThat(logEvent.getArguments()).containsExactly(customResource);
            });
        }
    }

    private Label createLabel(String value) {
        Label label = new Label();
        label.setValue(value);
        return label;
    }
}