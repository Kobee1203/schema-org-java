package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SchemaModelGeneratorApp {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorApp.class);

    private static final String VERBOSE_OPTION = "verbose";
    private static final String RESOURCE_OPTION = "resource";
    private static final String VERSION_OPTION = "version";
    private static final String MODELS_OPTION = "models";
    private static final String HELP_OPTION = "help";

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
            formatter.printHelp("java -jar schema-org-generator.jar SchemaModelGeneratorApp", options, true);
            System.exit(0);
        }

        // Parsing options
        final CommandLine line = parser.parse(options, args);

        // Models - if null all models will be generated
        List<String> models = line.hasOption(MODELS_OPTION) ? Arrays.asList(line.getOptionValues(MODELS_OPTION)) : null;

        // Schema resource location - if null use the 'version' option
        final String schemaResource = line.getOptionValue(RESOURCE_OPTION, null);

        // Schema version - if null the schema model resource into the JAR is used
        final String schemaVersion = line.getOptionValue(VERSION_OPTION, null);

        // Verbose
        final boolean verboseMode = line.hasOption(VERBOSE_OPTION);

        generate(schemaResource, schemaVersion, models, verboseMode);
    }

    private static void generate(String schemaResource, String schemaVersion, List<String> models, boolean verboseMode) {
        long start = System.currentTimeMillis();

        ParserOptions parserOptions = new ParserOptions();
        parserOptions.setSchemaResource(schemaResource);
        parserOptions.setSchemaVersion(schemaVersion);

        GeneratorOptions generatorOptions = new GeneratorOptions();
        generatorOptions.setModels(models);

        final SchemaModelGenerator generator = new SchemaModelGeneratorBuilder()
                .parserOptions(parserOptions)
                .generatorOptions(generatorOptions)
                .verbose(verboseMode)
                .build();
        generator.generate();

        long end = System.currentTimeMillis();
        LOG.info("Finished: {} s", TimeUnit.SECONDS.convert(end - start, TimeUnit.MILLISECONDS));
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
        final Option modelOption = Option.builder("m")
                .longOpt(MODELS_OPTION)
                .desc("list of models to be generated. If not specified, all models will be generated.")
                .hasArgs()
                .argName(MODELS_OPTION)
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
        options.addOption(modelOption);
        options.addOption(resourceOption);
        options.addOption(versionOption);
        options.addOption(verboseOption);

        return options;
    }

}
