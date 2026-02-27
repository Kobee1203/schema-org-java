package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.time.LocalDate;

/**
 * Serializer for Schema.org Date data type to JSON-LD ISO 8601 Date String.
 */
public class DateSerializer extends BaseDataTypeSerializer<LocalDate> {

    /**
     * Constructs a DateSerializer for the specified class.
     *
     * @param clazz the JsonLdDataType class to serialize
     */
    public DateSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
