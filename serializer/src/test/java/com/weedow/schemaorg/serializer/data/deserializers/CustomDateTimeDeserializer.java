package com.weedow.schemaorg.serializer.data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDataTypeDeserializer;
import com.weedow.schemaorg.serializer.deserialization.datatype.AbstractDataTypeDeserializer;
import org.schema.model.datatype.DateTime;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonLdDataTypeDeserializer
public class CustomDateTimeDeserializer extends AbstractDataTypeDeserializer<DateTime> {

    public CustomDateTimeDeserializer() {
        super(DateTime.class);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalDateTime.parse(p.getValueAsString(), DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}