package com.weedow.schemaorg.commons.model;

import lombok.Getter;

/**
 * Enumeration of Schema.org data types.
 *
 * @see <a href="https://schema.org/DataType">https://schema.org/DataType</a>
 */
@Getter
public enum SchemaDataType {
    /** Boolean data type. */
    BOOLEAN("Boolean"),

    /** Integer data type. */
    INTEGER("Integer"),

    /** Float data type. */
    FLOAT("Float"),

    /** Number data type. */
    NUMBER("Number"),

    /** Time data type. */
    TIME("Time"),

    /** Date data type. */
    DATE("Date"),

    /** DateTime data type. */
    DATE_TIME("DateTime"),

    /** URL data type. */
    URL("URL"),

    /** CssSelectorType data type. */
    CSS_SELECTOR_TYPE("CssSelectorType"),

    /** XPathType data type. */
    XPATH_TYPE("XPathType"),

    /** PronounceableText data type. */
    PRONOUNCEABLE_TEXT("PronounceableText"),

    /** Text data type. */
    TEXT("Text");

    /** Data type name */
    private final String name;

    SchemaDataType(String name) {
        this.name = name;
    }
}
