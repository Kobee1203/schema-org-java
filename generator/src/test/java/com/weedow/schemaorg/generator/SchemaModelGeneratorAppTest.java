package com.weedow.schemaorg.generator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOutNormalized;

@ExtendWith(SystemStubsExtension.class)
class SchemaModelGeneratorAppTest {

    @ParameterizedTest
    @ValueSource(strings = {"--help", "-h"})
    void help(String option) throws Exception {
        String[] args = new String[]{option};
        String text = tapSystemOutNormalized(() -> SchemaModelGeneratorApp.main(args));
        Assertions.assertThat(text).isEqualTo(
                """
                        usage: java -jar schema-org-generator.jar SchemaModelGeneratorApp [-dp
                               <package>] [-h] [-j] [-m <models>] [-mip <package>] [-mp <package>] [-o
                               <output>] [-R <resource>] [-V <version>] [-v]
                         -dp,--datatype-package <package>      Package of the data types - Default is
                                                               "org.schema.model.datatype"
                         -h,--help                             Show the help message
                         -j,--javatypes                        Use Java types instead of schema.org
                                                               DataTypes. If not specified, schema.org
                                                               DataTypes are used.
                         -m,--models <models>                  list of models to be generated. If not
                                                               specified, all models will be generated.
                         -mip,--model-impl-package <package>   Package of the model implementations -
                                                               Default is "org.schema.model.impl"
                         -mp,--model-package <package>         Package of the models - Default is
                                                               "org.schema.model"
                         -o,--output <output>                  Location of the output directory -
                                                               Default is
                                                               "/target/generated-sources/schemaorg"
                         -R,--resource <resource>              Schema resource to be used: either a
                                                               "classpath:" pseudo URL, a "file:" URL,
                                                               an URL or a plain file path.
                         -V,--version <version>                Schema version to be used: 'latest' to
                                                               use the latest version, or specific
                                                               version (eg. 13.0). If not specified, the
                                                               generator uses the resource in the JAR.
                                                               see
                                                               https://github.com/schemaorg/schemaorg/tr
                                                               ee/main/data/releases
                         -v,--verbose                          Verbose
                        """
        );
    }

    @Test
    void generate() throws Exception {
        String[] args = new String[]{"--models", "Thing"};
        String text = tapSystemOutNormalized(() -> SchemaModelGeneratorApp.main(args));
        Assertions.assertThat(text)
                .contains("Loading local default resource 'classpath:schemaorg-current-https.jsonld'")
                .contains("Parsing the schema definitions...")
                .contains("Parsing completed.")
                .contains("Generating models...")
                .contains("Model generation completed.")
                .containsPattern("Finished: \\d+ s");
    }

    @Test
    void generate_custom_resource() throws Exception {
        String[] args = new String[]{
                "--resource", "classpath:example.jsonld",
                "-mp", "org.example.model",
                "-mip", "org.example.model.impl",
                "-dp", "org.example.model.datatype"
        };
        String text = tapSystemOutNormalized(() -> SchemaModelGeneratorApp.main(args));
        Assertions.assertThat(text)
                .doesNotContain("Java types are used instead of Schema.org Data Types")
                .contains("Loading resource 'classpath:example.jsonld'")
                .contains("Parsing the schema definitions...")
                .contains("Parsing completed.")
                .contains("Generating models...")
                .contains("Model generation completed.")
                .containsPattern("Finished: \\d+ s");
    }

    @Test
    void generate_with_java_types() throws Exception {
        String[] args = new String[]{
                "--resource", "classpath:example.jsonld",
                "-mp", "org.example.models",
                "-mip", "org.example.models.impl",
                "-dp", "org.example.models.datatype",
                "--javatypes"
        };
        String text = tapSystemOutNormalized(() -> SchemaModelGeneratorApp.main(args));
        Assertions.assertThat(text)
                .contains("Java types are used instead of Schema.org Data Types")
                .contains("Loading resource 'classpath:example.jsonld'")
                .contains("Parsing the schema definitions...")
                .contains("Parsing completed.")
                .contains("Generating models...")
                .contains("Model generation completed.")
                .containsPattern("Finished: \\d+ s");
    }
}
