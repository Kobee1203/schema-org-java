package com.weedow.schemaorg.generator;

import com.weedow.schemaorg.generator.logging.LogMarkers;
import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;

/**
 * Constants and configuration utilities for the Schema.org model generator.
 * <p>
 * Provides methods to control verbose logging mode for the generator.
 */
public final class SchemaModelGeneratorConstants {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaModelGeneratorConstants.class);

    private SchemaModelGeneratorConstants() {
    }

    /** System property key for enabling verbose mode. */
    static final String VERBOSE_KEY = "com.weedow.shemaorg.generator.verbose";

    /**
     * Enables or disables verbose logging mode.
     * <p>
     * When enabled, the generator will output detailed debug information.
     *
     * @param verbose {@code true} to enable verbose mode, {@code false} to disable
     */
    public static void setVerbose(boolean verbose) {
        if (verbose) {
            System.setProperty(SchemaModelGeneratorConstants.VERBOSE_KEY, String.valueOf(verbose));
            LOG.info(LogMarkers.VERBOSE, "VERBOSE MODE: ON.");
        } else {
            System.clearProperty(SchemaModelGeneratorConstants.VERBOSE_KEY);
        }
    }

    /**
     * Checks whether verbose logging mode is currently enabled.
     *
     * @return {@code true} if verbose mode is enabled, {@code false} otherwise
     */
    public static boolean isVerbose() {
        return Boolean.parseBoolean(System.getProperty(VERBOSE_KEY, Boolean.FALSE.toString()));
    }
}
