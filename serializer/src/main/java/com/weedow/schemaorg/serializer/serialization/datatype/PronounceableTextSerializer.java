package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

@SuppressWarnings("java:S110")
public class PronounceableTextSerializer extends TextSerializer {

    public PronounceableTextSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
