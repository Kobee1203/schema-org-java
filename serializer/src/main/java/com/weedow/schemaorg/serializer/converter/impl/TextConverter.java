package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.lang.reflect.Type;

public class TextConverter extends AbstractConverter {

    public static final TextConverter INSTANCE = new TextConverter();

    private TextConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<?> sourceType, Class<? extends JsonLdDataType<?>> jsonLdDataType, Type javaType) {
        return jsonLdDataType.getSimpleName().equals("Text");
    }

    @Override
    public String getValue(Object source) {
        return source.toString();
    }
}
