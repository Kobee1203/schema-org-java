package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.io.IOException;

/**
 * Deserializer for Schema.org Float data type from JSON-LD.
 */
@SuppressWarnings("java:S110")
public class FloatDeserializer extends AbstractDataTypeDeserializer<JsonLdDataType<Float>> {

    /**
     * Constructs a FloatDeserializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to deserialize
     */
    public FloatDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return p.getFloatValue();
    }
}
