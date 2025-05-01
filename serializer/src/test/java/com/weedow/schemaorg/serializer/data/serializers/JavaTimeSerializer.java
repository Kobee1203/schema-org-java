package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.serializer.serialization.datatype.AbstractTypeSerializer;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import static java.time.temporal.ChronoField.*;

public class JavaTimeSerializer extends AbstractTypeSerializer<LocalTime> {

    public static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendValue(HOUR_OF_DAY, 2)
            .appendValue(MINUTE_OF_HOUR, 2)
            .appendValue(SECOND_OF_MINUTE, 2)
            .optionalStart()
            .appendFraction(NANO_OF_SECOND, 0, 9, true)
            .toFormatter(Locale.ENGLISH);

    public JavaTimeSerializer() {
        super(LocalTime.class);
    }

    @Override
    protected Object getValue(LocalTime value) {
        return value.format(FORMATTER);
    }
}