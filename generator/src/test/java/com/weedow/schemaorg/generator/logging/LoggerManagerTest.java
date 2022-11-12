package com.weedow.schemaorg.generator.logging;

import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;
import nl.altindag.log.LogCaptor;
import nl.altindag.log.model.LogEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class LoggerManagerTest {

    @Test
    void getLogger_with_verbose_mode() {
        boolean backupVerbose = SchemaModelGeneratorConstants.isVerbose();

        boolean verbose = true;
        SchemaModelGeneratorConstants.setVerbose(verbose);

        try (LogCaptor logCaptor = LogCaptor.forClass(LoggerManagerTest.class)) {
            Logger logger = LoggerManager.getInstance().getLogger(LoggerManagerTest.class);
            Assertions.assertThat(logger).isNotNull();

            Logger result = LoggerManager.getInstance().getLogger(LoggerManagerTest.class);
            Assertions.assertThat(result).isSameAs(logger); // Same instance

            logger.verbose("This is a verbose log: verbose={}", verbose);
            logger.info("This is an info log: verbose={}", verbose);
            logger.warn("This is a warn log: verbose={}", verbose, new IOException("Warning message"));
            logger.error("This is an error log: verbose={}", verbose, new IOException("Error message"));

            List<LogEvent> logEvents = logCaptor.getLogEvents();
            Assertions.assertThat(logEvents).hasSize(4);

            LogEvent logEvent1 = logEvents.get(0);
            Assertions.assertThat(logEvent1.getMessage()).isEqualTo("This is a verbose log: verbose={}");
            Assertions.assertThat(logEvent1.getArguments()).containsExactly(verbose);
            Assertions.assertThat(logEvent1.getLevel()).isEqualTo("INFO");
            Assertions.assertThat(logEvent1.getThrowable()).isNotPresent();

            LogEvent logEvent2 = logEvents.get(1);
            Assertions.assertThat(logEvent2.getMessage()).isEqualTo("This is an info log: verbose={}");
            Assertions.assertThat(logEvent2.getArguments()).containsExactly(verbose);
            Assertions.assertThat(logEvent2.getLevel()).isEqualTo("INFO");
            Assertions.assertThat(logEvent2.getThrowable()).isNotPresent();

            LogEvent logEvent3 = logEvents.get(2);
            Assertions.assertThat(logEvent3.getMessage()).isEqualTo("This is a warn log: verbose={}");
            Assertions.assertThat(logEvent3.getArguments()).containsExactly(verbose);
            Assertions.assertThat(logEvent3.getLevel()).isEqualTo("WARN");
            Assertions.assertThat(logEvent3.getThrowable()).isPresent();
            Assertions.assertThat(logEvent3.getThrowable().get())
                    .isInstanceOf(IOException.class)
                    .hasMessage("Warning message");

            LogEvent logEvent4 = logEvents.get(3);
            Assertions.assertThat(logEvent4.getMessage()).isEqualTo("This is an error log: verbose={}");
            Assertions.assertThat(logEvent4.getArguments()).containsExactly(verbose);
            Assertions.assertThat(logEvent4.getLevel()).isEqualTo("ERROR");
            Assertions.assertThat(logEvent4.getThrowable()).isPresent();
            Assertions.assertThat(logEvent4.getThrowable().get())
                    .isInstanceOf(IOException.class)
                    .hasMessage("Error message");
        } finally {
            SchemaModelGeneratorConstants.setVerbose(backupVerbose);
        }
    }

    @Test
    void getLogger_without_verbose_mode() {
        boolean backupVerbose = SchemaModelGeneratorConstants.isVerbose();

        boolean verbose = false;
        SchemaModelGeneratorConstants.setVerbose(verbose);

        try (LogCaptor logCaptor = LogCaptor.forClass(LoggerManagerTest.class)) {
            Logger logger = LoggerManager.getInstance().getLogger(LoggerManagerTest.class);
            Assertions.assertThat(logger).isNotNull();

            Logger result = LoggerManager.getInstance().getLogger(LoggerManagerTest.class);
            Assertions.assertThat(result).isSameAs(logger); // Same instance

            logger.verbose("This is a verbose log: verbose={}", verbose);
            logger.info("This is an info log: verbose={}", verbose);
            logger.warn("This is a warn log: verbose={}", verbose, new IOException("Warning message"));
            logger.error("This is an error log: verbose={}", verbose, new IOException("Error message"));

            List<LogEvent> logEvents = logCaptor.getLogEvents();
            Assertions.assertThat(logEvents).hasSize(3);

            LogEvent logEvent2 = logEvents.get(0);
            Assertions.assertThat(logEvent2.getMessage()).isEqualTo("This is an info log: verbose={}");
            Assertions.assertThat(logEvent2.getArguments()).containsExactly(verbose);
            Assertions.assertThat(logEvent2.getLevel()).isEqualTo("INFO");
            Assertions.assertThat(logEvent2.getThrowable()).isNotPresent();

            LogEvent logEvent3 = logEvents.get(1);
            Assertions.assertThat(logEvent3.getMessage()).isEqualTo("This is a warn log: verbose={}");
            Assertions.assertThat(logEvent3.getArguments()).containsExactly(verbose);
            Assertions.assertThat(logEvent3.getLevel()).isEqualTo("WARN");
            Assertions.assertThat(logEvent3.getThrowable()).isPresent();
            Assertions.assertThat(logEvent3.getThrowable().get())
                    .isInstanceOf(IOException.class)
                    .hasMessage("Warning message");

            LogEvent logEvent4 = logEvents.get(2);
            Assertions.assertThat(logEvent4.getMessage()).isEqualTo("This is an error log: verbose={}");
            Assertions.assertThat(logEvent4.getArguments()).containsExactly(verbose);
            Assertions.assertThat(logEvent4.getLevel()).isEqualTo("ERROR");
            Assertions.assertThat(logEvent4.getThrowable()).isPresent();
            Assertions.assertThat(logEvent4.getThrowable().get())
                    .isInstanceOf(IOException.class)
                    .hasMessage("Error message");
        } finally {
            SchemaModelGeneratorConstants.setVerbose(backupVerbose);
        }
    }
}