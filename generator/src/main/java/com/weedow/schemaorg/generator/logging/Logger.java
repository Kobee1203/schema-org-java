package com.weedow.schemaorg.generator.logging;

import org.slf4j.Marker;

/**
 * Logger interface providing logging methods with emoji support.
 */
public interface Logger {

    /**
     * Logs a verbose message.
     *
     * @param format the message format
     * @param arguments the message arguments
     */
    void verbose(String format, Object... arguments);

    /**
     * Logs a verbose message with an emoji.
     *
     * @param emoji the emoji prefix
     * @param format the message format
     * @param arguments the message arguments
     */
    void verbose(Emoji emoji, String format, Object... arguments);

    /**
     * Logs an info message with a marker.
     *
     * @param marker the SLF4J marker
     * @param format the message format
     * @param arguments the message arguments
     */
    void info(Marker marker, String format, Object... arguments);

    /**
     * Logs an info message with a marker and emoji.
     *
     * @param marker the SLF4J marker
     * @param emoji the emoji prefix
     * @param format the message format
     * @param arguments the message arguments
     */
    void info(Marker marker, Emoji emoji, String format, Object... arguments);

    /**
     * Logs an info message.
     *
     * @param format the message format
     * @param arguments the message arguments
     */
    void info(String format, Object... arguments);

    /**
     * Logs an info message with an emoji.
     *
     * @param emoji the emoji prefix
     * @param format the message format
     * @param arguments the message arguments
     */
    void info(Emoji emoji, String format, Object... arguments);

    /**
     * Logs a success message.
     *
     * @param format the message format
     * @param arguments the message arguments
     */
    void success(String format, Object... arguments);

    /**
     * Logs a success message with an emoji.
     *
     * @param emoji the emoji prefix
     * @param format the message format
     * @param arguments the message arguments
     */
    void success(Emoji emoji, String format, Object... arguments);

    /**
     * Logs a warning message.
     *
     * @param format the message format
     * @param arguments the message arguments
     */
    void warn(String format, Object... arguments);

    /**
     * Logs a warning message with an emoji.
     *
     * @param emoji the emoji prefix
     * @param format the message format
     * @param arguments the message arguments
     */
    void warn(Emoji emoji, String format, Object... arguments);

    /**
     * Logs an error message.
     *
     * @param format the message format
     * @param arguments the message arguments
     */
    void error(String format, Object... arguments);

    /**
     * Logs an error message with an emoji.
     *
     * @param emoji the emoji prefix
     * @param format the message format
     * @param arguments the message arguments
     */
    void error(Emoji emoji, String format, Object... arguments);
}
