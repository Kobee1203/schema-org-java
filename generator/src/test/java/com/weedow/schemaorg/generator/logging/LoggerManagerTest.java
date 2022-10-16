package com.weedow.schemaorg.generator.logging;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LoggerManagerTest {

    @Test
    void getLogger() {
        Logger logger = LoggerManager.getInstance().getLogger(LoggerManagerTest.class);
        Assertions.assertThat(logger).isNotNull();

        Logger result = LoggerManager.getInstance().getLogger(LoggerManagerTest.class);
        Assertions.assertThat(result).isSameAs(logger);
    }
}