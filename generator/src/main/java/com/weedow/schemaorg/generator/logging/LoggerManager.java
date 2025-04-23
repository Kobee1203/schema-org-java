package com.weedow.schemaorg.generator.logging;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("java:S6548")
public class LoggerManager {

    private static final LoggerManager INSTANCE = new LoggerManager();

    private final Map<String, Logger> loggerCache = new ConcurrentHashMap<>();

    private LoggerManager() {
    }

    public static LoggerManager getInstance() {
        return INSTANCE;
    }

    public final Logger getLogger(final Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public Logger getLogger(final String name) {
        return loggerCache.computeIfAbsent(name, LoggerManager::newLogger);
    }

    private static Logger newLogger(String name) {
        return new LoggerImpl(name);
    }
}
