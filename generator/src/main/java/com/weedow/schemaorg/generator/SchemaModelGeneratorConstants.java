package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;

public final class SchemaModelGeneratorConstants {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorConstants.class);

    private SchemaModelGeneratorConstants() {
    }

    static final String VERBOSE_KEY = "com.weedow.shemaorg.generator.verbose";

    public static void setVerbose(boolean verbose) {
        if (verbose) {
            LOG.info("VERBOSE MODE: ON.");
            System.setProperty(SchemaModelGeneratorConstants.VERBOSE_KEY, String.valueOf(verbose));
        } else {
            System.clearProperty(SchemaModelGeneratorConstants.VERBOSE_KEY);
        }
    }

    public static boolean isVerbose() {
        return Boolean.parseBoolean(System.getProperty(VERBOSE_KEY, Boolean.FALSE.toString()));
    }
}
