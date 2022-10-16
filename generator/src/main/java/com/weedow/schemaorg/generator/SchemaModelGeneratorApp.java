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

    public static void main(String[] args) throws ParseException {
        // Options
        final Options firstOptions = configFirstParameters();
        final Options options = configParameters(firstOptions);

        // Parsing help
        final CommandLineParser parser = new DefaultParser();
        final CommandLine firstLine = parser.parse(firstOptions, args, true);

        boolean helpMode = firstLine.hasOption("help");
        if (helpMode) {
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar schema-org-generator.jar SchemaModelGeneratorApp", options, true);
            System.exit(0);
        }

        // Parsing options
        final CommandLine line = parser.parse(options, args);

        // Verbose
        final boolean verboseMode = line.hasOption("verbose");

        // Timer
        final boolean timerMode = verboseMode || line.hasOption("timer");

        final String schemaVersion = line.getOptionValue("version", null);

        List<String> models = line.hasOption("models") ? Arrays.asList(line.getOptionValues("models")) : null;

        generate(schemaVersion, models, verboseMode);
    }

    private static void generate(String schemaVersion, List<String> models, boolean verboseMode) {
        long start = System.currentTimeMillis();

        ParserOptions parserOptions = new ParserOptions();
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
                .longOpt("help")
                .desc("Show the help message")
                .build();

        final Options firstOptions = new Options();

        firstOptions.addOption(helpFileOption);

        return firstOptions;
    }

    private static Options configParameters(final Options firstOptions) {
        final Option modelOption = Option.builder("m")
                .longOpt("models")
                .desc("list of models to be generated. If not specified, all models will be generated.")
                .hasArgs()
                .argName("models")
                .required(false)
                .build();

        final Option versionOption = Option.builder("V")
                .longOpt("version")
                .desc("Schema version to be used: 'latest' to use the latest version, or specific version (eg. 13.0). If not specified, the generator uses the resource in the JAR. see https://github.com/schemaorg/schemaorg/tree/main/data/releases")
                .hasArg()
                .argName("version")
                .required(false)
                .build();

        final Option timerOption = Option.builder("t")
                .longOpt("timer")
                .desc("Timer")
                .hasArg(false)
                .required(false)
                .build();

        final Option verboseOption = Option.builder("v")
                .longOpt("verbose")
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
        options.addOption(versionOption);
        options.addOption(timerOption);
        options.addOption(verboseOption);

        return options;
    }

}
