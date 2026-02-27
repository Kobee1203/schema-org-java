package com.weedow.schemaorg.serializer;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDataTypeDeserializerModifier;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializerModifier;

import java.io.Serial;

/**
 * Jackson module that registers custom serializers and deserializers for Schema.org data types.
 */
public class JsonLdDataTypeModule extends SimpleModule {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs the JsonLdDataType module and registers serializer/deserializer modifiers.
     */
    public JsonLdDataTypeModule() {
        super("JsonLdDataType Module");
        setDeserializerModifier(new JsonLdDataTypeDeserializerModifier());
        setSerializerModifier(new JsonLdDataTypeSerializerModifier());
    }
}