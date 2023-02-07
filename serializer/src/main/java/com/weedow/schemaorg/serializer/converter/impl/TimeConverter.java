package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeConverter extends AbstractConverter {

    public static final TimeConverter INSTANCE = new TimeConverter();

    private TimeConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<? extends JsonLdDataType<?>> jsonLdDataType, Class<?> targetType) {
        return (LocalTime.class.isAssignableFrom(targetType) || String.class.isAssignableFrom(targetType)) && jsonLdDataType.getSimpleName().equals("Time");
    }

    @Override
    public LocalTime getValue(Object source) {
        return source instanceof LocalTime ? (LocalTime) source : LocalTime.parse(source.toString(), DateTimeFormatter.ISO_TIME);
    }
}
