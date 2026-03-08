package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaGeneratorUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import uk.org.webcompere.systemstubs.security.SystemExit;

import java.util.List;

import static com.weedow.schemaorg.generator.logging.Emojis.TIMER;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOutNormalized;

@ExtendWith(SystemStubsExtension.class)
class SchemaModelGeneratorAppTest {

    @SystemStub
    private final SystemExit exit = new SystemExit();

    @AfterEach
    void tearDown() {
        SchemaGeneratorUtils.clearCache();
    }

    @ParameterizedTest
    @ValueSource(strings = {"--help", "-h"})
    void help(String option) throws Exception {
        String[] args = new String[]{option};
        String text = tapSystemOutNormalized(() -> exit.execute(() -> SchemaModelGeneratorApp.main(args)));
        Assertions.assertThat(exit.getExitCode()).isZero();
        Assertions.assertThat(text).isEqualTo(
                """
                        Usage: java -jar schema-org-generator-{version}-jar-with-dependencies.jar
                               [-hVjv] [-m=<models>]... [-o=<output>] [-r=<schemaResource>]
                               [-s=<schemaVersion>] [-D=<dataTypePackage>] [-I=<modelImplPackage>]
                               [-M=<modelPackage>] [-c=<TYPE=JAVA_TYPE>]... [-f=<Type>[:include|exclude]
                               [:prop1,prop2]]...
                        
                        A CLI tool to generate Java models and data types from Schema.org definitions.
                        
                          -h, --help              Show this help message and exit.
                          -V, --version           Print version information and exit.
                          -m, --models=<models>   list of models to be generated. If not specified, all
                                                    models will be generated.
                          -o, --output=<output>   Location of the output directory
                                                    Default: target/generated-sources/schemaorg
                          -r, --resource=<schemaResource>
                                                  Schema resource to be used: either a "classpath:"
                                                    pseudo URL, a "file:" URL, an URL or a plain file
                                                    path.
                          -s, --schema-version=<schemaVersion>
                                                  Schema version to be used: 'latest' to use the latest
                                                    version, or specific version (eg. 13.0). If not
                                                    specified, the generator uses the resource in the
                                                    JAR.
                          -D, --datatype-package=<dataTypePackage>
                                                  Package of the data types
                                                    Default: org.schema.model.datatype
                          -I, --model-impl-package=<modelImplPackage>
                                                  Package of the model implementations
                                                    Default: org.schema.model.impl
                          -M, --model-package=<modelPackage>
                                                  Package of the models
                                                    Default: org.schema.model.models
                          -j, --javatypes         Use Java types instead of schema.org DataTypes. If
                                                    not specified, schema.org DataTypes are used.
                          -c, --custom-datatypes=<TYPE=JAVA_TYPE>
                                                  Configures Java types to be used for Schema.org data
                                                    types during code generation (eg. DateTime=java.
                                                    time.ZonedDateTime)
                          -f, --filter=<Type>[:include|exclude][:prop1,prop2]
                                                  Filter properties or types. Format: <Type>[:
                                                    include|exclude][:prop1,prop2]. Default mode is
                                                    'exclude'.
                          -v, --verbose           Verbose mode. Helpful for troubleshooting.
                        
                        Please report issues at https://github.com/Kobee1203/schema-org-java/issues
                        """
        );
    }

    @Test
    void generate() throws Exception {
        String[] args = new String[]{"--models", "Thing"};
        String text = tapSystemOutNormalized(() -> exit.execute(() -> SchemaModelGeneratorApp.main(args)));
        Assertions.assertThat(text)
                .contains("Loading local default resource 'classpath:schemaorg-current-https.jsonld'")
                .contains("Parsing the schema definitions...")
                .contains("Parsing completed.")
                .contains("Model IDs specified: [Thing]")
                .contains("Copying common models...")
                .contains("Generating models...")
                .contains("████████████████████████████████████████")
                .contains("100% (272/272)")
                .contains(" > ")
                .contains("✔ Completed")
                .containsPattern(TIMER.value() + " Finished: \\d+ s")
                .contains("Model generation completed.");
    }

    @Test
    void generate_custom_resource() throws Exception {
        String[] args = new String[]{
                "--resource", "classpath:example.jsonld",
                "-M", "org.custom.model",
                "-I", "org.custom.model.impl",
                "-D", "org.custom.model.datatype"
        };
        String text = tapSystemOutNormalized(() -> exit.execute(() -> SchemaModelGeneratorApp.main(args)));
        Assertions.assertThat(text)
                .doesNotContain("Java types are used instead of Schema.org Data Types.")
                .contains("Loading resource 'classpath:example.jsonld'")
                .contains("Parsing the schema definitions...")
                .contains("Parsing completed.")
                .contains("Copying common models...")
                .contains("Generating models...")
                .contains("████████████████████████████████████████")
                .contains("100% (14/14)")
                .contains(" > ")
                .contains("✔ Completed")
                .containsPattern(TIMER.value() + " Finished: \\d+ s")
                .contains("Model generation completed.");
    }

    @Test
    void generate_with_java_types() throws Exception {
        String[] args = new String[]{
                "--resource", "classpath:example.jsonld",
                "-M", "org.javatypes.models",
                "-I", "org.javatypes.models.impl",
                "-D", "org.javatypes.models.datatype",
                "--javatypes"
        };
        String text = tapSystemOutNormalized(() -> exit.execute(() -> SchemaModelGeneratorApp.main(args)));
        Assertions.assertThat(text)
                .contains("Java types are used instead of Schema.org Data Types.")
                .contains("Loading resource 'classpath:example.jsonld'")
                .contains("Parsing the schema definitions...")
                .contains("Parsing completed.")
                .contains("Copying common models...")
                .contains("Generating models...")
                .contains("████████████████████████████████████████")
                .contains("100% (13/13)")
                .contains(" > ")
                .contains("✔ Completed")
                .containsPattern(TIMER.value() + " Finished: \\d+ s")
                .contains("Model generation completed.");
    }

    @Test
    void generate_with_custom_data_types() throws Exception {
        String[] args = new String[]{
                "--resource", "classpath:example.jsonld",
                "-M", "org.custom_dt.models",
                "-I", "org.custom_dt.models.impl",
                "-D", "org.custom_dt.models.datatype",
                "-c", "DateTime=java.time.ZonedDateTime XPathType=javax.xml.xpath.XPath"
        };
        String text = tapSystemOutNormalized(() -> exit.execute(() -> SchemaModelGeneratorApp.main(args)));
        Assertions.assertThat(text)
                .contains("Custom data Types configured: DateTime=java.time.ZonedDateTime, XPathType=javax.xml.xpath.XPath")
                .contains("Loading resource 'classpath:example.jsonld'")
                .contains("Parsing the schema definitions...")
                .contains("Parsing completed.")
                .contains("Copying common models...")
                .contains("Generating models...")
                .contains("████████████████████████████████████████")
                .contains("100% (14/14)")
                .contains(" > ")
                .contains("✔ Completed")
                .containsPattern(TIMER.value() + " Finished: \\d+ s")
                .contains("Model generation completed.");
    }

    @Test
    void generate_with_custom_data_types_and_java_types() throws Exception {
        String[] args = new String[]{
                "--resource", "classpath:example.jsonld",
                "-M", "org.custom_dt_jt.models",
                "-I", "org.custom_dt_jt.models.impl",
                "-D", "org.custom_dt_jt.models.datatype",
                "--custom-datatypes", "schema:DateTime=java.time.ZonedDateTime XPathType=javax.xml.xpath.XPath",
                "-j"
        };
        String text = tapSystemOutNormalized(() -> exit.execute(() -> SchemaModelGeneratorApp.main(args)));
        Assertions.assertThat(text)
                .contains("Custom data Types configured: schema:DateTime=java.time.ZonedDateTime, XPathType=javax.xml.xpath.XPath")
                .contains("Java types are used instead of Schema.org Data Types.")
                .contains("Loading resource 'classpath:example.jsonld'")
                .contains("Parsing the schema definitions...")
                .contains("Parsing completed.")
                .contains("Copying common models...")
                .contains("Generating models...")
                .contains("████████████████████████████████████████")
                .contains("100% (13/13)")
                .contains(" > ")
                .contains("✔ Completed")
                .containsPattern(TIMER.value() + " Finished: \\d+ s")
                .contains("Model generation completed.");
    }

    @Test
    void generate_with_filters() throws Exception {
        String[] args = new String[]{
                "-M", "org.filters.models",
                "-I", "org.filters.models.impl",
                "-D", "org.filters.models.datatype",
                "-m", "WebPage",
                "--filter", "Thing:exclude:additionalType,alternateName,potentialAction,sameAs,schema:subject*",
                "-f", "Organization:include:*Policy,*award*",
                "-f", "CreativeWork:*"
                // "-f", "MediaObject"
        };
        String text = tapSystemOutNormalized(() -> exit.execute(() -> SchemaModelGeneratorApp.main(args)));
        Assertions.assertThat(text)
                .contains("Loading local default resource 'classpath:schemaorg-current-https.jsonld'")
                .contains("Parsing the schema definitions...")
                .contains("Parsing completed.")
                .contains("Filtering properties: EXCLUDE [additionalType, alternateName, potentialAction, sameAs, schema:subject*] from schema:Thing")
                .contains("Filtering properties: INCLUDE [*Policy, *award*] from schema:Organization")
                .contains("Filtering properties: EXCLUDE [*] from schema:CreativeWork")
                .contains("Model IDs specified: [WebPage]")
                .contains("Copying common models...")
                .contains("Generating models...")
                .contains("████████████████████████████████████████")
                .contains("100% (240/240)")
                .contains(" > ")
                .contains("✔ Completed")
                .containsPattern(TIMER.value() + " Finished: \\d+ s")
                .contains("Model generation completed.");
    }
}
