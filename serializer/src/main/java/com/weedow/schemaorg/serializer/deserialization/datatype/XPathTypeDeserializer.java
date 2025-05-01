package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

@SuppressWarnings("java:S110")
public class XPathTypeDeserializer extends TextDeserializer {

    public XPathTypeDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
