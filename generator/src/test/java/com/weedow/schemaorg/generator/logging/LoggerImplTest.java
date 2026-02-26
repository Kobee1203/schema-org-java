package com.weedow.schemaorg.generator.logging;

import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;
import nl.altindag.log.LogCaptor;
import nl.altindag.log.model.LogEvent;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoggerImplTest {

    @Nested
    class InfoTests {

        @Test
        void info_logs_message_with_arguments() {
            try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
                Logger logger = new LoggerImpl(LoggerImplTest.class.getName());

                logger.info("Test message with arg: {}", "value");

                List<LogEvent> logEvents = logCaptor.getLogEvents();
                assertThat(logEvents).hasSize(1);

                LogEvent logEvent = logEvents.get(0);
                assertThat(logEvent.getMessage()).isEqualTo("Test message with arg: {}");
                assertThat(logEvent.getArguments()).containsExactly("value");
                assertThat(logEvent.getLevel()).isEqualTo("INFO");
            }
        }

        @Test
        void info_with_emoji_logs_formatted_message() {
            try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
                Logger logger = new LoggerImpl(LoggerImplTest.class.getName());
                Emoji emoji = Emoji.of("🎉");

                logger.info(emoji, "Success message: {}", "done");

                List<LogEvent> logEvents = logCaptor.getLogEvents();
                assertThat(logEvents).hasSize(1);

                LogEvent logEvent = logEvents.get(0);
                assertThat(logEvent.getMessage()).startsWith("🎉");
                assertThat(logEvent.getMessage()).contains("Success message: {}");
                assertThat(logEvent.getArguments()).containsExactly("done");
                assertThat(logEvent.getLevel()).isEqualTo("INFO");
            }
        }
    }

    @Nested
    class WarnTests {

        @Test
        void warn_logs_message_with_arguments() {
            try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
                Logger logger = new LoggerImpl(LoggerImplTest.class.getName());

                logger.warn("Warning message: {}", "issue");

                List<LogEvent> logEvents = logCaptor.getLogEvents();
                assertThat(logEvents).hasSize(1);

                LogEvent logEvent = logEvents.get(0);
                assertThat(logEvent.getMessage()).isEqualTo("Warning message: {}");
                assertThat(logEvent.getArguments()).containsExactly("issue");
                assertThat(logEvent.getLevel()).isEqualTo("WARN");
            }
        }

        @Test
        void warn_logs_message_with_throwable() {
            try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
                Logger logger = new LoggerImpl(LoggerImplTest.class.getName());
                IOException exception = new IOException("Test error");

                logger.warn("Warning with exception: {}", "value", exception);

                List<LogEvent> logEvents = logCaptor.getLogEvents();
                assertThat(logEvents).hasSize(1);

                LogEvent logEvent = logEvents.get(0);
                assertThat(logEvent.getMessage()).isEqualTo("Warning with exception: {}");
                assertThat(logEvent.getArguments()).containsExactly("value");
                assertThat(logEvent.getLevel()).isEqualTo("WARN");
                assertThat(logEvent.getThrowable()).isPresent();
                assertThat(logEvent.getThrowable().get())
                        .isInstanceOf(IOException.class)
                        .hasMessage("Test error");
            }
        }

        @Test
        void warn_with_emoji_logs_formatted_message() {
            try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
                Logger logger = new LoggerImpl(LoggerImplTest.class.getName());
                Emoji emoji = Emoji.of("⚠️");

                logger.warn(emoji, "Warning: {}", "something wrong");

                List<LogEvent> logEvents = logCaptor.getLogEvents();
                assertThat(logEvents).hasSize(1);

                LogEvent logEvent = logEvents.get(0);
                assertThat(logEvent.getMessage()).startsWith("⚠️");
                assertThat(logEvent.getMessage()).contains("Warning: {}");
                assertThat(logEvent.getArguments()).containsExactly("something wrong");
                assertThat(logEvent.getLevel()).isEqualTo("WARN");
            }
        }
    }

    @Nested
    class ErrorTests {

        @Test
        void error_logs_message_with_arguments() {
            try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
                Logger logger = new LoggerImpl(LoggerImplTest.class.getName());

                logger.error("Error message: {}", "failed");

                List<LogEvent> logEvents = logCaptor.getLogEvents();
                assertThat(logEvents).hasSize(1);

                LogEvent logEvent = logEvents.get(0);
                assertThat(logEvent.getMessage()).isEqualTo("Error message: {}");
                assertThat(logEvent.getArguments()).containsExactly("failed");
                assertThat(logEvent.getLevel()).isEqualTo("ERROR");
            }
        }

        @Test
        void error_logs_message_with_throwable() {
            try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
                Logger logger = new LoggerImpl(LoggerImplTest.class.getName());
                RuntimeException exception = new RuntimeException("Critical error");

                logger.error("Error with exception: {}", "value", exception);

                List<LogEvent> logEvents = logCaptor.getLogEvents();
                assertThat(logEvents).hasSize(1);

                LogEvent logEvent = logEvents.get(0);
                assertThat(logEvent.getMessage()).isEqualTo("Error with exception: {}");
                assertThat(logEvent.getArguments()).containsExactly("value");
                assertThat(logEvent.getLevel()).isEqualTo("ERROR");
                assertThat(logEvent.getThrowable()).isPresent();
                assertThat(logEvent.getThrowable().get())
                        .isInstanceOf(RuntimeException.class)
                        .hasMessage("Critical error");
            }
        }

        @Test
        void error_with_emoji_logs_formatted_message() {
            try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
                Logger logger = new LoggerImpl(LoggerImplTest.class.getName());
                Emoji emoji = Emoji.of("❌");

                logger.error(emoji, "Error: {}", "failed");

                List<LogEvent> logEvents = logCaptor.getLogEvents();
                assertThat(logEvents).hasSize(1);

                LogEvent logEvent = logEvents.get(0);
                assertThat(logEvent.getMessage()).startsWith("❌");
                assertThat(logEvent.getMessage()).contains("Error: {}");
                assertThat(logEvent.getArguments()).containsExactly("failed");
                assertThat(logEvent.getLevel()).isEqualTo("ERROR");
            }
        }
    }

    @Nested
    class VerboseTests {

        @Test
        void verbose_logs_when_verbose_mode_enabled() {
            boolean backupVerbose = SchemaModelGeneratorConstants.isVerbose();
            SchemaModelGeneratorConstants.setVerbose(true);

            try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
                Logger logger = new LoggerImpl(LoggerImplTest.class.getName());

                logger.verbose("Verbose message: {}", "debug");

                List<LogEvent> logEvents = logCaptor.getLogEvents();
                assertThat(logEvents).hasSize(1);

                LogEvent logEvent = logEvents.get(0);
                assertThat(logEvent.getMessage()).isEqualTo("Verbose message: {}");
                assertThat(logEvent.getArguments()).containsExactly("debug");
                assertThat(logEvent.getLevel()).isEqualTo("INFO");
            } finally {
                SchemaModelGeneratorConstants.setVerbose(backupVerbose);
            }
        }

        @Test
        void verbose_does_not_log_when_verbose_mode_disabled() {
            boolean backupVerbose = SchemaModelGeneratorConstants.isVerbose();
            SchemaModelGeneratorConstants.setVerbose(false);

            try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
                Logger logger = new LoggerImpl(LoggerImplTest.class.getName());

                logger.verbose("Verbose message: {}", "debug");

                List<LogEvent> logEvents = logCaptor.getLogEvents();
                assertThat(logEvents).isEmpty();
            } finally {
                SchemaModelGeneratorConstants.setVerbose(backupVerbose);
            }
        }

        @Test
        void verbose_with_emoji_logs_when_verbose_mode_enabled() {
            boolean backupVerbose = SchemaModelGeneratorConstants.isVerbose();
            SchemaModelGeneratorConstants.setVerbose(true);

            try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
                Logger logger = new LoggerImpl(LoggerImplTest.class.getName());
                Emoji emoji = Emoji.of("🔍");

                logger.verbose(emoji, "Debug info: {}", "details");

                List<LogEvent> logEvents = logCaptor.getLogEvents();
                assertThat(logEvents).hasSize(1);

                LogEvent logEvent = logEvents.get(0);
                assertThat(logEvent.getMessage()).startsWith("🔍");
                assertThat(logEvent.getMessage()).contains("Debug info: {}");
                assertThat(logEvent.getArguments()).containsExactly("details");
                assertThat(logEvent.getLevel()).isEqualTo("INFO");
            } finally {
                SchemaModelGeneratorConstants.setVerbose(backupVerbose);
            }
        }

        @Test
        void verbose_with_emoji_does_not_log_when_verbose_mode_disabled() {
            boolean backupVerbose = SchemaModelGeneratorConstants.isVerbose();
            SchemaModelGeneratorConstants.setVerbose(false);

            try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
                Logger logger = new LoggerImpl(LoggerImplTest.class.getName());
                Emoji emoji = Emoji.of("🔍");

                logger.verbose(emoji, "Debug info: {}", "details");

                List<LogEvent> logEvents = logCaptor.getLogEvents();
                assertThat(logEvents).isEmpty();
            } finally {
                SchemaModelGeneratorConstants.setVerbose(backupVerbose);
            }
        }
    }

    @Test
    void multiple_log_calls_produce_multiple_events() {
        try (LogCaptor logCaptor = LogCaptor.forClass(LoggerImplTest.class)) {
            Logger logger = new LoggerImpl(LoggerImplTest.class.getName());

            logger.info("First message");
            logger.warn("Second message");
            logger.error("Third message");

            List<LogEvent> logEvents = logCaptor.getLogEvents();
            assertThat(logEvents).hasSize(3);
            assertThat(logEvents).extracting(LogEvent::getLevel)
                    .containsExactly("INFO", "WARN", "ERROR");
        }
    }
}
