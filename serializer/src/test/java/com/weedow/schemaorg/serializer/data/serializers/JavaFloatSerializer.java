package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.serializer.serialization.datatype.AbstractTypeSerializer;

public class JavaFloatSerializer extends AbstractTypeSerializer<Float> {

    public JavaFloatSerializer() {
        super(Float.class);
    }

    @Override
    protected Object getValue(Float value) {
        return value.toString();
    }
}