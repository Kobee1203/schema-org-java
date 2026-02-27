package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Serializer for Schema.org PronounceableText data type to JSON-LD String.
 */
@SuppressWarnings("java:S110")
public class PronounceableTextSerializer extends TextSerializer {

    /**
     * Constructs a PronounceableTextSerializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public PronounceableTextSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
