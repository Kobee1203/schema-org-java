package com.weedow.schemaorg.generator.logging;

import picocli.CommandLine;

public final class LoggingConstants {

    private LoggingConstants() {
    }

    /**
     * Returns {@code true} if ANSI escape codes should be emitted, {@code false} otherwise.
     *
     * @see CommandLine.Help.Ansi#enabled
     */
    public static boolean isAnsiEnabled() {
        return CommandLine.Help.Ansi.AUTO.enabled();
    }

    public static final String PARAM = "'{}'";

    public static final String VERBOSE_MODE_ON = "VERBOSE MODE: ON.";

    public static final String LOADING_RESOURCE = "Loading resource";
    public static final String DOWNLOADING_VERSION = "Downloading version";
    public static final String LOADING_LOCAL_DEFAULT_RESOURCE = "Loading local default resource";

    public static final String CUSTOM_DATA_TYPES_CONFIGURED = "Custom data Types configured";
    public static final String JAVA_TYPES_USED = "Java types are used instead of Schema.org Data Types.";

    public static final String PARSING_SCHEMA_DEFINITIONS = "Parsing the schema definitions...";
    public static final String PARSING_COMPLETED = "Parsing completed.";
    public static final String PARSING_ERROR = "Could not generate the schema models";

    public static final String DEPRECATED = "** DEPRECATED **";
    public static final String ARCHIVED = "** ARCHIVED **";

    public static final String NO_SCHEMA_MODEL_FOUND = "No schema model found to generate";

    public static final String COULD_NOT_CREATE_DIRECTORY = "Could not create directory";
    public static final String MODEL_DIRECTORY_NOT_CREATED = "Model directory does not exist and could not be created";
    public static final String MODEL_IMPL_DIRECTORY_NOT_CREATED = "Model Implementation directory does not exist and could not be created";
    public static final String DATA_TYPE_DIRECTORY_NOT_CREATED = "DataType directory does not exist and could not be created";

    public static final String COULD_NOT_WRITE_PROPERTIES_FILE = "Could not write the properties file to directory";
    public static final String COULD_NOT_WRITE_OUTPUT_FILE = "Could not write output file";

    public static final String COPYING_COMMON_MODELS = "Copying common models...";
    public static final String COPY_ERROR = "Could not read the resource";

    public static final String GENERATING_MODELS = "Generating models...";

    public static final String COMPLETED = "Model generation completed.";
    public static final String FINISHED = "Finished:";
}
