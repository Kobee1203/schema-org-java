package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SchemaModelGeneratorApp {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorApp.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        final SchemaModelGenerator generator = new SchemaModelGeneratorBuilder()
                .parserOptions(new ParserOptions())
                .generatorOptions(new GeneratorOptions())
                .build();
        generator.generate();

        long end = System.currentTimeMillis();
        LOG.info("Finished: {} s", TimeUnit.SECONDS.convert(end - start, TimeUnit.MILLISECONDS));
    }

}
