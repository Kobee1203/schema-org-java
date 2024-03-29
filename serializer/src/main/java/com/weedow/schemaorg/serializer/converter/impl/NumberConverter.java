package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

public class NumberConverter extends AbstractConverter {

    public static final NumberConverter INSTANCE = new NumberConverter();

    private NumberConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<? extends JsonLdDataType<?>> jsonLdDataType, Class<?> targetType) {
        return (Number.class.isAssignableFrom(targetType) || String.class.isAssignableFrom(targetType)) && jsonLdDataType.getSimpleName().equals("Number");
    }

    @Override
    public Number getValue(Object source) {
        return source instanceof Number ? (Number) source : Double.valueOf(source.toString());
    }
}
