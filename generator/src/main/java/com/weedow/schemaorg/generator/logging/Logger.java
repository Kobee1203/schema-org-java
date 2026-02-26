package com.weedow.schemaorg.generator.logging;

import org.slf4j.Marker;

public interface Logger {

    void verbose(String format, Object... arguments);

    void verbose(Emoji emoji, String format, Object... arguments);

    void info(Marker marker, String format, Object... arguments);

    void info(Marker marker, Emoji emoji, String format, Object... arguments);

    void info(String format, Object... arguments);

    void info(Emoji emoji, String format, Object... arguments);

    void success(String format, Object... arguments);

    void success(Emoji emoji, String format, Object... arguments);

    void warn(String format, Object... arguments);

    void warn(Emoji emoji, String format, Object... arguments);

    void error(String format, Object... arguments);

    void error(Emoji emoji, String format, Object... arguments);
}
