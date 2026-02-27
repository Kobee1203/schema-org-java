package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deserializer for Schema.org Time data type from JSON-LD ISO 8601 Time String.
 */
@SuppressWarnings("java:S110")
public class TimeDeserializer extends AbstractDataTypeDeserializer<JsonLdDataType<LocalTime>> {

    /**
     * Constructs a TimeDeserializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to deserialize
     */
    public TimeDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalTime.parse(p.getValueAsString(), DateTimeFormatter.ISO_TIME);
    }
}
