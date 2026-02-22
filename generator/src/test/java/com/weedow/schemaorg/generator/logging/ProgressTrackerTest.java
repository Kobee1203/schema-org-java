package com.weedow.schemaorg.generator.logging;

import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class ProgressTrackerTest {

    private LogCaptor logCaptor;

    @BeforeEach
    void setUp() {
        logCaptor = LogCaptor.forName("PROGRESS_BAR");
        logCaptor.setLogLevelToInfo();
    }

    @AfterEach
    void tearDown() {
        logCaptor.close();
    }

    // -------------------------------------------------------------------------
    // Constructor tests
    // -------------------------------------------------------------------------

    @Test
    void constructor_withDefaultSuppliers_shouldLogInitMessage() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            new ProgressTracker(10);

            assertThat(logCaptor.getInfoLogs())
                    .hasSize(1)
                    .first().asString()
                    .contains("0% (0/10)")
                    .endsWith("Initializing...");
        }
    }

    @Test
    void constructor_withCustomSuppliers_shouldLogCustomInitMessage() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            Supplier<String> customInit = () -> "Custom init";
            Supplier<String> customCompleted = () -> "Custom done";

            new ProgressTracker(5, customInit, customCompleted);

            assertThat(logCaptor.getInfoLogs())
                    .hasSize(1)
                    .first().asString()
                    .contains("0% (0/5)")
                    .endsWith("Custom init");
        }
    }

    @Test
    void constructor_withNullSuppliers_shouldFallbackToDefaults() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            new ProgressTracker(5, null, null);

            assertThat(logCaptor.getInfoLogs())
                    .hasSize(1)
                    .first().asString()
                    .contains("0% (0/5)")
                    .endsWith("Initializing...");
        }
    }

    // -------------------------------------------------------------------------
    // tick() tests
    // -------------------------------------------------------------------------

    @Test
    void tick_singleStep_shouldLogProgressBar() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            ProgressTracker tracker = new ProgressTracker(4);
            logCaptor.clearLogs();

            tracker.tick("Step 1");

            assertThat(logCaptor.getInfoLogs())
                    .isNotEmpty()
                    .first().asString()
                    .contains("25% (1/4)")
                    .endsWith("Step 1");
        }
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void tick_withoutTask_shouldNotIncludeTaskInBar(String currentTask) {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            ProgressTracker tracker = new ProgressTracker(4);
            logCaptor.clearLogs();

            tracker.tick(currentTask);

            assertThat(logCaptor.getInfoLogs())
                    .isNotEmpty()
                    .first().asString()
                    .doesNotContain(">")
                    .endsWith("25% (1/4)");
        }
    }

    @Test
    void tick_lastStep_shouldLogCompletedMessageAndNewline() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            ProgressTracker tracker = new ProgressTracker(1);
            logCaptor.clearLogs();

            tracker.tick("Final step");

            assertThat(logCaptor.getInfoLogs()).hasSize(3);

            assertThat(logCaptor.getInfoLogs())
                    .first().asString()
                    .contains("100% (1/1)")
                    .endsWith("Final step");

            assertThat(logCaptor.getInfoLogs())
                    .element(1).asString()
                    .contains("100% (1/1)")
                    .endsWith("✔ Completed");

            assertThat(logCaptor.getInfoLogs())
                    .element(2).asString()
                    .isEqualTo("\n");
        }
    }

    @Test
    void tick_withCustomCompleted_shouldLogCustomCompletedMessage() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            ProgressTracker tracker = new ProgressTracker(1, null, () -> "Done!");
            logCaptor.clearLogs();

            tracker.tick("Last");

            assertThat(logCaptor.getInfoLogs()).hasSize(3);

            assertThat(logCaptor.getInfoLogs())
                    .first().asString()
                    .contains("100% (1/1)")
                    .endsWith("Last");

            assertThat(logCaptor.getInfoLogs())
                    .element(1).asString()
                    .contains("100% (1/1)")
                    .endsWith("Done!");

            assertThat(logCaptor.getInfoLogs())
                    .element(2).asString()
                    .isEqualTo("\n");
        }
    }

    @Test
    void tick_allSteps_shouldReach100Percent() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            int total = 5;
            ProgressTracker tracker = new ProgressTracker(total);
            logCaptor.clearLogs();

            for (int i = 1; i <= total; i++) {
                tracker.tick("Step " + i);
            }

            assertThat(logCaptor.getInfoLogs()).hasSize(7);

            assertThat(logCaptor.getInfoLogs())
                    .first().asString()
                    .contains("20% (1/5)")
                    .endsWith("Step 1");

            assertThat(logCaptor.getInfoLogs())
                    .element(1).asString()
                    .contains("40% (2/5)")
                    .endsWith("Step 2");

            assertThat(logCaptor.getInfoLogs())
                    .element(2).asString()
                    .contains("60% (3/5)")
                    .endsWith("Step 3");

            assertThat(logCaptor.getInfoLogs())
                    .element(3).asString()
                    .contains("80% (4/5)")
                    .endsWith("Step 4");

            assertThat(logCaptor.getInfoLogs())
                    .element(4).asString()
                    .contains("100% (5/5)")
                    .endsWith("Step 5");

            assertThat(logCaptor.getInfoLogs())
                    .element(5).asString()
                    .contains("100% (5/5)")
                    .endsWith("✔ Completed");

            assertThat(logCaptor.getInfoLogs())
                    .element(6).asString()
                    .isEqualTo("\n");
        }
    }

    @Test
    void tick_percentageNotIncreased_shouldNotLogRedundantBar() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            // With total=1000, first few ticks won't change the integer percentage
            ProgressTracker tracker = new ProgressTracker(1000);
            logCaptor.clearLogs();

            // 2 ticks on 1000 → both are 0%, second should not produce a new log
            tracker.tick("tick 1"); // 0%
            int logsAfterFirst = logCaptor.getInfoLogs().size();
            tracker.tick("tick 2"); // still 0%
            int logsAfterSecond = logCaptor.getInfoLogs().size();

            assertThat(logsAfterSecond).isEqualTo(logsAfterFirst);
        }
    }

    // -------------------------------------------------------------------------
    // Verbose mode tests
    // -------------------------------------------------------------------------

    @Test
    void whenVerbose_init_shouldNotLog() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(true);

            new ProgressTracker(5);

            assertThat(logCaptor.getInfoLogs()).isEmpty();
        }
    }

    @Test
    void whenVerbose_tick_shouldNotLog() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(true);

            ProgressTracker tracker = new ProgressTracker(3);
            logCaptor.clearLogs();

            tracker.tick("Some task");

            // complete() calls LOG.info("\n") unconditionally when current == total
            // but tick() with current != total just calls printBar which returns early
            assertThat(logCaptor.getInfoLogs()).isEmpty();
        }
    }

    @Test
    void whenVerbose_lastTick_shouldOnlyLogNewline() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(true);

            ProgressTracker tracker = new ProgressTracker(1);
            logCaptor.clearLogs();

            tracker.tick("Final");

            // complete() logs "\n" unconditionally (outside printBar)
            assertThat(logCaptor.getInfoLogs())
                    .hasSize(1)
                    .containsExactly("\n");
        }
    }

    // -------------------------------------------------------------------------
    // Bar content structure tests
    // -------------------------------------------------------------------------

    @Test
    void printBar_shouldStartWithCarriageReturn() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            new ProgressTracker(10);

            assertThat(logCaptor.getInfoLogs())
                    .first().asString()
                    .startsWith("\r");
        }
    }

    @Test
    void printBar_shouldContainPercentCurrentAndTotal() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            ProgressTracker tracker = new ProgressTracker(10);
            logCaptor.clearLogs();

            tracker.tick("Task A");

            assertThat(logCaptor.getInfoLogs())
                    .first().asString()
                    .contains("10%")
                    .contains("1/10")
                    .contains("Task A");
        }
    }

    @Test
    void printBar_separatorShouldBePresentWhenTaskIsProvided() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            ProgressTracker tracker = new ProgressTracker(10);
            logCaptor.clearLogs();

            tracker.tick("My Task");

            assertThat(logCaptor.getInfoLogs())
                    .first().asString()
                    .contains(" > ")
                    .contains("My Task");
        }
    }

    // -------------------------------------------------------------------------
    // Concurrency / thread-safety smoke test
    // -------------------------------------------------------------------------

    @Test
    void tick_concurrentCalls_shouldNotThrow() throws InterruptedException {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            int total = 100;
            ProgressTracker tracker = new ProgressTracker(total);

            Thread[] threads = new Thread[total];
            for (int i = 0; i < total; i++) {
                final int idx = i;
                threads[i] = new Thread(() -> tracker.tick("Task " + idx));
            }

            for (Thread t : threads) t.start();
            for (Thread t : threads) t.join();

            // No exception should have been thrown; and the completed message must appear
            assertThat(logCaptor.getInfoLogs())
                    .anySatisfy(log -> assertThat(log).contains("✔ Completed"));
        }
    }
}