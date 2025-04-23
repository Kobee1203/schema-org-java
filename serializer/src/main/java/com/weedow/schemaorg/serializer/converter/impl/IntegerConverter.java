package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

@SuppressWarnings("java:S6548")
public class IntegerConverter extends AbstractConverter {

    public static final IntegerConverter INSTANCE = new IntegerConverter();

    private IntegerConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<? extends JsonLdDataType<?>> jsonLdDataType, Class<?> targetType) {
        return (Number.class.isAssignableFrom(targetType) || String.class.isAssignableFrom(targetType)) && jsonLdDataType.getSimpleName().equals("Integer");
    }

    @Override
    public Integer getValue(Object source) {
        return source instanceof Integer value ? value : Integer.valueOf(source.toString());
    }
}
