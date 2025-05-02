package com.weedow.schemaorg.generator.plugin;

import com.soebes.itf.extension.assertj.MavenITAssertions;
import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenRepository;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;
import org.assertj.core.api.Assertions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@MavenJupiterExtension
@MavenRepository
class SchemaModelGeneratorMojoIT {

    @MavenTest
    void generate_with_verbose_mode(MavenExecutionResult result) {
        MavenITAssertions.assertThat(result).isSuccessful();

        MavenITAssertions.assertThat(result)
                .out()
                .info()
                .contains("VERBOSE MODE: ON.")
                .contains("Adding the generated java types and generated resources as compiled source root.")
                .anyMatch(s -> s.matches("Finished: \\d+ s"));

        MavenITAssertions.assertThat(result)
                .project()
                .hasTarget()
                .has("target/generated-sources/schemaorg/org/schema/model")
                .has("target/generated-sources/schemaorg/org/schema/model/datatype")
                .has("target/generated-sources/schemaorg/org/schema/model/impl");

        final List<String> lines = readResourceLines("/generated-classes-generate_with_verbose_mode.txt");
        MavenITAssertions.assertThat(result)
                .project()
                .withJarFile()
                .containsOnly(lines.toArray(new String[]{}));
    }

    @MavenTest
    void generate_with_commons_dependency(MavenExecutionResult result) {
        MavenITAssertions.assertThat(result).isSuccessful();

        MavenITAssertions.assertThat(result)
                .out()
                .info()
                .doesNotContain("VERBOSE MODE: ON.")
                .contains("Adding the generated java types and generated resources as compiled source root.")
                .anyMatch(s -> s.matches("Finished: \\d+ s"));

        MavenITAssertions.assertThat(result)
                .project()
                .hasTarget()
                .has("target/generated-sources/schemaorg/org/schema/model")
                .has("target/generated-sources/schemaorg/org/schema/model/datatype")
                .has("target/generated-sources/schemaorg/org/schema/model/impl");

        final List<String> lines = readResourceLines("/generated-classes-generate_with_commons_dependency.txt");
        MavenITAssertions.assertThat(result)
                .project()
                .withJarFile()
                .containsOnly(lines.toArray(new String[]{}));
    }

    @MavenTest
    void generate_with_schema_resource(MavenExecutionResult result) {
        MavenITAssertions.assertThat(result).isSuccessful();

        MavenITAssertions.assertThat(result)
                .out()
                .info()
                .doesNotContain("VERBOSE MODE: ON.")
                .contains("Adding the generated java types and generated resources as compiled source root.")
                .anyMatch(s -> s.matches("Finished: \\d+ s"));

        MavenITAssertions.assertThat(result)
                .project()
                .hasTarget()
                .has("target/generated-sources/example/org/schema/model")
                .has("target/generated-sources/example/org/schema/model/datatype")
                .has("target/generated-sources/example/org/schema/model/impl");

        final List<String> lines = readResourceLines("/generated-classes-generate_with_schema_resource.txt");
        MavenITAssertions.assertThat(result)
                .project()
                .withJarFile()
                .containsOnly(lines.toArray(new String[]{}));
    }

    @MavenTest
    void generate_with_java_types(MavenExecutionResult result) {
        MavenITAssertions.assertThat(result).isSuccessful();

        MavenITAssertions.assertThat(result)
                .out()
                .info()
                .doesNotContain("VERBOSE MODE: ON.")
                .contains("Java types are used instead of Schema.org Data Types")
                .contains("Adding the generated java types and generated resources as compiled source root.")
                .anyMatch(s -> s.matches("Finished: \\d+ s"));

        MavenITAssertions.assertThat(result)
                .project()
                .hasTarget()
                .has("target/generated-sources/example/org/schema/model")
                .has("target/generated-sources/example/org/schema/model/datatype")
                .has("target/generated-sources/example/org/schema/model/impl");

        final List<String> lines = readResourceLines("/generated-classes-generate_with_java_types.txt");
        MavenITAssertions.assertThat(result)
                .project()
                .withJarFile()
                .containsOnly(lines.toArray(new String[]{}));

        assertFileContent(
                result,
                "target/generated-sources/example/org/schema/model/Example.java",
                "/data/java_types/Example.java"
        );
        assertFileContent(
                result,
                "target/generated-sources/example/org/schema/model/impl/ExampleImpl.java",
                "/data/java_types/ExampleImpl.java"
        );
    }

    @MavenTest
    void generate_with_custom_data_types(MavenExecutionResult result) {
        MavenITAssertions.assertThat(result).isSuccessful();

        MavenITAssertions.assertThat(result)
                .out()
                .info()
                .contains("Custom data Types configured: DateTime=java.time.ZonedDateTime, XPathType=javax.xml.xpath.XPath")
                .contains("Adding the generated java types and generated resources as compiled source root.")
                .anyMatch(s -> s.matches("Finished: \\d+ s"));

        MavenITAssertions.assertThat(result)
                .project()
                .hasTarget()
                .has("target/generated-sources/example/org/schema/model")
                .has("target/generated-sources/example/org/schema/model/datatype")
                .has("target/generated-sources/example/org/schema/model/impl");

        final List<String> lines = readResourceLines("/generated-classes-generate_with_custom_data_types.txt");
        MavenITAssertions.assertThat(result)
                .project()
                .withJarFile()
                .containsOnly(lines.toArray(new String[]{}));

        assertFileContent(
                result,
                "target/generated-sources/example/org/schema/model/datatype/DateTime.java",
                "/data/custom_data_types/DateTime.java"
        );
        assertFileContent(
                result,
                "target/generated-sources/example/org/schema/model/datatype/XPathType.java",
                "/data/custom_data_types/XPathType.java"
        );
    }

    @MavenTest
    void generate_with_custom_data_types_and_java_types(MavenExecutionResult result) {
        MavenITAssertions.assertThat(result).isSuccessful();

        MavenITAssertions.assertThat(result)
                .out()
                .info()
                .contains("Custom data Types configured: DateTime=java.time.ZonedDateTime, XPathType=javax.xml.xpath.XPath")
                .contains("Java types are used instead of Schema.org Data Types")
                .contains("Adding the generated java types and generated resources as compiled source root.")
                .anyMatch(s -> s.matches("Finished: \\d+ s"));

        MavenITAssertions.assertThat(result)
                .project()
                .hasTarget()
                .has("target/generated-sources/example/org/schema/model")
                .has("target/generated-sources/example/org/schema/model/datatype")
                .has("target/generated-sources/example/org/schema/model/impl");

        final List<String> lines = readResourceLines("/generated-classes-generate_with_custom_data_types_and_java_types.txt");
        MavenITAssertions.assertThat(result)
                .project()
                .withJarFile()
                .containsOnly(lines.toArray(new String[]{}));

        assertFileContent(
                result,
                "target/generated-sources/example/org/schema/model/Example.java",
                "/data/custom_data_types_and_java_types/Example.java"
        );
        assertFileContent(
                result,
                "target/generated-sources/example/org/schema/model/impl/ExampleImpl.java",
                "/data/custom_data_types_and_java_types/ExampleImpl.java"
        );
    }

    @MavenTest
    void generate_with_skip_option(MavenExecutionResult result) {
        MavenITAssertions.assertThat(result).isSuccessful();

        MavenITAssertions.assertThat(result)
                .out()
                .info()
                .contains("Code generation is skipped.")
                .contains("Adding the generated java types and generated resources as compiled source root.")
                .noneMatch(s -> s.matches("Finished: \\d+ s"));

        MavenITAssertions.assertThat(result)
                .project()
                .hasTarget();

        final List<String> lines = readResourceLines("/generated-classes-generate_with_skip_option.txt");
        MavenITAssertions.assertThat(result)
                .project()
                .withJarFile()
                .containsOnly(lines.toArray(new String[]{}));
    }

    @MavenTest
    void generate_with_processing_NOTHING(MavenExecutionResult result) {
        MavenITAssertions.assertThat(result).isSuccessful();

        MavenITAssertions.assertThat(result)
                .out()
                .info()
                .doesNotContain("Adding the generated java types and generated resources as compiled source root.")
                .anyMatch(s -> s.matches("Finished: \\d+ s"));

        MavenITAssertions.assertThat(result)
                .project()
                .hasTarget();

        final List<String> lines = readResourceLines("/generated-classes-generate_with_processing_NOTHING.txt");
        MavenITAssertions.assertThat(result)
                .project()
                .withJarFile()
                .containsOnly(lines.toArray(new String[]{}));
    }

    private List<String> readResourceLines(String resourceLocation) {
        List<String> lines = null;
        try (InputStream resource = getClass().getResourceAsStream(resourceLocation)) {
            if (resource != null) {
                lines = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().toList();
            } else {
                Assertions.fail("Resource not found: " + resourceLocation);
            }
        } catch (IOException e) {
            Assertions.fail("Could not read the resource " + resourceLocation, e);
        }
        return lines;
    }

    private void assertFileContent(MavenExecutionResult result, String outputFilePath, String expectedFilePath) {
        try {
            Path projectDirectory = result.getMavenProjectResult().getTargetProjectDirectory();
            Path outputFile = projectDirectory.resolve(outputFilePath);
            URL resource = getClass().getResource(expectedFilePath);
            Assertions.assertThat(resource).as(() -> "Check Resource presence: " + expectedFilePath).isNotNull();
            Assertions.assertThat(outputFile).hasSameTextualContentAs(Paths.get(resource.toURI()), StandardCharsets.UTF_8);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}