package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Serializer for Schema.org CssSelectorType data type to JSON-LD String.
 */
@SuppressWarnings("java:S110")
public class CssSelectorTypeSerializer extends TextSerializer {

    /**
     * Constructs a CssSelectorTypeSerializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public CssSelectorTypeSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
