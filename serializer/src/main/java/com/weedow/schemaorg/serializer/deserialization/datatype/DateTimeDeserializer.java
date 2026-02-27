package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deserializer for Schema.org DateTime data type from JSON-LD ISO 8601 DateTime String.
 */
@SuppressWarnings("java:S110")
public class DateTimeDeserializer extends AbstractDataTypeDeserializer<JsonLdDataType<LocalDateTime>> {

    /**
     * Constructs a DateTimeDeserializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to deserialize
     */
    public DateTimeDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalDateTime.parse(p.getValueAsString(), DateTimeFormatter.ISO_DATE_TIME);
    }
}
