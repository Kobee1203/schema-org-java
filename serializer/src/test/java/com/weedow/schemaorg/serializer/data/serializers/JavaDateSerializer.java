package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.serializer.serialization.datatype.AbstractTypeSerializer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JavaDateSerializer extends AbstractTypeSerializer<LocalDate> {

    public JavaDateSerializer() {
        super(LocalDate.class);
    }

    @Override
    protected Object getValue(LocalDate value) {
        return value.format(DateTimeFormatter.BASIC_ISO_DATE);
    }
}