package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import java.io.IOException;

@SuppressWarnings("java:S110")
public class BooleanDeserializer extends AbstractDataTypeDeserializer {

    public BooleanDeserializer(Class<?> clazz) {
        super(clazz);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return p.getValueAsString();
    }
}
