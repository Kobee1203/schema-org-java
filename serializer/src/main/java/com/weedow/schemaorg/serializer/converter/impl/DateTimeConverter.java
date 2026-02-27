package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Converter for Schema.org DateTime data type to LocalDateTime.
 */
@SuppressWarnings("java:S6548")
public class DateTimeConverter extends AbstractConverter {

    /** Singleton instance. */
    public static final DateTimeConverter INSTANCE = new DateTimeConverter();

    private DateTimeConverter() {
    }

    @Override
    @SuppressWarnings("java:S1872")
    public boolean supports(Class<? extends JsonLdDataType<?>> jsonLdDataType, Class<?> targetType) {
        return (LocalDateTime.class.isAssignableFrom(targetType) || String.class.isAssignableFrom(targetType)) && jsonLdDataType.getSimpleName().equals("DateTime");
    }

    @Override
    public LocalDateTime getValue(Object source) {
        return source instanceof LocalDateTime localDateTime ? localDateTime : LocalDateTime.parse(source.toString(), DateTimeFormatter.ISO_DATE_TIME);
    }
}
