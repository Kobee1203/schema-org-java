package com.weedow.schemaorg.generator.logging;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Represents an emoji with automatic Variant Selector (U+FE0F) management.
 *
 * <p>The variant selector is appended to ensure consistent emoji rendering
 * across different terminals and prevent spacing issues (especially with Maven output).
 *
 * <p>Use the factory method {@code #of(String)} to create instances.
 */
@Accessors(fluent = true)
@Getter(AccessLevel.PACKAGE)
@Value(staticConstructor = "of")
public class Emoji {

    /**
     * U+FE0F (Variant Selector) appended to ensure consistent emoji rendering
     * across different terminals and prevent spacing issues.
     */
    @SuppressWarnings("UnnecessaryUnicodeEscape")
    public static final String VS = "\uFE0F";

    /** The raw emoji value (without variant selector). */
    @NonNull
    String rawValue;

    /**
     * Returns the emoji value with variant selector appended.
     *
     * @return the emoji with U+FE0F
     */
    public String value() {
        return rawValue + VS;
    }
}
