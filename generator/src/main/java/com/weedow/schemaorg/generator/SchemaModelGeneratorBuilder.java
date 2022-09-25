package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.core.SchemaModelGeneratorImpl;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.handler.*;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import com.weedow.schemaorg.generator.parser.SchemaModelParser;
import com.weedow.schemaorg.generator.parser.SchemaModelParserImpl;
import com.weedow.schemaorg.generator.reader.SchemaDefinitionReaderImpl;
import com.weedow.schemaorg.generator.template.TemplateService;
import com.weedow.schemaorg.generator.template.TemplateServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SchemaModelGeneratorBuilder {

    private ParserOptions parserOptions;
    private GeneratorOptions generatorOptions;

    public SchemaModelGeneratorBuilder parserOptions(ParserOptions parserOptions) {
        this.parserOptions = parserOptions;
        return this;
    }

    public SchemaModelGeneratorBuilder generatorOptions(GeneratorOptions generatorOptions) {
        this.generatorOptions = generatorOptions;
        return this;
    }

    public SchemaModelGenerator build() {
        final SchemaDefinitionReaderImpl schemaDefinitionReader = new SchemaDefinitionReaderImpl();
        List<ModelHandler> modelHandlers = Arrays.asList(
                new PropertyModelHandlerImpl(),
                new DataTypeModelHandlerImpl(),
                new SubDataTypeModelHandlerImpl(),
                new ClassModelHandlerImpl(),
                new EnumerationMemberModelHandlerImpl()
        );
        SchemaModelParser schemaModelParser = new SchemaModelParserImpl(schemaDefinitionReader, modelHandlers);

        final ParserOptions pOptions = this.parserOptions != null ? this.parserOptions : new ParserOptions();
        final Map<String, Type> schemaDefinitions = schemaModelParser.parse(pOptions);

        final GeneratorOptions gOptions = this.generatorOptions != null ? this.generatorOptions : new GeneratorOptions();
        TemplateService templateService = new TemplateServiceImpl();

        return new SchemaModelGeneratorImpl(gOptions, templateService, schemaDefinitions);
    }
}
