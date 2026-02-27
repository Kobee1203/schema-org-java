package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Deserializer for Schema.org PronounceableText data type from JSON-LD.
 */
@SuppressWarnings("java:S110")
public class PronounceableTextDeserializer extends TextDeserializer {

    /**
     * Constructs a PronounceableTextDeserializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to deserialize
     */
    public PronounceableTextDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}