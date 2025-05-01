package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.serializer.serialization.datatype.AbstractTypeSerializer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class JavaDateTimeSerializer extends AbstractTypeSerializer<LocalDateTime> {

    public JavaDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    protected Object getValue(LocalDateTime value) {
        return value
                .atZone(ZoneId.of("UTC"))
                .format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}