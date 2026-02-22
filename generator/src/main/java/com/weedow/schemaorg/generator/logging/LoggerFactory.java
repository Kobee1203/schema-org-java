package com.weedow.schemaorg.generator.logging;

public final class LoggerFactory {

    private LoggerFactory() {
    }

    public static Logger getLogger(Class<?> clazz) {
        return LoggerManager.getInstance().getLogger(clazz);
    }

    public static Logger getLogger(String loggerName) {
        return LoggerManager.getInstance().getLogger(loggerName);
    }
}
