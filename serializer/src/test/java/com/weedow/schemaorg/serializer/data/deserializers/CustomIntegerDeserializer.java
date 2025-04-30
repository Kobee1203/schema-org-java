package com.weedow.schemaorg.serializer.data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDataTypeDeserializer;
import com.weedow.schemaorg.serializer.deserialization.datatype.AbstractDataTypeDeserializer;
import org.schema.model.datatype.Integer;

import java.io.IOException;

@JsonLdDataTypeDeserializer
public class CustomIntegerDeserializer extends AbstractDataTypeDeserializer<Integer> {

    public CustomIntegerDeserializer() {
        super(Integer.class);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return p.getIntValue();
    }
}