package com.weedow.schemaorg.serializer.data.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.weedow.schemaorg.serializer.serialization.datatype.AbstractTypeSerializer;

import java.io.IOException;

public class JavaTextSerializer extends AbstractTypeSerializer<String> {

    public JavaTextSerializer() {
        super(String.class);
    }

    @Override
    protected Object getValue(String value) {
        return "[" + value + "]";
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        super.serialize(value, gen, provider);
    }
}