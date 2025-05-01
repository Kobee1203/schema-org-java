package com.weedow.schemaorg.serializer.data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.serializer.deserialization.datatype.AbstractTypeDeserializer;

import java.io.IOException;

public class JavaFloatDeserializer extends AbstractTypeDeserializer<Float> {

    public JavaFloatDeserializer() {
        super(Float.class);
    }

    @Override
    public Float deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return java.lang.Float.valueOf(p.getValueAsString());
    }
}