package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.core.SchemaModelGeneratorImpl;
import com.weedow.schemaorg.generator.core.copy.CopyService;
import com.weedow.schemaorg.generator.core.copy.CopyServiceImpl;
import com.weedow.schemaorg.generator.core.filter.SchemaDefinitionFilter;
import com.weedow.schemaorg.generator.core.filter.SchemaDefinitionFilterImpl;
import com.weedow.schemaorg.generator.core.stream.StreamService;
import com.weedow.schemaorg.generator.core.stream.StreamServiceImpl;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.handler.ClassModelHandlerImpl;
import com.weedow.schemaorg.generator.model.handler.EnumerationMemberModelHandlerImpl;
import com.weedow.schemaorg.generator.model.handler.ModelHandler;
import com.weedow.schemaorg.generator.model.handler.PropertyModelHandlerImpl;
import com.weedow.schemaorg.generator.model.handler.datatype.BaseDataTypeModelHandlerImpl;
import com.weedow.schemaorg.generator.model.handler.datatype.DataTypeModelHandlerImpl;
import com.weedow.schemaorg.generator.model.handler.datatype.SubDataTypeModelHandlerImpl;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import com.weedow.schemaorg.generator.parser.SchemaModelParser;
import com.weedow.schemaorg.generator.parser.SchemaModelParserImpl;
import com.weedow.schemaorg.generator.reader.SchemaDefinitionReader;
import com.weedow.schemaorg.generator.reader.SchemaDefinitionReaderImpl;
import com.weedow.schemaorg.generator.template.TemplateService;
import com.weedow.schemaorg.generator.template.TemplateServiceImpl;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Builder for creating configured {@link SchemaModelGenerator} instances.
 * <p>
 * This builder allows setting up parser options, generator options, and verbose logging
 * before building the generator. It automatically wires together all necessary components
 * including readers, parsers, handlers, and template services.
 *
 * <p>Example usage:
 * <pre>{@code
 * SchemaModelGenerator generator = new SchemaModelGeneratorBuilder()
 *     .parserOptions(parserOptions)
 *     .generatorOptions(generatorOptions)
 *     .verbose(true)
 *     .build();
 * }</pre>
 */
@NoArgsConstructor
public class SchemaModelGeneratorBuilder {

    private ParserOptions parserOptions;
    private GeneratorOptions generatorOptions;
    private boolean verbose;

    /**
     * Sets the parser options for reading and parsing Schema.org definitions.
     *
     * @param parserOptions the parser configuration options
     * @return this builder for method chaining
     */
    public SchemaModelGeneratorBuilder parserOptions(ParserOptions parserOptions) {
        this.parserOptions = parserOptions;
        return this;
    }

    /**
     * Sets the generator options for controlling code generation.
     *
     * @param generatorOptions the generator configuration options
     * @return this builder for method chaining
     */
    public SchemaModelGeneratorBuilder generatorOptions(GeneratorOptions generatorOptions) {
        this.generatorOptions = generatorOptions;
        return this;
    }

    /**
     * Enables or disables verbose logging during generation.
     *
     * @param verbose {@code true} to enable verbose logging, {@code false} to disable
     * @return this builder for method chaining
     */
    public SchemaModelGeneratorBuilder verbose(boolean verbose) {
        this.verbose = verbose;
        return this;
    }

    /**
     * Builds and returns a configured {@link SchemaModelGenerator} instance.
     * <p>
     * This method:
     * <ul>
     *   <li>Configures verbose logging</li>
     *   <li>Creates and wires all necessary components (reader, parser, handlers, template service)</li>
     *   <li>Parses the Schema.org definitions according to parser options</li>
     *   <li>Returns a generator ready to generate code</li>
     * </ul>
     *
     * @return a configured SchemaModelGenerator instance
     */
    public SchemaModelGenerator build() {
        SchemaModelGeneratorConstants.setVerbose(this.verbose);

        final SchemaDefinitionReader schemaDefinitionReader = new SchemaDefinitionReaderImpl();
        final List<ModelHandler> modelHandlers = Arrays.asList(
                new PropertyModelHandlerImpl(),
                new BaseDataTypeModelHandlerImpl(),
                new DataTypeModelHandlerImpl(),
                new SubDataTypeModelHandlerImpl(),
                new ClassModelHandlerImpl(),
                new EnumerationMemberModelHandlerImpl()
        );
        final SchemaModelParser schemaModelParser = new SchemaModelParserImpl(schemaDefinitionReader, modelHandlers);

        final ParserOptions pOptions = this.parserOptions != null ? this.parserOptions : new ParserOptions();
        final Map<String, Type> schemaDefinitions = schemaModelParser.parse(pOptions);

        final GeneratorOptions gOptions = this.generatorOptions != null ? this.generatorOptions : new GeneratorOptions();
        final TemplateService templateService = new TemplateServiceImpl();
        final SchemaDefinitionFilter schemaDefinitionFilter = new SchemaDefinitionFilterImpl();

        final CopyService copyService = new CopyServiceImpl();
        final StreamService streamService = new StreamServiceImpl();

        return new SchemaModelGeneratorImpl(gOptions, templateService, schemaDefinitionFilter, schemaDefinitions, copyService, streamService);
    }
}
