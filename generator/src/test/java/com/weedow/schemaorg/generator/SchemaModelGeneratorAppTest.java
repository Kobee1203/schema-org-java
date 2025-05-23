package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.core.SchemaGeneratorUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOutNormalized;

@ExtendWith(SystemStubsExtension.class)
class SchemaModelGeneratorAppTest {

    @AfterEach
    void tearDown() {
        SchemaGeneratorUtils.clearCache();
    }

    @ParameterizedTest
    @ValueSource(strings = {"--help", "-h"})
    void help(String option) throws Exception {
        String[] args = new String[]{option};
        String text = tapSystemOutNormalized(() -> SchemaModelGeneratorApp.main(args));
        Assertions.assertThat(text).isEqualTo(
                """
                        usage: java -jar schema-org-generator.jar SchemaModelGeneratorApp [-cd
                               <TYPE=JAVA_TYPE>] [-dp <package>] [-h] [-j] [-m <models>] [-mip
                               <package>] [-mp <package>] [-o <output>] [-R <resource>] [-V <version>]
                               [-v]
                         -cd,--custom-datatypes <TYPE=JAVA_TYPE>   Configures Java types to be used for
                                                                   Schema.org data types during code
                                                                   generation (eg.
                                                                   DateTime=java.time.ZonedDateTime)
                         -dp,--datatype-package <package>          Package of the data types - Default
                                                                   is "org.schema.model.datatype"
                         -h,--help                                 Show the help message
                         -j,--javatypes                            Use Java types instead of schema.org
                                                                   DataTypes. If not specified,
                                                                   schema.org DataTypes are used.
                         -m,--models <models>                      list of models to be generated. If
                                                                   not specified, all models will be
                                                                   generated.
                         -mip,--model-impl-package <package>       Package of the model implementations
                                                                   - Default is "org.schema.model.impl"
                         -mp,--model-package <package>             Package of the models - Default is
                                                                   "org.schema.model"
                         -o,--output <output>                      Location of the output directory -
                                                                   Default is
                                                                   "/target/generated-sources/schemaorg"
                         -R,--resource <resource>                  Schema resource to be used: either a
                                                                   "classpath:" pseudo URL, a "file:"
                                                                   URL, an URL or a plain file path.
                         -V,--version <version>                    Schema version to be used: 'latest'
                                                                   to use the latest version, or
                                                                   specific version (eg. 13.0). If not
                                                                   specified, the generator uses the
                                                                   resource in the JAR. see
                                                                   https://github.com/schemaorg/schemaor
                                                                   g/tree/main/data/releases
                         -v,--verbose                              Verbose
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
                "-mp", "org.custom.model",
                "-mip", "org.custom.model.impl",
                "-dp", "org.custom.model.datatype"
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
                "-mp", "org.javatypes.models",
                "-mip", "org.javatypes.models.impl",
                "-dp", "org.javatypes.models.datatype",
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

    @Test
    void generate_with_custom_data_types() throws Exception {
        String[] args = new String[]{
                "--resource", "classpath:example.jsonld",
                "-mp", "org.custom_dt.models",
                "-mip", "org.custom_dt.models.impl",
                "-dp", "org.custom_dt.models.datatype",
                "-cd", "DateTime=java.time.ZonedDateTime XPathType=javax.xml.xpath.XPath"
        };
        String text = tapSystemOutNormalized(() -> SchemaModelGeneratorApp.main(args));
        Assertions.assertThat(text)
                .contains("Custom data Types configured: DateTime=java.time.ZonedDateTime, XPathType=javax.xml.xpath.XPath")
                .contains("Loading resource 'classpath:example.jsonld'")
                .contains("Parsing the schema definitions...")
                .contains("Parsing completed.")
                .contains("Generating models...")
                .contains("Model generation completed.")
                .containsPattern("Finished: \\d+ s");
    }

    @Test
    void generate_with_custom_data_types_and_java_types() throws Exception {
        String[] args = new String[]{
                "--resource", "classpath:example.jsonld",
                "-mp", "org.custom_dt_jt.models",
                "-mip", "org.custom_dt_jt.models.impl",
                "-dp", "org.custom_dt_jt.models.datatype",
                "--custom-datatypes", "schema:DateTime=java.time.ZonedDateTime XPathType=javax.xml.xpath.XPath",
                "-j"
        };
        String text = tapSystemOutNormalized(() -> SchemaModelGeneratorApp.main(args));
        Assertions.assertThat(text)
                .contains("Custom data Types configured: schema:DateTime=java.time.ZonedDateTime, XPathType=javax.xml.xpath.XPath")
                .contains("Java types are used instead of Schema.org Data Types")
                .contains("Loading resource 'classpath:example.jsonld'")
                .contains("Parsing the schema definitions...")
                .contains("Parsing completed.")
                .contains("Generating models...")
                .contains("Model generation completed.")
                .containsPattern("Finished: \\d+ s");
    }
}
