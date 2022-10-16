package com.weedow.schemaorg.generator.logging;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoggerFactoryTest {

    @Test
    void getLogger() {
        try(MockedStatic<LoggerManager> mockedStatic = Mockito.mockStatic(LoggerManager.class)) {
            Logger logger = mock(Logger.class);

            LoggerManager loggerManager = mock(LoggerManager.class);
            when(loggerManager.getLogger(LoggerFactoryTest.class)).thenReturn(logger);
            mockedStatic.when(LoggerManager::getInstance).thenReturn(loggerManager);

            Logger result = LoggerFactory.getLogger(LoggerFactoryTest.class);

            Assertions.assertThat(result).isSameAs(logger);
        }
    }
}