package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.time.LocalTime;

public class TimeSerializer extends BaseDataTypeSerializer<LocalTime> {

    public TimeSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
