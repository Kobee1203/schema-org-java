package com.weedow.schemaorg.serializer.data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.serializer.deserialization.datatype.AbstractTypeDeserializer;

import java.io.IOException;

public class JavaNumberDeserializer extends AbstractTypeDeserializer<Number> {

    public JavaNumberDeserializer() {
        super(Number.class);
    }

    @Override
    public Number deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return p.getNumberValue().doubleValue();
    }
}