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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SchemaModelGeneratorApp {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorApp.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        final SchemaDefinitionReaderImpl schemaDefinitionReader = new SchemaDefinitionReaderImpl();
        List<ModelHandler> modelHandlers = Arrays.asList(
                new PropertyModelHandlerImpl(),
                new DataTypeModelHandlerImpl(),
                new SubDataTypeModelHandlerImpl(),
                new ClassModelHandlerImpl(),
                new EnumerationMemberModelHandlerImpl()
        );
        SchemaModelParser schemaModelParser = new SchemaModelParserImpl(schemaDefinitionReader, modelHandlers);

        ParserOptions parserOptions = new ParserOptions();
        final Map<String, Type> schemaDefinitions = schemaModelParser.parse(parserOptions);

        TemplateService templateService = new TemplateServiceImpl();
        final SchemaModelGenerator schemaModelGenerator = new SchemaModelGeneratorImpl(templateService, schemaDefinitions);

        GeneratorOptions generatorOptions = new GeneratorOptions();
        schemaModelGenerator.generate(generatorOptions);

        long end = System.currentTimeMillis();
        LOG.info("Finished: {} s", TimeUnit.SECONDS.convert(end - start, TimeUnit.MILLISECONDS));
    }

}
