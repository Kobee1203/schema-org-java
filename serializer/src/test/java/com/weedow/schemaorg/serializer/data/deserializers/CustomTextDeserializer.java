package com.weedow.schemaorg.serializer.data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDataTypeDeserializer;
import com.weedow.schemaorg.serializer.deserialization.datatype.AbstractDataTypeDeserializer;
import org.schema.model.datatype.Text;

import java.io.IOException;

@JsonLdDataTypeDeserializer
public class CustomTextDeserializer extends AbstractDataTypeDeserializer<Text> {

    public CustomTextDeserializer() {
        super(Text.class);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return p.getValueAsString().replace("[", "").replace("]", "");
    }
}