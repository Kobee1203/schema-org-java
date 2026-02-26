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
}
