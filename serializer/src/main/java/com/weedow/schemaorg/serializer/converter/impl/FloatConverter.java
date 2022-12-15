package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.lang.reflect.Type;

public class FloatConverter extends AbstractConverter {

    public static final FloatConverter INSTANCE = new FloatConverter();

    private FloatConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<?> sourceType, Class<? extends JsonLdDataType<?>> jsonLdDataType, Type javaType) {
        return (Number.class.isAssignableFrom(sourceType) || String.class.isAssignableFrom(sourceType)) && jsonLdDataType.getSimpleName().equals("Float");
    }

    @Override
    public Float getValue(Object source) {
        return source instanceof Float ? (Float) source : Float.valueOf(source.toString());
    }
}
