package com.weedow.schemaorg.generator.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

import java.util.List;
import java.util.Map;

import static com.weedow.schemaorg.generator.logging.LoggingConstants.*;
import static java.util.Map.entry;

@SuppressWarnings("java:S110") // Heritage hierarchy depth is deep due to Logback framework
public class HighlightConverter extends ForegroundCompositeConverterBase<ILoggingEvent> {

    private static final List<String> YELLOW_KEYWORDS = List.of(
            DEPRECATED,
            ARCHIVED,
            COULD_NOT_CREATE_DIRECTORY,
            COULD_NOT_WRITE_PROPERTIES_FILE
    );
    private static final List<String> RED_KEYWORDS = List.of(
            PARSING_ERROR,
            MODEL_DIRECTORY_NOT_CREATED,
            MODEL_IMPL_DIRECTORY_NOT_CREATED,
            DATA_TYPE_DIRECTORY_NOT_CREATED,
            COPY_ERROR
    );
    private static final List<String> SUCCESS_KEYWORDS = List.of(FINISHED, COMPLETED);

    private static final Map<String, String> EMOJI_MAP = Map.ofEntries(
    entry(LOADING_RESOURCE, "📂"),
    entry(DOWNLOADING_VERSION, "📥"),
    entry(LOADING_LOCAL_DEFAULT_RESOURCE, "📦"),
    entry(CUSTOM_DATA_TYPES_CONFIGURED, "🏷️"),
    entry(JAVA_TYPES_USED, "💬"),
    entry(PARSING_SCHEMA_DEFINITIONS, "🔍"),
    entry(PARSING_COMPLETED, "🏁"),
    entry(PARSING_ERROR, "⚠️"),
    entry(NO_SCHEMA_MODEL_FOUND, "👻"),
    entry(MODEL_DIRECTORY_NOT_CREATED, "❌"),
    entry(MODEL_IMPL_DIRECTORY_NOT_CREATED, "❌"),
    entry(DATA_TYPE_DIRECTORY_NOT_CREATED, "❌"),
    entry(COULD_NOT_WRITE_PROPERTIES_FILE, "⚠️"),
    entry(COULD_NOT_WRITE_OUTPUT_FILE, "⚠️"),
    entry(COPYING_COMMON_MODELS, "📑"),
    entry(COPY_ERROR, "⚠️"),
    entry(GENERATING_MODELS, "⚙️"),
    entry(FINISHED, "⏱️"),
    entry(COMPLETED, "✅"),
    entry(DEPRECATED, "⚠️"),
    entry(ARCHIVED, "📜")
);

    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        String message = event.getMessage();

        if (message == null) return ANSIConstants.DEFAULT_FG;

        if(message.equals(VERBOSE_MODE_ON)) {
            return ANSIConstants.BOLD + ANSIConstants.WHITE_FG;
        }

        if (YELLOW_KEYWORDS.stream().anyMatch(message::contains)) {
            return ANSIConstants.YELLOW_FG;
        }

        if (RED_KEYWORDS.stream().anyMatch(message::contains)) {
            return ANSIConstants.RED_FG;
        }

        if (SUCCESS_KEYWORDS.stream().anyMatch(message::contains)) {
            return ANSIConstants.BOLD + ANSIConstants.GREEN_FG;
        }

        return ANSIConstants.DEFAULT_FG;
    }

    @Override
    protected String transform(ILoggingEvent event, String in) {
        String message = event.getMessage();

        if (message == null) return super.transform(event, in);

        return EMOJI_MAP.entrySet().stream()
                .filter(entry -> message.contains(entry.getKey()))
                .findFirst()
                .map(entry -> entry.getValue() + " " + super.transform(event, in))
                .orElse(super.transform(event, in));
    }
}