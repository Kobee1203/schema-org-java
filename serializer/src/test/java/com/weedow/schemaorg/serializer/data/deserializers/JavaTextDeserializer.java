package com.weedow.schemaorg.serializer.data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.serializer.deserialization.datatype.AbstractTypeDeserializer;

import java.io.IOException;

public class JavaTextDeserializer extends AbstractTypeDeserializer<String> {

    public JavaTextDeserializer() {
        super(String.class);
    }


    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return p.getValueAsString().replace("[", "").replace("]", "");
    }
}