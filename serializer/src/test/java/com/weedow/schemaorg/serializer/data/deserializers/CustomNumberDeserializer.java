package com.weedow.schemaorg.serializer.data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDataTypeDeserializer;
import com.weedow.schemaorg.serializer.deserialization.datatype.AbstractDataTypeDeserializer;
import org.schema.model.datatype.Number;

import java.io.IOException;

@JsonLdDataTypeDeserializer
public class CustomNumberDeserializer extends AbstractDataTypeDeserializer<Number> {

    public CustomNumberDeserializer() {
        super(Number.class);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return p.getNumberValue().doubleValue();
    }
}