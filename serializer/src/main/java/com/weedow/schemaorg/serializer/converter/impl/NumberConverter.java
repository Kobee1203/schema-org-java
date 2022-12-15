package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.lang.reflect.Type;

public class NumberConverter extends AbstractConverter {

    public static final NumberConverter INSTANCE = new NumberConverter();

    private NumberConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<?> sourceType, Class<? extends JsonLdDataType<?>> jsonLdDataType, Type javaType) {
        return (Number.class.isAssignableFrom(sourceType) || String.class.isAssignableFrom(sourceType)) && jsonLdDataType.getSimpleName().equals("Number");
    }

    @Override
    public Number getValue(Object source) {
        return source instanceof Number ? (Number) source : Double.valueOf(source.toString());
    }
}
