package com.weedow.schemaorg.generator.logging;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * SLF4J Markers for specialized log categorization.
 */
public final class LogMarkers {

    private LogMarkers() {
    }

    /**
     * Marker for verbose messages.
     * Used to apply green color in console output.
     */
    public static final Marker VERBOSE = MarkerFactory.getMarker("VERBOSE");

    /**
     * Marker for success messages.
     * Used to apply green color in console output.
     */
    public static final Marker SUCCESS = MarkerFactory.getMarker("SUCCESS");

    /**
     * Marker for warning messages.
     * Used to apply yellow color in console output.
     */
    public static final Marker WARNING = MarkerFactory.getMarker("WARNING");

    /**
     * Marker for error messages.
     * Used to apply red color in console output.
     */
    public static final Marker ERROR = MarkerFactory.getMarker("ERROR");
}