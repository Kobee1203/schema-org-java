package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.lang.reflect.Type;

public class PronounceableTextConverter extends AbstractConverter {

    public static final PronounceableTextConverter INSTANCE = new PronounceableTextConverter();

    private PronounceableTextConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<?> sourceType, Class<? extends JsonLdDataType<?>> jsonLdDataType, Type javaType) {
        return String.class.isAssignableFrom(sourceType) && jsonLdDataType.getSimpleName().equals("PronounceableText");
    }

    @Override
    public String getValue(Object source) {
        return source.toString();
    }
}
