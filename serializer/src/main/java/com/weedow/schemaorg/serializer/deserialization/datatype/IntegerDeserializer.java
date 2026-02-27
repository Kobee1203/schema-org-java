package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.io.IOException;

/**
 * Deserializer for Schema.org Integer data type from JSON-LD.
 */
@SuppressWarnings("java:S110")
public class IntegerDeserializer extends AbstractDataTypeDeserializer<JsonLdDataType<Integer>> {

    /**
     * Constructs an IntegerDeserializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to deserialize
     */
    public IntegerDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return p.getIntValue();
    }
}
