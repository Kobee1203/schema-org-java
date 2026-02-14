package com.weedow.schemaorg.generator.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static ch.qos.logback.core.pattern.color.ANSIConstants.*;
import static ch.qos.logback.core.pattern.color.ANSIConstants.ESC_END;
import static com.weedow.schemaorg.generator.logging.LoggingConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HighlightConverterTest {

    private static final String RESET_DEFAULT_COLOR = ESC_START + RESET + DEFAULT_FG + ESC_END;

    private HighlightConverter converter;

    @Mock
    private ILoggingEvent event;

    @BeforeEach
    void setUp() {
        converter = new HighlightConverter();
        // start() is required to initialize the composite converter chain
        converter.start();
    }

    // =========================================================================
    // getForegroundColorCode()
    // =========================================================================

    @Nested
    class GetForegroundColorCode {

        @Test
        void shouldReturnDefaultFg_whenMessageIsNull() {
            when(event.getMessage()).thenReturn(null);

            String color = converter.getForegroundColorCode(event);

            assertThat(color).isEqualTo(ANSIConstants.DEFAULT_FG);
        }

        @ParameterizedTest
        @ValueSource(strings = {"Some unrelated message", "", "   "})
        void shouldReturnDefaultFg_whenMessageMatchesNoKeyword(String message) {
            when(event.getMessage()).thenReturn(message);

            String color = converter.getForegroundColorCode(event);

            assertThat(color).isEqualTo(ANSIConstants.DEFAULT_FG);
        }

        @Test
        void shouldReturnBoldWhite_whenMessageIsVerboseModeOn() {
            when(event.getMessage()).thenReturn(VERBOSE_MODE_ON);

            String color = converter.getForegroundColorCode(event);

            assertThat(color).isEqualTo(ANSIConstants.BOLD + ANSIConstants.WHITE_FG);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                DEPRECATED,
                ARCHIVED,
                COULD_NOT_CREATE_DIRECTORY,
                COULD_NOT_WRITE_PROPERTIES_FILE
        })
        void shouldReturnYellow_forYellowKeyword(String keyword) {
            when(event.getMessage()).thenReturn("Some prefix " + keyword + " some suffix");

            String color = converter.getForegroundColorCode(event);

            assertThat(color).isEqualTo(ANSIConstants.YELLOW_FG);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                PARSING_ERROR,
                MODEL_DIRECTORY_NOT_CREATED,
                MODEL_IMPL_DIRECTORY_NOT_CREATED,
                DATA_TYPE_DIRECTORY_NOT_CREATED,
                COPY_ERROR
        })
        void shouldReturnRed_forRedKeyword(String keyword) {
            when(event.getMessage()).thenReturn("Error context: " + keyword);

            String color = converter.getForegroundColorCode(event);

            assertThat(color).isEqualTo(ANSIConstants.RED_FG);
        }

        @ParameterizedTest
        @ValueSource(strings = {FINISHED, COMPLETED})
        void shouldReturnBoldGreen_forSuccessKeyword(String keyword) {
            when(event.getMessage()).thenReturn(keyword + " in 1.23s");

            String color = converter.getForegroundColorCode(event);

            assertThat(color).isEqualTo(ANSIConstants.BOLD + ANSIConstants.GREEN_FG);
        }

        @Test
        void shouldReturnYellow_whenMessageContainsBothYellowAndSuccessKeywords() {
            // YELLOW_KEYWORDS are checked before SUCCESS_KEYWORDS
            when(event.getMessage()).thenReturn(DEPRECATED + " " + FINISHED);

            String color = converter.getForegroundColorCode(event);

            assertThat(color).isEqualTo(ANSIConstants.YELLOW_FG);
        }

        @Test
        void shouldReturnRed_whenMessageContainsBothRedAndSuccessKeywords() {
            // RED_KEYWORDS are checked before SUCCESS_KEYWORDS
            when(event.getMessage()).thenReturn(PARSING_ERROR + " " + COMPLETED);

            String color = converter.getForegroundColorCode(event);

            assertThat(color).isEqualTo(ANSIConstants.RED_FG);
        }

        @Test
        void shouldReturnYellow_whenMessageContainsBothYellowAndRedKeywords() {
            // YELLOW_KEYWORDS are checked before RED_KEYWORDS
            when(event.getMessage()).thenReturn(DEPRECATED + " " + PARSING_ERROR);

            String color = converter.getForegroundColorCode(event);

            assertThat(color).isEqualTo(ANSIConstants.YELLOW_FG);
        }
    }

    // =========================================================================
    // transform()
    // =========================================================================

    @Nested
    class Transform {

        @Test
        void shouldNotPrependEmoji_whenMessageIsNull() {
            when(event.getMessage()).thenReturn(null);

            String result = converter.transform(event, "some input");

            assertThat(result).isEqualTo("\u001B[39msome input\u001B[0;39m");
        }

        @Test
        void shouldNotPrependEmoji_whenMessageMatchesNoKey() {
            when(event.getMessage()).thenReturn("unknown message");

            String result = converter.transform(event, "unknown message");

            assertThat(result).isEqualTo("\u001B[39munknown message\u001B[0;39m");
        }

        @ParameterizedTest
        @ValueSource(strings = {
                LOADING_RESOURCE,
                DOWNLOADING_VERSION,
                LOADING_LOCAL_DEFAULT_RESOURCE,
                CUSTOM_DATA_TYPES_CONFIGURED,
                JAVA_TYPES_USED,
                PARSING_SCHEMA_DEFINITIONS,
                PARSING_COMPLETED,
                PARSING_ERROR,
                NO_SCHEMA_MODEL_FOUND,
                MODEL_DIRECTORY_NOT_CREATED,
                MODEL_IMPL_DIRECTORY_NOT_CREATED,
                DATA_TYPE_DIRECTORY_NOT_CREATED,
                COULD_NOT_WRITE_PROPERTIES_FILE,
                COULD_NOT_WRITE_OUTPUT_FILE,
                COPYING_COMMON_MODELS,
                COPY_ERROR,
                GENERATING_MODELS,
                FINISHED,
                COMPLETED,
                DEPRECATED,
                ARCHIVED
        })
        void shouldPrependEmoji_forEachMappedKeyword(String keyword) {
            String message = "Context: " + keyword;
            when(event.getMessage()).thenReturn(message);

            String result = converter.transform(event, message);

            // Result must start with an emoji followed by a space
            assertThat(result)
                    .as("transform() for keyword '%s' should prepend an emoji and append original input", keyword)
                    .matches("^.+ .+")
                    .endsWith(message + RESET_DEFAULT_COLOR);
        }

        @Test
        void shouldPrependFolderEmoji_whenMessageContainsLoadingResource() {
            String message = LOADING_RESOURCE + ": schema.jsonld";
            when(event.getMessage()).thenReturn(message);

            String result = converter.transform(event, message);

            assertThat(result).startsWith("📂 ");
        }

        @Test
        void shouldPrependDownloadEmoji_whenMessageContainsDownloadingVersion() {
            String message = DOWNLOADING_VERSION + " 14.0";
            when(event.getMessage()).thenReturn(message);

            String result = converter.transform(event, message);

            assertThat(result).startsWith("📥 ");
        }

        @Test
        void shouldPrependCheckEmoji_whenMessageContainsCompleted() {
            String message = COMPLETED;
            when(event.getMessage()).thenReturn(message);

            String result = converter.transform(event, message);

            assertThat(result).startsWith("✅ ");
        }

        @Test
        void shouldPrependTimerEmoji_whenMessageContainsFinished() {
            String message = FINISHED + " in 500ms";
            when(event.getMessage()).thenReturn(message);

            String result = converter.transform(event, message);

            assertThat(result).startsWith("⏱️ ");
        }

        @Test
        void shouldPrependWarningEmoji_whenMessageContainsParsingError() {
            String message = PARSING_ERROR + " at line 12";
            when(event.getMessage()).thenReturn(message);

            String result = converter.transform(event, message);

            assertThat(result).startsWith("⚠️ ");
        }

        @Test
        void shouldPrependCrossEmoji_whenMessageContainsModelDirectoryNotCreated() {
            String message = MODEL_DIRECTORY_NOT_CREATED;
            when(event.getMessage()).thenReturn(message);

            String result = converter.transform(event, message);

            assertThat(result).startsWith("❌ ");
        }

        @Test
        void transform_onlyFirstMatchingKeywordEmojiIsPrepended() {
            // findFirst() on the entrySet stream means only one emoji is prepended.
            String message = LOADING_RESOURCE + " " + DOWNLOADING_VERSION;
            when(event.getMessage()).thenReturn(message);

            String result = converter.transform(event, message);

            // Only one emoji should be prepended — the result must start with a single emoji codepoint then a space
            int firstCodePoint = result.codePointAt(0);
            int emojiCharCount = Character.charCount(firstCodePoint);
            int secondCodePoint = result.codePointAt(emojiCharCount);
            assertThat(Character.getType(firstCodePoint))
                    .as("First character should be an emoji/symbol")
                    .isIn(
                            (int) Character.OTHER_SYMBOL,
                            (int) Character.OTHER_LETTER,
                            (int) Character.MODIFIER_SYMBOL
                    );
            assertThat(secondCodePoint)
                    .as("Second character after the emoji should be a space")
                    .isEqualTo(' ');

            String afterEmoji = result.substring(emojiCharCount + 1); // skip emoji + space
            assertThat(afterEmoji)
                    .as("The rest of the result after the emoji prefix should match the original message")
                    .contains(message);
        }
    }
}