package com.weedow.schemaorg.generator.logging;

import ch.qos.logback.core.pattern.color.ANSIConstants;
import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;

import java.util.Optional;
import java.util.function.Supplier;

import static ch.qos.logback.core.pattern.color.ANSIConstants.*;

public class ProgressTracker {

    private static final Logger LOG = LoggerFactory.getLogger("PROGRESS_BAR");

    private static final Supplier<String> DEFAULT_INIT = () -> "Initializing...";
    private static final Supplier<String> DEFAULT_COMPLETED = () -> "✔ Completed";

    private static final int BAR_SIZE = 40;

    // \r: Return to beginning of line, \u001B[K: Erase to end of line
    public static final String RESET = "\r\u001B[K";

    private static final String PROGRESS_BAR_COLOR_FG = ESC_START + ANSIConstants.RESET + "38;5;39" + ESC_END;
    private static final String PROGRESS_BAR_COLOR_BG = ESC_START + ANSIConstants.RESET + "38;5;244" + ESC_END;
    private static final String PERCENT_INFO_COLOR = ESC_START + ANSIConstants.RESET + DEFAULT_FG + ESC_END;
    private static final String INIT_COLOR = ESC_START + ANSIConstants.RESET + DEFAULT_FG + ESC_END;
    private static final String COMPLETED_COLOR = ESC_START + ANSIConstants.RESET + "38;5;70" + ESC_END;
    private static final String TASK_COLOR = ESC_START + ANSIConstants.RESET + WHITE_FG + ESC_END;
    private static final String SEPARATOR_COLOR = ESC_START + ANSIConstants.RESET + WHITE_FG + ESC_END;

    private static final ProgressBarColors DEFAULT_PROGRESS_BAR_COLORS = new ProgressBarColors(PROGRESS_BAR_COLOR_FG, PROGRESS_BAR_COLOR_BG, PERCENT_INFO_COLOR, TASK_COLOR);
    private static final ProgressBarColors INIT_PROGRESS_BAR_COLORS = new ProgressBarColors(PROGRESS_BAR_COLOR_FG, PROGRESS_BAR_COLOR_BG, PERCENT_INFO_COLOR, INIT_COLOR);
    private static final ProgressBarColors COMPLETED_PROGRESS_BAR_COLORS = new ProgressBarColors(PROGRESS_BAR_COLOR_FG, PROGRESS_BAR_COLOR_BG, PERCENT_INFO_COLOR, COMPLETED_COLOR);

    private final int total;
    private final Supplier<String> init;
    private final Supplier<String> completed;

    private int current = 0;
    private int lastPercent = -1;

    public ProgressTracker(int total, Supplier<String> init, Supplier<String> completed) {
        this.total = total;
        this.init = Optional.ofNullable(init).orElse(DEFAULT_INIT);
        this.completed = Optional.ofNullable(completed).orElse(DEFAULT_COMPLETED);

        init();
    }

    public ProgressTracker(int total) {
        this(total, null, null);
    }

    private synchronized void init() {
        printBar(0, init.get(), INIT_PROGRESS_BAR_COLORS);
    }

    private synchronized void complete() {
        printBar(100, completed.get(), COMPLETED_PROGRESS_BAR_COLORS);
        LOG.info("\n");
    }

    public synchronized void tick(String currentTask) {
        current++;
        int percent = (int) (((double) current / total) * 100);

        // The console is only refreshed if the percentage has increased.
        if (percent > lastPercent || current == total) {
            lastPercent = percent;
            printBar(percent, currentTask, DEFAULT_PROGRESS_BAR_COLORS);

            if (current == total) {
                complete();
            }
        }
    }

    private void printBar(int percent, String currentTask, ProgressBarColors colors) {
        if (SchemaModelGeneratorConstants.isVerbose()) {
            return;
        }

        int completedBars = (int) ((percent / 100.0) * BAR_SIZE);

        StringBuilder sb = new StringBuilder(RESET);
        sb.append(colors.fgColor);

        for (int i = 0; i < BAR_SIZE; i++) {
            if (i == completedBars) sb.append(colors.bgColor);
            sb.append(i < completedBars ? "█" : "▒");
        }

        sb.append(colors.percentInfoColor).append(String.format(" %d%% (%d/%d)", percent, current, total));

        if (currentTask != null && !currentTask.isEmpty()) {
            sb.append(SEPARATOR_COLOR).append(" > ");
            sb.append(colors.taskColor).append(currentTask);
        }

        LOG.info(sb.toString());
    }

    private record ProgressBarColors(String fgColor, String bgColor, String percentInfoColor, String taskColor) {
    }
}
