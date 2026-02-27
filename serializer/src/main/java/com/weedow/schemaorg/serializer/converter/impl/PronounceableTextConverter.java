package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Converter for Schema.org PronounceableText data type to String.
 */
@SuppressWarnings("java:S6548")
public class PronounceableTextConverter extends AbstractConverter {

    /** Singleton instance. */
    public static final PronounceableTextConverter INSTANCE = new PronounceableTextConverter();

    private PronounceableTextConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<? extends JsonLdDataType<?>> jsonLdDataType, Class<?> targetType) {
        return String.class.isAssignableFrom(targetType) && jsonLdDataType.getSimpleName().equals("PronounceableText");
    }

    @Override
    public String getValue(Object source) {
        return source.toString();
    }
}
