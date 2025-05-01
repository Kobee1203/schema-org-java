package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

public class BooleanSerializer extends BaseDataTypeSerializer<Boolean> {

    public BooleanSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
