package com.weedow.schemaorg.generator.logging;

import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.io.PrintStream;
import java.util.List;

import static com.weedow.schemaorg.generator.logging.ProgressTracker.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, SystemStubsExtension.class})
class ProgressTrackerTest {

    private PrintStream originalOut;

    private PrintStream mockOut;
    
    @BeforeEach
    void setUp() {
        originalOut = System.out;
        mockOut = mockSystemOut();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    // -------------------------------------------------------------------------
    // Constructor tests
    // -------------------------------------------------------------------------

    @Test
    void constructor_withDefaultSuppliers_shouldLogInitMessage() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            new ProgressTracker(10);

            List<String> prints = capturePrintCalls(mockOut);
            assertThat(prints).hasSize(2);
            assertThat(prints).first().asString().isEqualTo(HIDE_CURSOR + "\n");
            assertThat(prints).element(1).asString()
                    .isEqualTo(expectedMsg(0, 40, "0% (0/10)", DEFAULT_INIT.get(), INIT_COLOR));
        }
    }

    @Test
    void constructor_withCustomSuppliers_shouldLogCustomInitMessage() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            new ProgressTracker(5, () -> "Custom init", () -> "Custom done");

            List<String> prints = capturePrintCalls(mockOut);
            assertThat(prints).hasSize(2);
            assertThat(prints).first().asString().isEqualTo(HIDE_CURSOR + "\n");
            assertThat(prints).element(1).asString()
                    .isEqualTo(expectedMsg(0, 40, "0% (0/5)", "Custom init", INIT_COLOR));
        }
    }

    @Test
    void constructor_withNullSuppliers_shouldFallbackToDefaults() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            new ProgressTracker(5, null, null);

            List<String> prints = capturePrintCalls(mockOut);
            assertThat(prints).hasSize(2);
            assertThat(prints).first().asString().isEqualTo(HIDE_CURSOR + "\n");
            assertThat(prints).element(1).asString()
                    .isEqualTo(expectedMsg(0, 40, "0% (0/5)", DEFAULT_INIT.get(), INIT_COLOR));
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
            reset(mockOut);

            tracker.tick("Step 1");

            List<String> prints = capturePrintCalls(mockOut);
            assertThat(prints)
                    .hasSize(1)
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
            reset(mockOut);

            tracker.tick(currentTask);

            List<String> prints = capturePrintCalls(mockOut);
            assertThat(prints)
                    .hasSize(1)
                    .first().asString()
                    .doesNotContain(">")
                    .isEqualTo(expectedMsg(10, 30, "25% (1/4)", currentTask, TASK_COLOR));
        }
    }

    @Test
    void tick_lastStep_shouldLogCompletedMessageAndShowCursor() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            ProgressTracker tracker = new ProgressTracker(1);
            reset(mockOut);

            tracker.tick("Final step");

            List<String> prints = capturePrintCalls(mockOut);
            assertThat(prints).hasSize(3);
            assertThat(prints)
                    .first().asString()
                    .isEqualTo(expectedMsg(40, 0, "100% (1/1)", "Final step", TASK_COLOR));
            assertThat(prints)
                    .element(1).asString()
                    .isEqualTo(expectedMsg(40, 0, "100% (1/1)", DEFAULT_COMPLETED.get(), COMPLETED_COLOR));
            assertThat(prints)
                    .element(2).asString()
                    .isEqualTo(SHOW_CURSOR + "\n\n");
        }
    }

    @Test
    void tick_withCustomCompleted_shouldLogCustomCompletedMessage() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            ProgressTracker tracker = new ProgressTracker(1, null, () -> "Done!");
            reset(mockOut);

            tracker.tick("Last");

            List<String> prints = capturePrintCalls(mockOut);
            assertThat(prints).hasSize(3);
            assertThat(prints)
                    .first().asString()
                    .isEqualTo(expectedMsg(40, 0, "100% (1/1)", "Last", TASK_COLOR));
            assertThat(prints)
                    .element(1).asString()
                    .isEqualTo(expectedMsg(40, 0, "100% (1/1)", "Done!", COMPLETED_COLOR));
            assertThat(prints)
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
            reset(mockOut);

            for (int i = 1; i <= total; i++) {
                tracker.tick("Step " + i);
            }

            List<String> prints = capturePrintCalls(mockOut);
            assertThat(prints).hasSize(7);

            assertThat(prints)
                    .first().asString()
                    .isEqualTo(expectedMsg(8, 32, "20% (1/5)", "Step 1", TASK_COLOR));
            assertThat(prints)
                    .element(1).asString()
                    .isEqualTo(expectedMsg(16, 24, "40% (2/5)", "Step 2", TASK_COLOR));
            assertThat(prints)
                    .element(2).asString()
                    .isEqualTo(expectedMsg(24, 16, "60% (3/5)", "Step 3", TASK_COLOR));
            assertThat(prints)
                    .element(3).asString()
                    .isEqualTo(expectedMsg(32, 8, "80% (4/5)", "Step 4", TASK_COLOR));
            assertThat(prints)
                    .element(4).asString()
                    .isEqualTo(expectedMsg(40, 0, "100% (5/5)", "Step 5", TASK_COLOR));
            assertThat(prints)
                    .element(5).asString()
                    .isEqualTo(expectedMsg(40, 0, "100% (5/5)", DEFAULT_COMPLETED.get(), COMPLETED_COLOR));
            assertThat(prints)
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
            reset(mockOut);

            // 2 ticks on 1000 → both are 0%, second should not produce a new print
            tracker.tick("tick 1"); // 0%
            // Verify first tick printed
            verify(mockOut, atLeastOnce()).print(expectedMsg(0, 40, "0% (1/1000)", "tick 1", TASK_COLOR));
            reset(mockOut);

            tracker.tick("tick 2"); // still 0%

            // Second tick should not call print() since percentage didn't change
            verify(mockOut, never()).print(anyString());
        }
    }

    // -------------------------------------------------------------------------
    // Verbose mode tests
    // -------------------------------------------------------------------------

    @Test
    void whenVerbose_init_shouldNotLogProgressBar() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(true);

            new ProgressTracker(5);

            verify(mockOut, never()).print(anyString());
        }
    }

    @Test
    void whenVerbose_tick_shouldNotLog() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(true);

            ProgressTracker tracker = new ProgressTracker(3);
            reset(mockOut);

            tracker.tick("Some task");

            verify(mockOut, never()).print(anyString());
        }
    }

    @Test
    void whenVerbose_lastTick_shouldNotLog() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(true);

            ProgressTracker tracker = new ProgressTracker(1);
            reset(mockOut);

            tracker.tick("Final");

            verify(mockOut, never()).print(anyString());
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

            // Verify print was called (no exception should have been thrown)
            List<String> prints = capturePrintCalls(mockOut);

            // Should have at least 100 progress bars + 1 completed message + 1 show cursor
            assertThat(prints).hasSize(102);

            assertThat(prints).first().asString().isEqualTo(HIDE_CURSOR + "\n");
            assertThat(prints).last().asString().isEqualTo(SHOW_CURSOR + "\n\n");

            // Should contain the 100% progress and completed message
            assertThat(prints).anySatisfy(log ->
                    assertThat(log).isEqualTo(expectedMsg(40, 0, "100% (100/100)", DEFAULT_COMPLETED.get(), COMPLETED_COLOR)));
        }
    }

    // -------------------------------------------------------------------------
    // Progress bar formatting tests
    // -------------------------------------------------------------------------

    @Test
    void tick_shouldIncludeProgressBarCharacters() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            ProgressTracker tracker = new ProgressTracker(2);
            reset(mockOut);

            tracker.tick("Step 1");

            List<String> prints = capturePrintCalls(mockOut);
            assertThat(prints)
                    .first().asString()
                    .contains("█") // Filled bar character
                    .contains("▒"); // Empty bar character
        }
    }

    @Test
    void tick_at100Percent_shouldShowFullBar() {
        try (MockedStatic<SchemaModelGeneratorConstants> mocked = mockStatic(SchemaModelGeneratorConstants.class)) {
            mocked.when(SchemaModelGeneratorConstants::isVerbose).thenReturn(false);

            ProgressTracker tracker = new ProgressTracker(1);
            reset(mockOut);

            tracker.tick("Complete");

            List<String> prints = capturePrintCalls(mockOut);
            assertThat(prints)
                    .first().asString()
                    .contains("100% (1/1)")
                    .contains("█") // Should have filled bar
                    .doesNotContain("▒"); // Should not have empty bar at 100%
        }
    }

    /**
     * Helper method to mock System.out and return the mock for verification.
     */
    private static PrintStream mockSystemOut() {
        PrintStream mockOut = mock(PrintStream.class);
        System.setOut(mockOut);
        return mockOut;
    }

    /**
     * Helper method to capture all print() calls from the mock.
     */
    private static List<String> capturePrintCalls(PrintStream mockOut) {
        ArgumentCaptor<String> printCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockOut, atLeastOnce()).print(printCaptor.capture());
        return printCaptor.getAllValues();
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