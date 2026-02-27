package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Serializer for Schema.org Number data type to JSON-LD Number.
 */
public class NumberSerializer extends BaseDataTypeSerializer<Number> {

    /**
     * Constructs a NumberSerializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public NumberSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
