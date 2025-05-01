package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializer;
import com.weedow.schemaorg.serializer.serialization.datatype.NumberSerializer;
import org.schema.model.datatype.Number;

import java.math.BigDecimal;

@JsonLdDataTypeSerializer
public class CustomNumberSerializer extends NumberSerializer {

    public CustomNumberSerializer() {
        super(Number.class);
    }

    @Override
    protected Object getValue(JsonLdDataType<java.lang.Number> jsonLdDataType) {
        return BigDecimal.valueOf(jsonLdDataType.getValue().doubleValue());
    }
}