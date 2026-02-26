package com.weedow.schemaorg.generator.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.weedow.schemaorg.generator.logging.LogMarkers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HighlightConverterTest {

    private HighlightConverter converter;

    @Mock
    private ILoggingEvent event;

    @BeforeEach
    void setUp() {
        converter = new HighlightConverter();
        // start() is required to initialize the composite converter chain
        converter.start();
    }

    @Test
    void getForegroundColorCode_withNullMessage_shouldReturnDefaultColor() {
        when(event.getMessage()).thenReturn(null);

        String colorCode = converter.getForegroundColorCode(event);

        assertThat(colorCode).isEqualTo(ANSIConstants.DEFAULT_FG);
    }

    @Test
    void getForegroundColorCode_withVerboseMarker_shouldReturnWhite() {
        when(event.getMessage()).thenReturn("Test message");
        when(event.getMarkerList()).thenReturn(List.of(VERBOSE));
        when(event.getLevel()).thenReturn(Level.INFO);

        String colorCode = converter.getForegroundColorCode(event);

        assertThat(colorCode).isEqualTo(ANSIConstants.WHITE_FG);
    }

    @Test
    void getForegroundColorCode_withSuccessMarker_shouldReturnBoldGreen() {
        when(event.getMessage()).thenReturn("Test message");
        when(event.getMarkerList()).thenReturn(List.of(SUCCESS));
        when(event.getLevel()).thenReturn(Level.INFO);

        String colorCode = converter.getForegroundColorCode(event);

        assertThat(colorCode).isEqualTo(ANSIConstants.BOLD + ANSIConstants.GREEN_FG);
    }

    @Test
    void getForegroundColorCode_withWarningMarker_shouldReturnYellow() {
        when(event.getMessage()).thenReturn("Test message");
        when(event.getMarkerList()).thenReturn(List.of(WARNING));
        when(event.getLevel()).thenReturn(Level.WARN);

        String colorCode = converter.getForegroundColorCode(event);

        assertThat(colorCode).isEqualTo(ANSIConstants.YELLOW_FG);
    }

    @Test
    void getForegroundColorCode_withErrorMarkerAndInfoLevel_shouldReturnMagenta() {
        when(event.getMessage()).thenReturn("Test message");
        when(event.getMarkerList()).thenReturn(List.of(ERROR));
        when(event.getLevel()).thenReturn(Level.INFO);

        String colorCode = converter.getForegroundColorCode(event);

        assertThat(colorCode).isEqualTo(ANSIConstants.MAGENTA_FG);
    }

    @Test
    void getForegroundColorCode_withErrorMarkerAndErrorLevel_shouldReturnRed() {
        when(event.getMessage()).thenReturn("Test message");
        when(event.getMarkerList()).thenReturn(List.of(ERROR));
        when(event.getLevel()).thenReturn(Level.ERROR);

        String colorCode = converter.getForegroundColorCode(event);

        assertThat(colorCode).isEqualTo(ANSIConstants.RED_FG);
    }

    @Test
    void getForegroundColorCode_withoutMarkerAndErrorLevel_shouldReturnRed() {
        when(event.getMessage()).thenReturn("Test message");
        when(event.getMarkerList()).thenReturn(null);
        when(event.getLevel()).thenReturn(Level.ERROR);

        String colorCode = converter.getForegroundColorCode(event);

        assertThat(colorCode).isEqualTo(ANSIConstants.RED_FG);
    }

    @Test
    void getForegroundColorCode_withoutMarkerAndWarnLevel_shouldReturnYellow() {
        when(event.getMessage()).thenReturn("Test message");
        when(event.getMarkerList()).thenReturn(null);
        when(event.getLevel()).thenReturn(Level.WARN);

        String colorCode = converter.getForegroundColorCode(event);

        assertThat(colorCode).isEqualTo(ANSIConstants.YELLOW_FG);
    }

    @Test
    void getForegroundColorCode_withoutMarkerAndInfoLevel_shouldReturnDefault() {
        when(event.getMessage()).thenReturn("Test message");
        when(event.getMarkerList()).thenReturn(null);
        when(event.getLevel()).thenReturn(Level.INFO);

        String colorCode = converter.getForegroundColorCode(event);

        assertThat(colorCode).isEqualTo(ANSIConstants.DEFAULT_FG);
    }

    @Test
    void getForegroundColorCode_withoutMarkerAndDebugLevel_shouldReturnDefault() {
        when(event.getMessage()).thenReturn("Test message");
        when(event.getMarkerList()).thenReturn(null);
        when(event.getLevel()).thenReturn(Level.DEBUG);

        String colorCode = converter.getForegroundColorCode(event);

        assertThat(colorCode).isEqualTo(ANSIConstants.DEFAULT_FG);
    }
}