package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Converter for Schema.org Text data type to Java String.
 */
@SuppressWarnings("java:S6548")
public class TextConverter extends AbstractConverter {

    /** Singleton instance. */
    public static final TextConverter INSTANCE = new TextConverter();

    private TextConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<? extends JsonLdDataType<?>> jsonLdDataType, Class<?> targetType) {
        return jsonLdDataType.getSimpleName().equals("Text");
    }

    @Override
    public String getValue(Object source) {
        return source.toString();
    }
}
