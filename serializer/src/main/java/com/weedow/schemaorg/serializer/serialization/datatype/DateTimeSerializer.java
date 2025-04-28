package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.time.LocalDateTime;

public class DateTimeSerializer extends BaseDataTypeSerializer<LocalDateTime> {

    public DateTimeSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
