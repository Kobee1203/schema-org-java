package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.time.LocalDateTime;

/**
 * Serializer for Schema.org DateTime data type to JSON-LD ISO 8601 DateTime String.
 */
public class DateTimeSerializer extends BaseDataTypeSerializer<LocalDateTime> {

    /**
     * Constructs a DateTimeSerializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public DateTimeSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
