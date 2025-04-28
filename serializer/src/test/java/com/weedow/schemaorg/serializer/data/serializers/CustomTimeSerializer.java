package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializer;
import com.weedow.schemaorg.serializer.serialization.datatype.TimeSerializer;
import org.schema.model.datatype.Time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import static java.time.temporal.ChronoField.*;

@JsonLdDataTypeSerializer
public class CustomTimeSerializer extends TimeSerializer {

    public static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendValue(HOUR_OF_DAY, 2)
            .appendValue(MINUTE_OF_HOUR, 2)
            .appendValue(SECOND_OF_MINUTE, 2)
            .optionalStart()
            .appendFraction(NANO_OF_SECOND, 0, 9, true)
            .toFormatter(Locale.ENGLISH);

    public CustomTimeSerializer() {
        super(Time.class);
    }

    @Override
    protected Object getValue(JsonLdDataType<LocalTime> jsonLdDataType) {
        return jsonLdDataType.getValue().format(FORMATTER);
    }
}