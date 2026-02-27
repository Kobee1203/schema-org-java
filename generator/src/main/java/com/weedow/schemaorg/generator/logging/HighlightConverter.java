package com.weedow.schemaorg.generator.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;
import lombok.NoArgsConstructor;
import org.slf4j.Marker;

import java.util.List;
import java.util.Optional;

import static com.weedow.schemaorg.generator.logging.LogMarkers.*;

/**
 * Logback converter that applies color highlighting to log messages based on log level and markers.
 */
@NoArgsConstructor
@SuppressWarnings("java:S110") // Heritage hierarchy depth is deep due to Logback framework
public class HighlightConverter extends ForegroundCompositeConverterBase<ILoggingEvent> {

    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        String message = event.getMessage();

        if (message == null) return ANSIConstants.DEFAULT_FG;

        Level level = event.getLevel();
        for (Marker marker : Optional.ofNullable(event.getMarkerList()).orElse(List.of())) {
            if (marker.contains(VERBOSE)) {
                return ANSIConstants.WHITE_FG;
            }
            if (marker.contains(SUCCESS.getName())) {
                return ANSIConstants.BOLD + ANSIConstants.GREEN_FG;
            }
            if (marker.contains(WARNING.getName())) {
                return ANSIConstants.YELLOW_FG;
            }
            if (marker.contains(ERROR.getName())) {
                return level.toInt() == Level.INFO_INT ? ANSIConstants.MAGENTA_FG : ANSIConstants.RED_FG;
            }
        }

        // Fallback to log level
        return switch (level.toInt()) {
            case Level.ERROR_INT -> ANSIConstants.RED_FG;
            case Level.WARN_INT -> ANSIConstants.YELLOW_FG;
            default -> ANSIConstants.DEFAULT_FG;
        };
    }
}