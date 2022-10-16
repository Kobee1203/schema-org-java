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
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@MavenJupiterExtension
@MavenRepository
class SchemaModelGeneratorMojoIT {

    @MavenTest
    void test(MavenExecutionResult result) {
        MavenITAssertions.assertThat(result).isSuccessful();

        MavenITAssertions.assertThat(result)
                .out()
                .info()
                .contains("VERBOSE MODE: ON.");

        MavenITAssertions.assertThat(result)
                .project()
                .hasTarget()
                .has("target/generated-sources/schemaorg/org/schema/model")
                .has("target/generated-sources/schemaorg/org/schema/model/datatype")
                .has("target/generated-sources/schemaorg/org/schema/model/impl");

        final List<String> lines = readResourceLines("/generated-classes.txt");
        MavenITAssertions.assertThat(result)
                .project()
                .withJarFile()
                .containsOnly(lines.toArray(new String[]{}));
    }

    private List<String> readResourceLines(String resourceLocation) {
        List<String> lines = null;
        try (InputStream resource = getClass().getResourceAsStream(resourceLocation)) {
            if (resource != null) {
                lines = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
            } else {
                Assertions.fail("Resource not found: " + resourceLocation);
            }
        } catch (IOException e) {
            Assertions.fail("Could not read the resource " + resourceLocation, e);
        }
        return lines;
    }
}