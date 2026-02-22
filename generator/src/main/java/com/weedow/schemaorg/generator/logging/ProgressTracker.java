package com.weedow.schemaorg.generator.logging;

import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;

import java.util.Optional;
import java.util.function.Supplier;

import static ch.qos.logback.core.pattern.color.ANSIConstants.*;

public class ProgressTracker {

    private static final Logger LOG = LoggerFactory.getLogger("PROGRESS_BAR");

    protected static final Supplier<String> DEFAULT_INIT = () -> "Initializing...";
    protected static final Supplier<String> DEFAULT_COMPLETED = () -> "✔ Completed";

    private static final int BAR_SIZE = 40;

    // \r: Return to beginning of line, \u001B[2K: Erase the entire line
    protected static final String RESET_LINE = "\r" + ESC_START +"2K";

    protected static final String SET_DEFAULT_COLOR = ESC_START + RESET + DEFAULT_FG + ESC_END;

    protected static final String PROGRESS_BAR_COLOR_FG = "38;5;39";
    protected static final String PROGRESS_BAR_COLOR_BG = "38;5;244";
    protected static final String PERCENT_INFO_COLOR = DEFAULT_FG;
    protected static final String INIT_COLOR = WHITE_FG;
    protected static final String COMPLETED_COLOR = "38;5;70";
    protected static final String TASK_COLOR = WHITE_FG;
    protected static final String SEPARATOR_COLOR = WHITE_FG;

    private static final ProgressBarColors DEFAULT_PROGRESS_BAR_COLORS = new ProgressBarColors(PROGRESS_BAR_COLOR_FG, PROGRESS_BAR_COLOR_BG, PERCENT_INFO_COLOR, TASK_COLOR);
    private static final ProgressBarColors INIT_PROGRESS_BAR_COLORS = new ProgressBarColors(PROGRESS_BAR_COLOR_FG, PROGRESS_BAR_COLOR_BG, PERCENT_INFO_COLOR, INIT_COLOR);
    private static final ProgressBarColors COMPLETED_PROGRESS_BAR_COLORS = new ProgressBarColors(PROGRESS_BAR_COLOR_FG, PROGRESS_BAR_COLOR_BG, PERCENT_INFO_COLOR, COMPLETED_COLOR);

    protected static final String HIDE_CURSOR = ESC_START + "?25l";
    protected static final String SHOW_CURSOR = ESC_START + "?25h";

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
        LOG.info(HIDE_CURSOR + "\n");
        printBar(0, init.get(), INIT_PROGRESS_BAR_COLORS);
    }

    private synchronized void complete() {
        printBar(100, completed.get(), COMPLETED_PROGRESS_BAR_COLORS);
        LOG.info(SHOW_CURSOR + "\n\n");
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

        StringBuilder sb = new StringBuilder(RESET_LINE);

        sb.append(msg(colors.fgColor, () -> {
            StringBuilder bar = new StringBuilder();
            for (int i = 0; i < BAR_SIZE; i++) {
                if (i == completedBars) bar.append(applyColor(colors.bgColor));
                bar.append(i < completedBars ? "█" : "▒");
            }
            return bar.toString();
        }));

        sb.append(msg(colors.percentInfoColor, () -> String.format(" %d%% (%d/%d)", percent, current, total)));

        if (currentTask != null && !currentTask.isEmpty()) {
            sb.append(msg(SEPARATOR_COLOR, () -> " > "));
            sb.append(msg(colors.taskColor, () -> currentTask));
        }

        // Final space required for stable rendering in some terminals
        sb.append(" ");

        LOG.info(sb.toString());
    }

    private static String msg(String color, Supplier<String> msg) {
        return applyColor(color) + msg.get() + SET_DEFAULT_COLOR;
    }

    private static String applyColor(String color) {
        return ESC_START + RESET + color + ESC_END;
    }

    private record ProgressBarColors(String fgColor, String bgColor, String percentInfoColor, String taskColor) {
    }
}
