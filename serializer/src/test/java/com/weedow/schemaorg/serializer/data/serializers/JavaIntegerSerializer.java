package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.serializer.serialization.datatype.AbstractTypeSerializer;

public class JavaIntegerSerializer extends AbstractTypeSerializer<Integer> {

    public JavaIntegerSerializer() {
        super(Integer.class);
    }

    @Override
    protected Object getValue(Integer value) {
        return value.doubleValue();
    }
}