package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.time.LocalDate;

public class DateSerializer extends BaseDataTypeSerializer<LocalDate> {

    public DateSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
