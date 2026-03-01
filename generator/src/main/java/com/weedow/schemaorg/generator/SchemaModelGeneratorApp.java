package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;
import com.weedow.schemaorg.generator.parser.ParserOptions;

import picocli.CommandLine;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static com.weedow.schemaorg.generator.logging.Emojis.TIMER;

/**
 * Command-line application for generating Java classes from Schema.org definitions.
 * <p>
 * This application uses picocli to provide a user-friendly CLI interface for configuring
 * and running the Schema.org Java code generator. It supports options for:
 * <ul>
 *   <li>Selecting specific models to generate or generating all models</li>
 *   <li>Choosing Schema.org versions or custom resources</li>
 *   <li>Configuring output packages and directories</li>
 *   <li>Using Java standard types or Schema.org data types</li>
 *   <li>Custom type mappings for data types</li>
 *   <li>Verbose logging for troubleshooting</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>
 * java -jar schema-org-generator-{version}-jar-with-dependencies.jar \
 *   --models Person Organization \
 *   --output target/generated-sources \
 *   --schema-version 15.0 \
 *   --javatypes
 * </pre>
 */

@picocli.CommandLine.Command(
        name = "java -jar schema-org-generator-{version}-jar-with-dependencies.jar",
        descriptionHeading = "%n",
        description = "@|bold,cyan A CLI tool to generate Java models and data types from Schema.org definitions.|@",
        footer = "@|faint Please report issues at https://github.com/Kobee1203/schema-org-java/issues|@",
        optionListHeading = "%n",
        mixinStandardHelpOptions = true,
        usageHelpAutoWidth = true,
        showDefaultValues = true,
        sortOptions = false,
        sortSynopsis = false,
        footerHeading = "%n",
        versionProvider = VersionProvider.class
)
public class SchemaModelGeneratorApp implements Callable<Integer> {

    /** Default constructor */
    public SchemaModelGeneratorApp() {
        // empty
    }

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorApp.class);

    @picocli.CommandLine.Option(names = {"-m", "--models"}, description = "list of models to be generated. If not specified, all models will be generated.", order = 0)
    private List<String> models;

    @picocli.CommandLine.Option(names = {"-o", "--output"}, description = "Location of the output directory (default: target/generated-sources/schemaorg)", order = 1)
    private String output;

    @picocli.CommandLine.Option(names = {"-r", "--resource"}, description = "Schema resource to be used: either a \"classpath:\" pseudo URL, a \"file:\" URL, an URL or a plain file path.", order = 2)
    private String schemaResource;

    @picocli.CommandLine.Option(names = {"-s", "--schema-version"}, description = "Schema version to be used: 'latest' to use the latest version, or specific version (eg. 13.0). If not specified, the generator uses the resource in the JAR.", order = 3)
    private String schemaVersion;

    @picocli.CommandLine.Option(names = {"-D", "--datatype-package"}, description = "Package of the data types", defaultValue = "org.schema.model.datatype", order = 4)
    private String dataTypePackage;

    @picocli.CommandLine.Option(names = {"-I", "--model-impl-package"}, description = "Package of the model implementations", defaultValue = "org.schema.model.impl", order = 5)
    private String modelImplPackage;

    @picocli.CommandLine.Option(names = {"-M", "--model-package"}, description = "Package of the models", defaultValue = "org.schema.model.models", order = 6)
    private String modelPackage;

    @picocli.CommandLine.Option(names = {"-j", "--javatypes"}, description = "Use Java types instead of schema.org DataTypes. If not specified, schema.org DataTypes are used.", order = 7)
    private boolean javaTypes;

    @picocli.CommandLine.Option(names = {"-c", "--custom-datatypes"}, description = "Configures Java types to be used for Schema.org data types during code generation (eg. DateTime=java.time.ZonedDateTime)", split = " ", paramLabel = "<TYPE=JAVA_TYPE>", order = 8)
    private Map<String, String> customDataTypes;

    @picocli.CommandLine.Option(names = {"-v", "--verbose"}, description = "Verbose mode. Helpful for troubleshooting.", order = 9)
    private boolean verboseMode;

    /**
     * Main entry point for the CLI application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        picocli.CommandLine commandLine = new picocli.CommandLine(new SchemaModelGeneratorApp());
        commandLine.setColorScheme(picocli.CommandLine.Help.defaultColorScheme(picocli.CommandLine.Help.Ansi.AUTO));

        int exitCode = commandLine.execute(args);

        System.exit(exitCode);
    }

    /**
     * Executes the code generation based on provided command-line options.
     *
     * @return exit code (0 for success)
     * @throws Exception if generation fails
     */
    @Override
    public Integer call() throws Exception {
        GeneratorOptions generatorOptions = new GeneratorOptions()
                .setOutputFolder(Path.of(output != null ? output : GeneratorOptions.DEFAULT_OUTPUT_DIR.toAbsolutePath().toString()))
                .setModelPackage(modelPackage)
                .setModelImplPackage(modelImplPackage)
                .setDataTypePackage(dataTypePackage)
                .setModels(models)
                .addCompleteHandler(elapsedTime -> LOG.success(TIMER, "Finished:" + " {} s", elapsedTime.toSeconds()));

        ParserOptions parserOptions = new ParserOptions()
                .setSchemaResource(schemaResource)
                .setSchemaVersion(schemaVersion)
                .setUsedJavaTypes(javaTypes)
                .setCustomDataTypes(customDataTypes);

        final SchemaModelGenerator generator = new SchemaModelGeneratorBuilder()
                .parserOptions(parserOptions)
                .generatorOptions(generatorOptions)
                .verbose(verboseMode)
                .build();
        generator.generate();

        return 0;
    }
}

/**
 * Version provider for picocli that reads version information from properties file.
 */
class VersionProvider implements CommandLine.IVersionProvider {

    /**
     * Retrieves the application version from the version.properties resource file.
     *
     * @return array containing the version string
     * @throws Exception if an error occurs (caught and ignored, returns "unknown")
     */
    @Override
    public String[] getVersion() throws Exception {
        String version = "unknown";
        java.util.Properties properties = new java.util.Properties();
        try (java.io.InputStream is = getClass().getResourceAsStream("/version.properties")) {
            if (is != null) {
                properties.load(is);
                version = properties.getProperty("version", "unknown");
            }
        } catch (java.io.IOException e) {
            // ignore
        }
        return new String[]{version};
    }
}
