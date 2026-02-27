package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Serializer for Schema.org Float data type to JSON-LD Number.
 */
public class FloatSerializer extends BaseDataTypeSerializer<Number> {

    /**
     * Constructs a FloatSerializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public FloatSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
