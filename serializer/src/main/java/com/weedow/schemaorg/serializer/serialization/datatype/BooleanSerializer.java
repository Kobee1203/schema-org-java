package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Serializer for Schema.org Boolean data type to JSON-LD boolean.
 */
public class BooleanSerializer extends BaseDataTypeSerializer<Boolean> {

    /**
     * Constructs a BooleanSerializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public BooleanSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
