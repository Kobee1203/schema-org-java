package com.weedow.schemaorg.generator.logging;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages logger instances with caching to avoid creating duplicate loggers.
 */
@SuppressWarnings("java:S6548")
public class LoggerManager {

    private static final LoggerManager INSTANCE = new LoggerManager();

    private final Map<String, Logger> loggerCache = new ConcurrentHashMap<>();

    private LoggerManager() {
    }

    /**
     * Returns the singleton instance of the logger manager.
     *
     * @return the logger manager instance
     */
    public static LoggerManager getInstance() {
        return INSTANCE;
    }

    /**
     * Returns a logger for the specified class.
     *
     * @param clazz the class for which to get a logger
     * @return the logger instance
     */
    public final Logger getLogger(final Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    /**
     * Returns a logger with the specified name.
     *
     * @param name the logger name
     * @return the logger instance
     */
    public Logger getLogger(final String name) {
        return loggerCache.computeIfAbsent(name, LoggerManager::newLogger);
    }

    private static Logger newLogger(String name) {
        return new LoggerImpl(name);
    }
}
