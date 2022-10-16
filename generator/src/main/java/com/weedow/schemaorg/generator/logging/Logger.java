package com.weedow.schemaorg.generator.logging;

public interface Logger {

    void verbose(String format, Object... arguments);

    void info(String format, Object... arguments);

    void warn(String format, Object... arguments);

    void error(String format, Object... arguments);
}
