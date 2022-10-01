package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

class SchemaModelGeneratorBuilderTest {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorBuilderTest.class);

    @Test
    void generate() {
        final ParserOptions parserOptions = new ParserOptions();
        parserOptions.setSchemaVersion(null); // Use local resource

        final GeneratorOptions generatorOptions = new GeneratorOptions();
        final Path outputFolderPath = generatorOptions.getOutputFolder().toPath();
        generatorOptions.addSuccessHandler((templateName, outputFile, context) -> {
            Path outputFilePath = outputFile.toPath();
            String expectedFilePath = ("/data/" + outputFolderPath.relativize(outputFilePath)).replace("\\", "/");
            LOG.info("Comparing {} with {}", outputFilePath, expectedFilePath);
            try (InputStream resourceAsStream = getClass().getResourceAsStream(expectedFilePath)) {
                assert resourceAsStream != null;
                byte[] expected = resourceAsStream.readAllBytes();
                Assertions.assertThat(outputFile).hasBinaryContent(expected);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        generatorOptions.addErrorHandler((templateName, outputFile, context, e) -> Assertions.fail("An error occurred while generating {}: {}", outputFile, e.getMessage()));

        final SchemaModelGenerator schemaModelGenerator = new SchemaModelGeneratorBuilder()
                .parserOptions(parserOptions)
                .generatorOptions(generatorOptions)
                .build();
        schemaModelGenerator.generate();
    }

}