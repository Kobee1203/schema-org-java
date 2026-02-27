package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;

/**
 * Abstract base deserializer for Schema.org data types from JSON-LD.
 * Provides common deserialization infrastructure for type-specific deserializers.
 *
 * @param <T> the type to deserialize
 */
public abstract class AbstractTypeDeserializer<T> extends StdDeserializer<T> {

    /**
     * Constructs a deserializer for the specified class.
     *
     * @param clazz the class to deserialize
     */
    protected AbstractTypeDeserializer(Class<?> clazz) {
        super(clazz);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends T> handledType() {
        return (Class<? extends T>) super.handledType();
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, ctxt);
    }
}
