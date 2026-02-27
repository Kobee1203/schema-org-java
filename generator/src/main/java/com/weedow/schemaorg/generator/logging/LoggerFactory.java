package com.weedow.schemaorg.generator.logging;

/**
 * Factory for creating and retrieving logger instances.
 */
public final class LoggerFactory {

    private LoggerFactory() {
    }

    /**
     * Returns a logger for the specified class.
     *
     * @param clazz the class for which to get a logger
     * @return the logger instance
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerManager.getInstance().getLogger(clazz);
    }

    /**
     * Returns a logger with the specified name.
     *
     * @param loggerName the name of the logger
     * @return the logger instance
     */
    public static Logger getLogger(String loggerName) {
        return LoggerManager.getInstance().getLogger(loggerName);
    }
}
