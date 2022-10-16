package com.weedow.schemaorg.generator.logging;

import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;

public class LoggerImpl implements Logger {

    private final org.slf4j.Logger logger;

    public LoggerImpl(String name) {
        logger = org.slf4j.LoggerFactory.getLogger(name);
    }

    @Override
    public void verbose(String format, Object... arguments) {
        if (SchemaModelGeneratorConstants.isVerbose()) {
            info(format, arguments);
        }
    }

    @Override
    public void info(String format, Object... arguments) {
        logger.info(format, arguments);
    }

    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(format, arguments);
    }

    @Override
    public void error(String format, Object... arguments) {
        logger.error(format, arguments);
    }
}
