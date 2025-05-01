package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

public class BaseDataTypeSerializer<T> extends AbstractTypeSerializer<JsonLdDataType<T>> {

    public BaseDataTypeSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }

    @Override
    protected Object getValue(JsonLdDataType<T> jsonLdDataType) {
        return jsonLdDataType.getValue();
    }
}
