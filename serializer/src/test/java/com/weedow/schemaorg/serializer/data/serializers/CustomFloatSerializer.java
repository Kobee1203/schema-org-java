package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializer;
import com.weedow.schemaorg.serializer.serialization.datatype.FloatSerializer;
import org.schema.model.datatype.Float;

@JsonLdDataTypeSerializer
public class CustomFloatSerializer extends FloatSerializer {

    public CustomFloatSerializer() {
        super(Float.class);
    }

    @Override
    protected Object getValue(JsonLdDataType<java.lang.Number> jsonLdDataType) {
        return jsonLdDataType.getValue().toString();
    }
}