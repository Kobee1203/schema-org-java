package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

@SuppressWarnings("java:S6548")
public class TextConverter extends AbstractConverter {

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
