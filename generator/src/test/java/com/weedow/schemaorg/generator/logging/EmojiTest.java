package com.weedow.schemaorg.generator.logging;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmojiTest {

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(Emoji.class).verify();
    }

    @Test
    void toStringContract() {
        ToStringVerifier.forClass(Emoji.class).verify();
    }

    @Test
    void of_creates_emoji_with_raw_value() {
        String rawEmoji = "🎉";
        Emoji emoji = Emoji.of(rawEmoji);

        assertThat(emoji.rawValue()).isEqualTo(rawEmoji);
    }

    @Test
    void value_returns_emoji_with_variant_selector() {
        String rawEmoji = "🎉";
        Emoji emoji = Emoji.of(rawEmoji);

        assertThat(emoji.value()).isEqualTo(rawEmoji + Emoji.VS);
    }

    @Test
    void value_appends_variant_selector_to_any_emoji() {
        String[] emojis = {"✅", "❌", "🔍", "📦", "⚙️"};

        for (String rawEmoji : emojis) {
            Emoji emoji = Emoji.of(rawEmoji);
            assertThat(emoji.value())
                    .startsWith(rawEmoji)
                    .endsWith(Emoji.VS);
        }
    }

    @Test
    void of_with_null_throws_exception() {
        // noinspection DataFlowIssue
        assertThatThrownBy(() -> Emoji.of(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("rawValue");
    }

    @Test
    void different_emojis_are_not_equal() {
        Emoji emoji1 = Emoji.of("🎉");
        Emoji emoji2 = Emoji.of("✅");

        assertThat(emoji1).isNotEqualTo(emoji2);
    }

    @Test
    void same_emoji_instances_are_equal() {
        Emoji emoji1 = Emoji.of("🎉");
        Emoji emoji2 = Emoji.of("🎉");

        assertThat(emoji1)
                .isEqualTo(emoji2)
                .hasSameHashCodeAs(emoji2);
    }

    @Test
    void value_is_different_from_raw_value() {
        String rawEmoji = "🎉";
        Emoji emoji = Emoji.of(rawEmoji);

        assertThat(emoji.value()).isNotEqualTo(emoji.rawValue());
        assertThat(emoji.value()).hasSize(emoji.rawValue().length() + 1);
    }
}
