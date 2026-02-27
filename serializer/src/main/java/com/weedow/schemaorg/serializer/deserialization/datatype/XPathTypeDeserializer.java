package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Deserializer for Schema.org XPathType data type from JSON-LD.
 */
@SuppressWarnings("java:S110")
public class XPathTypeDeserializer extends TextDeserializer {

    /**
     * Constructs an XPathTypeDeserializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to deserialize
     */
    public XPathTypeDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
