package com.weedow.schemaorg.generator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static uk.org.webcompere.systemstubs.SystemStubs.catchSystemExit;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOutNormalized;

@ExtendWith(SystemStubsExtension.class)
class SchemaModelGeneratorAppTest {

    @ParameterizedTest
    @ValueSource(strings = {"--help", "-h"})
    void help(String option) throws Exception {
        String[] args = new String[]{option};
        String text = tapSystemOutNormalized(() -> {
            int exitCode = catchSystemExit(() -> SchemaModelGeneratorApp.main(args));
            Assertions.assertThat(exitCode).isZero();
        });
        Assertions.assertThat(text).isEqualTo(
                "usage: java -jar schema-org-generator.jar SchemaModelGeneratorApp [-h] [-m\n" +
                        "       <models>] [-V <version>] [-v]\n" +
                        " -h,--help                Show the help message\n" +
                        " -m,--models <models>     list of models to be generated. If not\n" +
                        "                          specified, all models will be generated.\n" +
                        " -V,--version <version>   Schema version to be used: 'latest' to use the\n" +
                        "                          latest version, or specific version (eg. 13.0).\n" +
                        "                          If not specified, the generator uses the\n" +
                        "                          resource in the JAR. see\n" +
                        "                          https://github.com/schemaorg/schemaorg/tree/main\n" +
                        "                          /data/releases\n" +
                        " -v,--verbose             Verbose\n"
        );
    }

    @Test
    void generate() throws Exception {
        String[] args = new String[]{"--models", "Thing"};
        String text = tapSystemOutNormalized(() -> {
            SchemaModelGeneratorApp.main(args);
        });
        Assertions.assertThat(text)
                .contains("Loading local resource '/schemaorg-current-https.jsonld'")
                .contains("Parsing the schema definitions...")
                .contains("Parsing completed.")
                .contains("Generating models...")
                .contains("Model generation completed.")
                .containsPattern("Finished: \\d+ s");
    }
}
