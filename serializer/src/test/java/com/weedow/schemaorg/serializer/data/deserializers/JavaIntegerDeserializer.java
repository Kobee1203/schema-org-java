package com.weedow.schemaorg.serializer.data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.serializer.deserialization.datatype.AbstractTypeDeserializer;

import java.io.IOException;

public class JavaIntegerDeserializer extends AbstractTypeDeserializer<Integer> {

    public JavaIntegerDeserializer() {
        super(Integer.class);
    }

    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return p.getIntValue();
    }
}