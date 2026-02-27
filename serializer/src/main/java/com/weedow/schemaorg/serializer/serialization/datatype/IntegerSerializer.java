package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Serializer for Schema.org Integer data type to JSON-LD Number.
 */
public class IntegerSerializer extends BaseDataTypeSerializer<Number> {

    /**
     * Constructs an IntegerSerializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public IntegerSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
