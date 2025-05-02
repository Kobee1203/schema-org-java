package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.apache.commons.cli.*;

import java.nio.file.Path;
import java.util.*;

public class SchemaModelGeneratorApp {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorApp.class);

    private static final String HELP_OPTION = "help";
    private static final String OUTPUT_OPTION = "output";
    private static final String MODEL_PACKAGE_OPTION = "model-package";
    private static final String MODEL_IMPL_PACKAGE_OPTION = "model-impl-package";
    private static final String DATATYPE_PACKAGE_OPTION = "datatype-package";
    private static final String RESOURCE_OPTION = "resource";
    private static final String VERSION_OPTION = "version";
    private static final String JAVATYPES_OPTION = "javatypes";
    private static final String CUSTOM_DATATYPES_OPTION = "custom-datatypes";
    private static final String MODELS_OPTION = "models";
    private static final String VERBOSE_OPTION = "verbose";

    private static final String PACKAGE_ARG_VALUE = "package";

    public static void main(String[] args) throws ParseException {
        // Options
        final Options firstOptions = configFirstParameters();
        final Options options = configParameters(firstOptions);

        // Parsing help
        final CommandLineParser parser = new DefaultParser();
        final CommandLine firstLine = parser.parse(firstOptions, args, true);

        boolean helpMode = firstLine.hasOption(HELP_OPTION);
        if (helpMode) {
            final HelpFormatter formatter = new HelpFormatter();
            formatter.setWidth(80);
            formatter.printHelp("java -jar schema-org-generator.jar SchemaModelGeneratorApp", options, true);
            return;
        }

        // Parsing options
        final CommandLine line = parser.parse(options, args);

        // Location of the output directory - Default is "/target/generated-sources/schemaorg"
        final String output = line.getOptionValue(OUTPUT_OPTION, GeneratorOptions.DEFAULT_OUTPUT_DIR.toAbsolutePath().toString());

        // Package of the models - Default is "org.schema.model"
        final String modelPackage = line.getOptionValue(MODEL_PACKAGE_OPTION, GeneratorOptions.DEFAULT_MODEL_PACKAGE);

        // Package of the model implementations - Default is "org.schema.model.impl"
        final String modelImplPackage = line.getOptionValue(MODEL_IMPL_PACKAGE_OPTION, GeneratorOptions.DEFAULT_MODEL_IMPL_PACKAGE);

        // Package of the data types - Default is "org.schema.model.datatype"
        final String dataTypePackage = line.getOptionValue(DATATYPE_PACKAGE_OPTION, GeneratorOptions.DEFAULT_DATE_TYPE_PACKAGE);

        // Models - if null all models will be generated
        List<String> models = line.hasOption(MODELS_OPTION) ? Arrays.asList(line.getOptionValues(MODELS_OPTION)) : null;

        // Java Types - If true Java types are used instead of schema.org DataTypes
        final boolean javaTypes = line.hasOption(JAVATYPES_OPTION);

        // Custom Data Types - Configures Java types to be used for Schema.org data types during code generation.
        Map<String, String> customDataTypes = new LinkedHashMap<>();
        if (line.hasOption(CUSTOM_DATATYPES_OPTION)) {
            String[] values = line.getOptionValues(CUSTOM_DATATYPES_OPTION);
            for (String mapping : values) {
                int separatorIndex = mapping.indexOf('=');
                if (separatorIndex > 0 && separatorIndex < mapping.length() - 1) {
                    String type = mapping.substring(0, separatorIndex);
                    String javaType = mapping.substring(separatorIndex + 1);
                    customDataTypes.put(type.trim(), javaType.trim());
                }
            }
        }


        // Schema resource location - if null use the 'version' option
        final String schemaResource = line.getOptionValue(RESOURCE_OPTION);

        // Schema version - if null the schema model resource into the JAR is used
        final String schemaVersion = line.getOptionValue(VERSION_OPTION);

        // Verbose
        final boolean verboseMode = line.hasOption(VERBOSE_OPTION);

        generate(
                output,
                modelPackage,
                modelImplPackage,
                dataTypePackage,
                schemaResource,
                schemaVersion,
                javaTypes,
                customDataTypes,
                models,
                verboseMode
        );
    }

    @SuppressWarnings("java:S107")
    private static void generate(
            String output,
            String modelPackage,
            String modelImplPackage,
            String dataTypePackage,
            String schemaResource,
            String schemaVersion,
            boolean javaTypes,
            Map<String, String> customDataTypes,
            List<String> models,
            boolean verboseMode
    ) {
        ParserOptions parserOptions = new ParserOptions()
                .setSchemaResource(schemaResource)
                .setSchemaVersion(schemaVersion)
                .setUsedJavaTypes(javaTypes)
                .setCustomDataTypes(customDataTypes);

        GeneratorOptions generatorOptions = new GeneratorOptions()
                .setOutputFolder(Path.of(output))
                .setModelPackage(modelPackage)
                .setModelImplPackage(modelImplPackage)
                .setDataTypePackage(dataTypePackage)
                .setModels(models)
                .addCompleteHandler(elapsedTime -> LOG.info("Finished: {} s", elapsedTime.toSeconds()));

        final SchemaModelGenerator generator = new SchemaModelGeneratorBuilder()
                .parserOptions(parserOptions)
                .generatorOptions(generatorOptions)
                .verbose(verboseMode)
                .build();
        generator.generate();
    }

    private static Options configFirstParameters() {
        final Option helpFileOption = Option.builder("h")
                .longOpt(HELP_OPTION)
                .desc("Show the help message")
                .build();

        final Options firstOptions = new Options();

        firstOptions.addOption(helpFileOption);

        return firstOptions;
    }

    private static Options configParameters(final Options firstOptions) {
        final Option outputOption = Option.builder("o")
                .longOpt(OUTPUT_OPTION)
                .desc("Location of the output directory - Default is \"/target/generated-sources/schemaorg\"")
                .hasArg()
                .argName(OUTPUT_OPTION)
                .required(false)
                .build();

        final Option modelPackageOption = Option.builder("mp")
                .longOpt(MODEL_PACKAGE_OPTION)
                .desc("Package of the models - Default is \"org.schema.model\"")
                .hasArg()
                .argName(PACKAGE_ARG_VALUE)
                .required(false)
                .build();

        final Option modelImplPackageOption = Option.builder("mip")
                .longOpt(MODEL_IMPL_PACKAGE_OPTION)
                .desc("Package of the model implementations - Default is \"org.schema.model.impl\"")
                .hasArg()
                .argName(PACKAGE_ARG_VALUE)
                .required(false)
                .build();

        final Option dataTypePackageOption = Option.builder("dp")
                .longOpt(DATATYPE_PACKAGE_OPTION)
                .desc("Package of the data types - Default is \"org.schema.model.datatype\"")
                .hasArg()
                .argName(PACKAGE_ARG_VALUE)
                .required(false)
                .build();

        final Option resourceOption = Option.builder("R")
                .longOpt(RESOURCE_OPTION)
                .desc("Schema resource to be used: either a \"classpath:\" pseudo URL, a \"file:\" URL, an URL or a plain file path.")
                .hasArg()
                .argName(RESOURCE_OPTION)
                .required(false)
                .build();

        final Option versionOption = Option.builder("V")
                .longOpt(VERSION_OPTION)
                .desc("Schema version to be used: 'latest' to use the latest version, or specific version (eg. 13.0). If not specified, the generator uses the resource in the JAR. see https://github.com/schemaorg/schemaorg/tree/main/data/releases")
                .hasArg()
                .argName(VERSION_OPTION)
                .required(false)
                .build();

        final Option javaTypesOption = Option.builder("j")
                .longOpt(JAVATYPES_OPTION)
                .desc("Use Java types instead of schema.org DataTypes. If not specified, schema.org DataTypes are used.")
                .hasArg(false)
                .required(false)
                .build();

        final Option customDataTypesOption = Option.builder("cd")
                .longOpt(CUSTOM_DATATYPES_OPTION)
                .desc("Configures Java types to be used for Schema.org data types during code generation (eg. DateTime=java.time.ZonedDateTime)")
                .hasArgs()
                .valueSeparator(' ')
                .argName("TYPE=JAVA_TYPE")
                .required(false)
                .build();

        final Option modelOption = Option.builder("m")
                .longOpt(MODELS_OPTION)
                .desc("list of models to be generated. If not specified, all models will be generated.")
                .hasArgs()
                .argName(MODELS_OPTION)
                .required(false)
                .build();

        final Option verboseOption = Option.builder("v")
                .longOpt(VERBOSE_OPTION)
                .desc("Verbose")
                .hasArg(false)
                .required(false)
                .build();

        final Options options = new Options();

        // First Options
        for (final Option fo : firstOptions.getOptions()) {
            options.addOption(fo);
        }

        // All other options
        options
                .addOption(outputOption)
                .addOption(modelPackageOption)
                .addOption(modelImplPackageOption)
                .addOption(dataTypePackageOption)
                .addOption(outputOption)
                .addOption(outputOption)
                .addOption(resourceOption)
                .addOption(versionOption)
                .addOption(javaTypesOption)
                .addOption(customDataTypesOption)
                .addOption(modelOption)
                .addOption(verboseOption);

        return options;
    }

}
