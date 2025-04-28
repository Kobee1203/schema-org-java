package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;

public abstract class AbstractTypeDeserializer<T> extends StdDeserializer<T> {

    protected AbstractTypeDeserializer(Class<?> clazz) {
        super(clazz);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<T> handledType() {
        return (Class<T>) super.handledType();
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, ctxt);
    }
}
