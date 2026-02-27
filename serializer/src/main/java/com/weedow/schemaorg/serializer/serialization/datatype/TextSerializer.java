package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Serializer for Schema.org Text data type to JSON-LD String.
 */
public class TextSerializer extends BaseDataTypeSerializer<String> {

    /**
     * Constructs a TextSerializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public TextSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
