package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Serializer for Schema.org XPathType data type to JSON-LD String.
 */
@SuppressWarnings("java:S110")
public class XPathTypeSerializer extends TextSerializer {

    /**
     * Constructs an XPathTypeSerializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public XPathTypeSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
