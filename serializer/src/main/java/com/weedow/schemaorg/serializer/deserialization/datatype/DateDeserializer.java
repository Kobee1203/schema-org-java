package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deserializer for Schema.org Date data type from JSON-LD ISO 8601 Date String.
 */
@SuppressWarnings("java:S110")
public class DateDeserializer extends AbstractDataTypeDeserializer<JsonLdDataType<LocalDate>> {

    /**
     * Constructs a DateDeserializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to deserialize
     */
    public DateDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalDate.parse(p.getValueAsString(), DateTimeFormatter.ISO_DATE);
    }
}
