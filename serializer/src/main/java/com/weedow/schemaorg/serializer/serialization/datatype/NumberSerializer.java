package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

public class NumberSerializer extends BaseDataTypeSerializer<Number> {

    public NumberSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
