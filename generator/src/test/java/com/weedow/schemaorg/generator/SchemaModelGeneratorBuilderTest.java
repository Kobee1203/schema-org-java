package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaGeneratorUtils;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class SchemaModelGeneratorBuilderTest {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorBuilderTest.class);

    @AfterEach
    void tearDown() {
        SchemaGeneratorUtils.clearCache();
    }

    @Test
    @Disabled("This test is too long because we compare verify all generated classes. Enable locally if it is required to check all generated classes.")
    void generate_all() {
        Map<Path, List<String>> dataMap = generateAndVerify(null, null, null, false, null);

        Assertions.assertThat(dataMap).hasSize(3);
        Assertions.assertThat(dataMap.get(Path.of("org", "schema", "model", "datatype"))).hasSize(13);
        Assertions.assertThat(dataMap.get(Path.of("org", "schema", "model"))).hasSize(880);
        Assertions.assertThat(dataMap.get(Path.of("org", "schema", "model", "impl"))).hasSize(880);
    }

    @Test
    void generate_specific_models() {
        final String packageName = "spec";
        List<String> models = List.of("Thing");

        Map<Path, List<String>> dataMap = generateAndVerify(models, packageName, null, false, null);

        Assertions.assertThat(dataMap).hasSize(3);
        Assertions.assertThat(dataMap.get(Path.of(packageName, "model", "datatype"))).hasSize(11);
        Assertions.assertThat(dataMap.get(Path.of(packageName, "model"))).hasSize(205);
        Assertions.assertThat(dataMap.get(Path.of(packageName, "model", "impl"))).hasSize(205);
    }

    @Test
    void generate_specific_models_with_custom_data_types() {
        final String packageName = "spec_custom";
        List<String> models = List.of("Thing");
        Map<String, String> customDataTypes = Map.of(
                "schema:DateTime", ZonedDateTime.class.getName(),
                "XPathType", javax.xml.xpath.XPath.class.getName()
        );

        Map<Path, List<String>> dataMap = generateAndVerify(models, packageName, null, false, customDataTypes);

        Assertions.assertThat(dataMap).hasSize(3);
        Assertions.assertThat(dataMap.get(Path.of(packageName, "model", "datatype"))).hasSize(11);
        Assertions.assertThat(dataMap.get(Path.of(packageName, "model"))).hasSize(205);
        Assertions.assertThat(dataMap.get(Path.of(packageName, "model", "impl"))).hasSize(205);
    }

    @Test
    void generate_with_java_types() {
        final String packageName = "javatypes";
        List<String> models = List.of("Example");

        Map<Path, List<String>> dataMap = generateAndVerify(models, packageName, "classpath:data/example.jsonld", true, null);

        Assertions.assertThat(dataMap).hasSize(2);
        Assertions.assertThat(dataMap.get(Path.of(packageName, "model"))).hasSize(1);
        Assertions.assertThat(dataMap.get(Path.of(packageName, "model", "impl"))).hasSize(1);
    }

    private Map<Path, List<String>> generateAndVerify(List<String> models, String packageName, String schemaResource, boolean usedJavaTypes, Map<String, String> customDataTypes) {
        final Map<Path, List<String>> dataMap = new ConcurrentHashMap<>();

        final ParserOptions parserOptions = new ParserOptions()
                .setSchemaVersion(null)
                .setSchemaResource(schemaResource)
                .setUsedJavaTypes(usedJavaTypes)
                .setCustomDataTypes(customDataTypes);

        final GeneratorOptions generatorOptions = new GeneratorOptions();
        generatorOptions.setModels(models);
        if (packageName != null) {
            generatorOptions.setModelPackage(packageName + ".model");
            generatorOptions.setModelImplPackage(packageName + ".model.impl");
            generatorOptions.setDataTypePackage(packageName + ".model.datatype");
        }
        generatorOptions
                .addSuccessHandler((templateName, outputFile, context) -> {
                    String expectedFilePath = "/data/" + generatorOptions.getOutputFolder().relativize(outputFile).toString().replace("\\", "/");
                    LOG.info("Comparing {} with {}", outputFile, expectedFilePath);
                    try {
                        URL resource = getClass().getResource(expectedFilePath);
                        Assertions.assertThat(resource).as(() -> "Check Resource presence: " + expectedFilePath).isNotNull();
                        Assertions.assertThat(outputFile).hasSameTextualContentAs(Paths.get(resource.toURI()), StandardCharsets.UTF_8);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                })
                .addSuccessHandler((templateName, outputFile, context) -> {
                    Path classPath = generatorOptions.getOutputFolder().relativize(outputFile);
                    dataMap.computeIfAbsent(classPath.getParent(), path -> Collections.synchronizedList(new ArrayList<>())).add(classPath.getFileName().toString());
                })
                .addErrorHandler((templateName, outputFile, context, e) -> Assertions.fail("An error occurred while generating {}: {}", outputFile, e.getMessage()))
                .addCompleteHandler((Duration elapsedTime) ->
                        LOG.info("Completed ({} ms): {}",
                                elapsedTime.toMillis(),
                                dataMap.entrySet()
                                        .stream()
                                        .map(entry -> entry.getKey() + "=" + entry.getValue().size())
                                        .toList()
                        )
                );

        final SchemaModelGenerator schemaModelGenerator = new SchemaModelGeneratorBuilder()
                .parserOptions(parserOptions)
                .generatorOptions(generatorOptions)
                .build();
        schemaModelGenerator.generate();

        return dataMap;
    }

}