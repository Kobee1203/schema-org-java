package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

@SuppressWarnings("java:S110")
public class CssSelectorTypeSerializer extends TextSerializer {

    public CssSelectorTypeSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
