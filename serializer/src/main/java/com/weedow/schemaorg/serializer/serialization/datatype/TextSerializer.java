package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

public class TextSerializer extends BaseDataTypeSerializer<String> {

    public TextSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
