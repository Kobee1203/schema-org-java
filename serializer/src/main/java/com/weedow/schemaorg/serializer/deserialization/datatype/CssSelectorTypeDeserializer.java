package com.weedow.schemaorg.serializer.deserialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

@SuppressWarnings("java:S110")
public class CssSelectorTypeDeserializer extends TextDeserializer {

    public CssSelectorTypeDeserializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
