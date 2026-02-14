package com.weedow.schemaorg.generator.logging;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoggingConstantsTest {

    @Test
    void isAnsiEnabled() {
        String backup = System.getProperty("picocli.ansi");
        try {
            System.setProperty("picocli.ansi", "true");
            assertThat(LoggingConstants.isAnsiEnabled()).isTrue();

            System.setProperty("picocli.ansi", "false");
            assertThat(LoggingConstants.isAnsiEnabled()).isFalse();
        } finally {
            if (backup == null) {
                System.clearProperty("picocli.ansi");
            } else {
                System.setProperty("picocli.ansi", backup);
            }
        }
    }
}