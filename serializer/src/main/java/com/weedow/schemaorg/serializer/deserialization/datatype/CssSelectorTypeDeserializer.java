package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Deserializer for Schema.org CssSelectorType data type from JSON-LD.
 */
@SuppressWarnings("java:S110")
public class CssSelectorTypeDeserializer extends TextDeserializer {

    /**
     * Constructs a CssSelectorTypeDeserializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to deserialize
     */
    public CssSelectorTypeDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
