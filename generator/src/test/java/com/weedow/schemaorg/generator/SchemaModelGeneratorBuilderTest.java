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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class SchemaModelGeneratorBuilderTest {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorBuilderTest.class);

    @Test
    @Disabled("This test is too long because we compare verify all generated classes. Enable locally if it is required to check all generated classes.")
    void generate_all() {
        Map<Path, List<String>> dataMap = generateAndVerify(null, null, false);

        Assertions.assertThat(dataMap).hasSize(3);
        Assertions.assertThat(dataMap.get(Path.of("org", "schema", "model", "datatype"))).hasSize(13);
        Assertions.assertThat(dataMap.get(Path.of("org", "schema", "model"))).hasSize(880);
        Assertions.assertThat(dataMap.get(Path.of("org", "schema", "model", "impl"))).hasSize(880);
    }

    @Test
    void generate_specific_models() {
        List<String> models = List.of("Thing");

        Map<Path, List<String>> dataMap = generateAndVerify(models, null, false);

        Assertions.assertThat(dataMap).hasSize(3);
        Assertions.assertThat(dataMap.get(Path.of("org", "schema", "model", "datatype"))).hasSize(11);
        Assertions.assertThat(dataMap.get(Path.of("org", "schema", "model"))).hasSize(205);
        Assertions.assertThat(dataMap.get(Path.of("org", "schema", "model", "impl"))).hasSize(205);
    }

    @Test
    void generate_with_java_types() {
        List<String> models = List.of("Example");

        Map<Path, List<String>> dataMap = generateAndVerify(models, "classpath:data/example.jsonld", true);

        Assertions.assertThat(dataMap).hasSize(2);
        Assertions.assertThat(dataMap.get(Path.of("org", "schema", "model"))).hasSize(1);
        Assertions.assertThat(dataMap.get(Path.of("org", "schema", "model", "impl"))).hasSize(1);
    }

    private Map<Path, List<String>> generateAndVerify(List<String> models, String schemaResource, boolean usedJavaTypes) {
        final Map<Path, List<String>> dataMap = new ConcurrentHashMap<>();

        final ParserOptions parserOptions = new ParserOptions()
                .setSchemaVersion(null)
                .setSchemaResource(schemaResource)
                .setUsedJavaTypes(usedJavaTypes);

        final GeneratorOptions generatorOptions = new GeneratorOptions();
        generatorOptions.setModels(models);
        generatorOptions
                .addSuccessHandler((templateName, outputFile, context) -> {
                    String expectedFilePath = "/data/" + generatorOptions.getOutputFolder().relativize(outputFile).toString().replace("\\", "/");
                    LOG.info("Comparing {} with {}", outputFile, expectedFilePath);
                    try (InputStream resourceAsStream = getClass().getResourceAsStream(expectedFilePath)) {
                        assert resourceAsStream != null;
                        byte[] expected = resourceAsStream.readAllBytes();
                        Assertions.assertThat(outputFile).hasBinaryContent(expected);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .addSuccessHandler((templateName, outputFile, context) -> {
                    Path classPath = generatorOptions.getOutputFolder().relativize(outputFile);
                    dataMap.computeIfAbsent(classPath.getParent(), path -> new ArrayList<>()).add(classPath.getFileName().toString());
                })
                .addErrorHandler((templateName, outputFile, context, e) -> Assertions.fail("An error occurred while generating {}: {}", outputFile, e.getMessage()));

        final SchemaModelGenerator schemaModelGenerator = new SchemaModelGeneratorBuilder()
                .parserOptions(parserOptions)
                .generatorOptions(generatorOptions)
                .build();
        schemaModelGenerator.generate();

        return dataMap;
    }

}