package com.weedow.schemaorg.generator.logging;

import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;
import org.slf4j.Marker;

import static com.weedow.schemaorg.generator.logging.LogMarkers.*;
import static com.weedow.schemaorg.generator.logging.LoggingUtils.msg;

/**
 * Implementation of the Logger interface using SLF4J.
 */
public class LoggerImpl implements Logger {

    private final org.slf4j.Logger logger;

    /**
     * Creates a new logger with the specified name.
     *
     * @param name the logger name
     */
    public LoggerImpl(String name) {
        logger = org.slf4j.LoggerFactory.getLogger(name);
    }

    @Override
    public void verbose(String format, Object... arguments) {
        if (SchemaModelGeneratorConstants.isVerbose()) {
            info(VERBOSE, format, arguments);
        }
    }

    @Override
    public void verbose(Emoji emoji, String format, Object... arguments) {
        verbose(msg(emoji, format), arguments);
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        logger.info(marker, format, arguments);
    }

    @Override
    public void info(Marker marker, Emoji emoji, String format, Object... arguments) {
        info(marker, msg(emoji, format), arguments);
    }

    @Override
    public void info(String format, Object... arguments) {
        logger.info(format, arguments);
    }

    @Override
    public void info(Emoji emoji, String format, Object... arguments) {
        info(msg(emoji, format), arguments);
    }

    @Override
    public void success(String format, Object... arguments) {
        logger.info(SUCCESS, format, arguments);
    }

    @Override
    public void success(Emoji emoji, String format, Object... arguments) {
        success(msg(emoji, format), arguments);
    }

    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(WARNING, format, arguments);
    }

    public void warn(Emoji emoji, String format, Object... arguments) {
        warn(msg(emoji, format), arguments);
    }

    @Override
    public void error(String format, Object... arguments) {
        logger.error(ERROR, format, arguments);
    }

    public void error(Emoji emoji, String format, Object... arguments) {
        error(msg(emoji, format), arguments);
    }
}
