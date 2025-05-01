package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

public class IntegerSerializer extends BaseDataTypeSerializer<Number> {

    public IntegerSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
