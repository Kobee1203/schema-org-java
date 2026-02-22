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

import java.util.List;
import java.util.function.Supplier;

import static com.weedow.schemaorg.generator.logging.ProgressTracker.*;
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

            List<String> infoLogs = logCaptor.getInfoLogs();
            assertThat(infoLogs).hasSize(2);
            assertThat(infoLogs).first().asString().isEqualTo(HIDE_CURSOR + "\n");
            assertThat(infoLogs).element(1).asString()
                    .isEqualTo(expectedMsg(0, 40, "0% (0/10)", DEFAULT_INIT.get(), TASK_COLOR));
        }
    }

    @Test
    void constructor_withCustomSuppliers_shouldLogCustomInitMessage() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            Supplier<String> customInit = () -> "Custom init";
            Supplier<String> customCompleted = () -> "Custom done";

            new ProgressTracker(5, customInit, customCompleted);

            List<String> infoLogs = logCaptor.getInfoLogs();
            assertThat(infoLogs).hasSize(2);
            assertThat(infoLogs).first().asString().isEqualTo(HIDE_CURSOR + "\n");
            assertThat(infoLogs).element(1).asString()
                    .isEqualTo(expectedMsg(0, 40, "0% (0/5)", "Custom init", TASK_COLOR));
        }
    }

    @Test
    void constructor_withNullSuppliers_shouldFallbackToDefaults() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            new ProgressTracker(5, null, null);

            List<String> infoLogs = logCaptor.getInfoLogs();
            assertThat(infoLogs).hasSize(2);
            assertThat(infoLogs).first().asString().isEqualTo(HIDE_CURSOR + "\n");
            assertThat(infoLogs).element(1).asString()
                    .isEqualTo(expectedMsg(0, 40, "0% (0/5)", DEFAULT_INIT.get(), TASK_COLOR));
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
                    .isEqualTo(expectedMsg(10, 30, "25% (1/4)", "Step 1", TASK_COLOR));
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
                    .isEqualTo(expectedMsg(10, 30, "25% (1/4)", currentTask, TASK_COLOR));
        }
    }

    @Test
    void tick_lastStep_shouldLogCompletedMessageAndShowCursorCodeWithNewline() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            ProgressTracker tracker = new ProgressTracker(1);
            logCaptor.clearLogs();

            tracker.tick("Final step");

            assertThat(logCaptor.getInfoLogs()).hasSize(3);

            assertThat(logCaptor.getInfoLogs())
                    .first().asString()
                    .isEqualTo(expectedMsg(40, 0, "100% (1/1)", "Final step", TASK_COLOR));

            assertThat(logCaptor.getInfoLogs())
                    .element(1).asString()
                    .isEqualTo(expectedMsg(40, 0, "100% (1/1)", DEFAULT_COMPLETED.get(), COMPLETED_COLOR));

            assertThat(logCaptor.getInfoLogs())
                    .element(2).asString()
                    .isEqualTo(SHOW_CURSOR + "\n\n");
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
                    .isEqualTo(expectedMsg(40, 0, "100% (1/1)", "Last", TASK_COLOR));

            assertThat(logCaptor.getInfoLogs())
                    .element(1).asString()
                    .isEqualTo(expectedMsg(40, 0, "100% (1/1)", "Done!", COMPLETED_COLOR));

            assertThat(logCaptor.getInfoLogs())
                    .element(2).asString()
                    .isEqualTo(SHOW_CURSOR + "\n\n");
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
                    .isEqualTo(expectedMsg(8, 32, "20% (1/5)", "Step 1", TASK_COLOR));

            assertThat(logCaptor.getInfoLogs())
                    .element(1).asString()
                    .isEqualTo(expectedMsg(16, 24, "40% (2/5)", "Step 2", TASK_COLOR));

            assertThat(logCaptor.getInfoLogs())
                    .element(2).asString()
                    .isEqualTo(expectedMsg(24, 16, "60% (3/5)", "Step 3", TASK_COLOR));

            assertThat(logCaptor.getInfoLogs())
                    .element(3).asString()
                    .isEqualTo(expectedMsg(32, 8, "80% (4/5)", "Step 4", TASK_COLOR));

            assertThat(logCaptor.getInfoLogs())
                    .element(4).asString()
                    .isEqualTo(expectedMsg(40, 0, "100% (5/5)", "Step 5", TASK_COLOR));

            assertThat(logCaptor.getInfoLogs())
                    .element(5).asString()
                    .isEqualTo(expectedMsg(40, 0, "100% (5/5)", DEFAULT_COMPLETED.get(), COMPLETED_COLOR));

            assertThat(logCaptor.getInfoLogs())
                    .element(6).asString()
                    .isEqualTo(SHOW_CURSOR + "\n\n");
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
    void whenVerbose_init_shouldLogHideCursorCodeWithNewline() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(true);

            new ProgressTracker(5);

            assertThat(logCaptor.getInfoLogs())
                    .hasSize(1)
                    .first().asString()
                    .isEqualTo(HIDE_CURSOR + "\n");
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
    void whenVerbose_lastTick_shouldOnlyLogShowCursorCodeWithNewline() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(true);

            ProgressTracker tracker = new ProgressTracker(1);
            logCaptor.clearLogs();

            tracker.tick("Final");

            // complete() logs "\n\n" unconditionally (outside printBar)
            assertThat(logCaptor.getInfoLogs())
                    .hasSize(1)
                    .first().asString()
                    .isEqualTo(SHOW_CURSOR + "\n\n");
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
            List<String> infoLogs = logCaptor.getInfoLogs();
            assertThat(infoLogs).hasSize(102);
            assertThat(infoLogs).first().asString().isEqualTo(HIDE_CURSOR + "\n");
            assertThat(infoLogs).last().asString().isEqualTo(SHOW_CURSOR + "\n\n");
            assertThat(infoLogs).anySatisfy(log ->
                    assertThat(log).isEqualTo(expectedMsg(40, 0, "100% (100/100)", DEFAULT_COMPLETED.get(), COMPLETED_COLOR)));
        }
    }

    private static String expectedMsg(int fgBarCount, int bgBarCount, String percentInfo, String task, String taskColor) {
        return "\r\u001B[2K" +
                "\u001B[0;38;5;39m" + "█".repeat(fgBarCount) +
                (bgBarCount > 0 ? "\u001B[0;38;5;244m" + "▒".repeat(bgBarCount) : "") +
                "\u001B[0;39m" +
                "\u001B[0;39m " + percentInfo + "\u001B[0;39m" +
                (task != null && !task.isEmpty() ? "\u001B[0;37m > \u001B[0;39m" + "\u001B[0;" + taskColor + "m" + task + "\u001B[0;39m" : "") +
                " ";
    }
}