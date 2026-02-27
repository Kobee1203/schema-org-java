package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * Base serializer for Schema.org data types that wrap a value.
 * Extracts and serializes the wrapped value from JsonLdDataType instances.
 *
 * @param <T> the type of the wrapped value
 */
public class BaseDataTypeSerializer<T> extends AbstractTypeSerializer<JsonLdDataType<T>> {

    /**
     * Constructs a serializer for the specified JsonLdDataType class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public BaseDataTypeSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }

    @Override
    protected Object getValue(JsonLdDataType<T> jsonLdDataType) {
        return jsonLdDataType.getValue();
    }
}
