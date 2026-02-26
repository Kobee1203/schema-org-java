package com.weedow.schemaorg.generator.logging;

public final class LoggingUtils {

    private LoggingUtils() {
    }

    /**
     * Formats a message with an emoji prefix, ensuring consistent emoji rendering across different terminals.
     *
     * @param emoji  the emoji character to prefix the message with
     * @param format the message text
     * @return the formatted message with emoji prefix and variant selector
     */
    public static String msg(Emoji emoji, String format) {
        return emoji.value() + " " + format;
    }
}
