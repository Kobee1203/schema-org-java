package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.serializer.serialization.datatype.AbstractTypeSerializer;

import java.math.BigDecimal;

public class JavaNumberSerializer extends AbstractTypeSerializer<Number> {

    public JavaNumberSerializer() {
        super(Number.class);
    }

    @Override
    protected Object getValue(Number value) {
        return BigDecimal.valueOf(value.doubleValue());
    }
}