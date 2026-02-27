package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.net.URL;

/**
 * Serializer for Schema.org URL data type to JSON-LD String.
 */
public class URLSerializer extends BaseDataTypeSerializer<URL> {

    /**
     * Constructs a URLSerializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public URLSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
