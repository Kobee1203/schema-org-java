package com.weedow.schemaorg.serializer.data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.serializer.deserialization.datatype.AbstractTypeDeserializer;

import java.io.IOException;

public class JavaBooleanDeserializer extends AbstractTypeDeserializer<Boolean> {

    public JavaBooleanDeserializer() {
        super(Boolean.class);
    }

    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return "1".equals(p.getValueAsString());
    }
}