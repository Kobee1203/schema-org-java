package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.lang.reflect.Type;

public class BooleanConverter extends AbstractConverter {

    public static final BooleanConverter INSTANCE = new BooleanConverter();

    private BooleanConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<?> sourceType, Class<? extends JsonLdDataType<?>> jsonLdDataType, Type javaType) {
        return (Boolean.class.isAssignableFrom(sourceType) || String.class.isAssignableFrom(sourceType)) && jsonLdDataType.getSimpleName().equals("Boolean");
    }

    @Override
    public Boolean getValue(Object source) {
        return source instanceof Boolean ? (Boolean) source : Boolean.valueOf(source.toString());
    }
}
