package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter extends AbstractConverter {

    public static final DateConverter INSTANCE = new DateConverter();

    private DateConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<?> sourceType, Class<? extends JsonLdDataType<?>> jsonLdDataType, Type javaType) {
        return (LocalDate.class.isAssignableFrom(sourceType) || String.class.isAssignableFrom(sourceType)) && jsonLdDataType.getSimpleName().equals("Date");
    }

    @Override
    public LocalDate getValue(Object source) {
        return source instanceof LocalDate ? (LocalDate) source : LocalDate.parse(source.toString(), DateTimeFormatter.ISO_DATE);
    }
}
