package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import java.io.IOException;

@SuppressWarnings("java:S110")
public class XPathTypeDeserializer extends AbstractDataTypeDeserializer {

    public XPathTypeDeserializer(Class<?> clazz) {
        super(clazz);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return p.getValueAsString();
    }
}
