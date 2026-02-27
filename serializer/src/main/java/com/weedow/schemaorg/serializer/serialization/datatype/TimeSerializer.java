package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.time.LocalTime;

/**
 * Serializer for Schema.org Time data type to JSON-LD ISO 8601 Time String.
 */
public class TimeSerializer extends BaseDataTypeSerializer<LocalTime> {

    /**
     * Constructs a TimeSerializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public TimeSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
