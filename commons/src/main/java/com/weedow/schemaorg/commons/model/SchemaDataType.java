package com.weedow.schemaorg.commons.model;

import lombok.Getter;

@Getter
public enum SchemaDataType {
    BOOLEAN("Boolean"),
    INTEGER("Integer"),
    FLOAT("Float"),
    NUMBER("Number"),
    TIME("Time"),
    DATE("Date"),
    DATE_TIME("DateTime"),
    URL("URL"),
    CSS_SELECTOR_TYPE("CssSelectorType"),
    XPATH_TYPE("XPathType"),
    PRONOUNCEABLE_TEXT("PronounceableText"),
    TEXT("Text");

    private final String name;

    SchemaDataType(String name) {
        this.name = name;
    }
}
