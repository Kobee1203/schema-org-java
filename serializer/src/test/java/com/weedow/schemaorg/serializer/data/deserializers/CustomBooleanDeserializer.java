package com.weedow.schemaorg.serializer.data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDataTypeDeserializer;
import com.weedow.schemaorg.serializer.deserialization.datatype.AbstractDataTypeDeserializer;
import org.schema.model.datatype.Boolean;

import java.io.IOException;

@JsonLdDataTypeDeserializer
public class CustomBooleanDeserializer extends AbstractDataTypeDeserializer<Boolean> {

    public CustomBooleanDeserializer() {
        super(Boolean.class);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return "1".equals(p.getValueAsString());
    }
}