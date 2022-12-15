package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter extends AbstractConverter {

    public static final DateTimeConverter INSTANCE = new DateTimeConverter();

    private DateTimeConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<?> sourceType, Class<? extends JsonLdDataType<?>> jsonLdDataType, Type javaType) {
        return (LocalDateTime.class.isAssignableFrom(sourceType) || String.class.isAssignableFrom(sourceType)) && jsonLdDataType.getSimpleName().equals("DateTime");
    }

    @Override
    public LocalDateTime getValue(Object source) {
        return source instanceof LocalDateTime ? (LocalDateTime) source : LocalDateTime.parse(source.toString(), DateTimeFormatter.ISO_DATE_TIME);
    }
}
