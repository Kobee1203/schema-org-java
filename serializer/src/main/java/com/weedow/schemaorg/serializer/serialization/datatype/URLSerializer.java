package com.weedow.schemaorg.serializer.serialization.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.net.URL;

public class URLSerializer extends BaseDataTypeSerializer<URL> {

    public URLSerializer(Class<? extends JsonLdDataType<?>> clazz) {
        super(clazz);
    }
}
