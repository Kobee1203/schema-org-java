package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class SchemaModelGeneratorBuilderTest {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorBuilderTest.class);

    @Test
    @Disabled
    void generate_all() {
        generateAndVerify(null);
    }

    @Test
    void generate_specific_models() {
        List<String> models = Arrays.asList("Thing");

        generateAndVerify(models);
    }

    private void generateAndVerify(List<String> models) {
        final Map<Path, List<String>> dataMap = new HashMap<>();

        final ParserOptions parserOptions = new ParserOptions();
        parserOptions.setSchemaVersion(null); // Use local resource

        final GeneratorOptions generatorOptions = new GeneratorOptions();
        generatorOptions.setModels(models);
        generatorOptions
                .addSuccessHandler((templateName, outputFile, context) -> {
                    Path outputFilePath = outputFile.toPath();
                    String expectedFilePath = "/data/" + generatorOptions.getOutputFolder().toPath().relativize(outputFilePath).toString().replace("\\", "/");
                    LOG.info("Comparing {} with {}", outputFilePath, expectedFilePath);
                    try (InputStream resourceAsStream = getClass().getResourceAsStream(expectedFilePath)) {
                        assert resourceAsStream != null;
                        byte[] expected = resourceAsStream.readAllBytes();
                        Assertions.assertThat(outputFile).hasBinaryContent(expected);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .addSuccessHandler((templateName, outputFile, context) -> {
                    Path outputFilePath = outputFile.toPath();
                    Path classPath = generatorOptions.getOutputFolder().toPath().relativize(outputFilePath);
                    dataMap.computeIfAbsent(classPath.getParent(), path -> new ArrayList<>()).add(classPath.getFileName().toString());
                })
                .addErrorHandler((templateName, outputFile, context, e) -> Assertions.fail("An error occurred while generating {}: {}", outputFile, e.getMessage()));

        final SchemaModelGenerator schemaModelGenerator = new SchemaModelGeneratorBuilder()
                .parserOptions(parserOptions)
                .generatorOptions(generatorOptions)
                .build();
        schemaModelGenerator.generate();
        Assertions.assertThat(dataMap).hasSize(3);
        int dataTypeCount = 13;
        int modelCount = 882; // + JsonLdTypeName
        int modelImplCount = 881;
        if (models != null && !models.isEmpty()) {
            dataTypeCount = 11;
            modelCount = 207; // + JsonLdTypeName
            modelImplCount = 206;
        }
        Assertions.assertThat(dataMap.get(Paths.get("org/schema/model/datatype"))).hasSize(dataTypeCount);
        Assertions.assertThat(dataMap.get(Paths.get("org/schema/model"))).hasSize(modelCount);
        Assertions.assertThat(dataMap.get(Paths.get("org/schema/model/impl"))).hasSize(modelImplCount);
    }

}