package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

@SuppressWarnings("java:S110")
public class XPathTypeSerializer extends TextSerializer {

    public XPathTypeSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
