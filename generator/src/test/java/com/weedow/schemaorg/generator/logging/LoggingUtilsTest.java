package com.weedow.schemaorg.generator.logging;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

class LoggingUtilsTest {

    @Test
    void constructor_is_private() throws NoSuchMethodException {
        Constructor<LoggingUtils> constructor = LoggingUtils.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Test message", "Line 1\nLine 2\nLine 3"})
    void msg_formats_message_with_emoji(String message) {
        Emoji emoji = Emoji.of("🎉");

        String result = LoggingUtils.msg(emoji, message);

        assertThat(result).isEqualTo("🎉" + Emoji.VS + " " + message);
    }

    @Test
    void msg_works_with_different_emojis() {
        String[] emojis = {"🎉", "✅", "❌", "🔍", "📦"};
        String message = "Test";

        for (String emojiStr : emojis) {
            Emoji emoji = Emoji.of(emojiStr);
            String result = LoggingUtils.msg(emoji, message);
            assertThat(result).isEqualTo(emojiStr + Emoji.VS + " " + message);
        }
    }
}
