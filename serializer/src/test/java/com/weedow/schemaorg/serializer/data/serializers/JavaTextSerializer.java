package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.serializer.serialization.datatype.AbstractTypeSerializer;

public class JavaTextSerializer extends AbstractTypeSerializer<String> {

    public JavaTextSerializer() {
        super(String.class);
    }

    @Override
    protected Object getValue(String value) {
        return "[" + value + "]";
    }
}