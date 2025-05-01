package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.serializer.serialization.datatype.AbstractTypeSerializer;

public class JavaBooleanSerializer extends AbstractTypeSerializer<Boolean> {

    public JavaBooleanSerializer() {
        super(Boolean.class);
    }

    @Override
    protected Object getValue(Boolean value) {
        return value ? "1" : "0";
    }
}