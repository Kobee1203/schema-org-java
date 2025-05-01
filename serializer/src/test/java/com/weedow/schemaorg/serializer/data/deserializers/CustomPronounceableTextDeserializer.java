package com.weedow.schemaorg.serializer.data.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDataTypeDeserializer;
import com.weedow.schemaorg.serializer.deserialization.datatype.AbstractDataTypeDeserializer;
import org.schema.model.datatype.PronounceableText;

import java.io.IOException;

@JsonLdDataTypeDeserializer
public class CustomPronounceableTextDeserializer extends AbstractDataTypeDeserializer<PronounceableText> {

    public CustomPronounceableTextDeserializer() {
        super(PronounceableText.class);
    }

    @Override
    protected Object getValue(JsonParser p, DeserializationContext ctxt) throws IOException {
        return p.getValueAsString().replace("speak:", "");
    }
}