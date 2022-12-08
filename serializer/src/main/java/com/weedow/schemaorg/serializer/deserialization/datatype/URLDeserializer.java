package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.net.URL;

public class URLDeserializer extends AbstractDataTypeDeserializer {

    public URLDeserializer(JavaType delegateType, JsonDeserializer<?> defaultDeserializer) {
        super(delegateType, defaultDeserializer);
    }


    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return new URL(p.getValueAsString());
    }
}
