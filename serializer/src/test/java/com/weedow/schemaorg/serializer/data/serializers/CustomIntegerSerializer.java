package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializer;
import com.weedow.schemaorg.serializer.serialization.datatype.IntegerSerializer;
import org.schema.model.datatype.Integer;

@JsonLdDataTypeSerializer
public class CustomIntegerSerializer extends IntegerSerializer {

    public CustomIntegerSerializer() {
        super(Integer.class);
    }

    @Override
    protected Object getValue(JsonLdDataType<java.lang.Number> jsonLdDataType) {
        return jsonLdDataType.getValue().doubleValue();
    }
}