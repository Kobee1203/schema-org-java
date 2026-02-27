package com.weedow.schemaorg.generator.logging;

/**
 * Predefined emoji constants for consistent logging output.
 */
public final class Emojis {

    private Emojis() {
    }

    /** Emoji for archived items. */
    public static final Emoji ARCHIVED = Emoji.of("📜");
    /** Emoji for warning messages. */
    public static final Emoji WARNING = Emoji.of("⚠");
    /** Emoji for error messages. */
    public static final Emoji ERROR = Emoji.of("❌");
    /** Emoji for resource loading operations. */
    public static final Emoji RESOURCE = Emoji.of("📂");
    /** Emoji for download operations. */
    public static final Emoji DOWNLOAD = Emoji.of("📥");
    /** Emoji for local default resource loading operations. */
    public static final Emoji PACKAGE = Emoji.of("📦");
    /** Emoji for search operations. */
    public static final Emoji SEARCH = Emoji.of("🔍");
    /** Emoji for completion/flag operations. */
    public static final Emoji FLAG = Emoji.of("🏁");
    /** Emoji for copy operations. */
    public static final Emoji COPY = Emoji.of("📑");
    /** Emoji for custom datatypes-related message. */
    public static final Emoji LABEL = Emoji.of("🏷");
    /** Emoji for Java-related operations. */
    public static final Emoji JAVA = Emoji.of("☕");
    /** Emoji for in progress generation operations. */
    public static final Emoji GEAR = Emoji.of("⚙");
    /** Emoji for completed generation operations. */
    public static final Emoji CHECK = Emoji.of("✅");
    /** Emoji for elapsed runtime message. */
    public static final Emoji TIMER = Emoji.of("⏱");
    /** Emoji for not found schema model message. */
    public static final Emoji GHOST = Emoji.of("👻");
}