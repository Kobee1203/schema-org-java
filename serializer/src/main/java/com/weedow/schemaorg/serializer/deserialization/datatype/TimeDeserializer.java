package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("java:S110")
public class TimeDeserializer extends AbstractDataTypeDeserializer<JsonLdDataType<LocalTime>> {

    public TimeDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalTime.parse(p.getValueAsString(), DateTimeFormatter.ISO_TIME);
    }
}
