package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializer;
import com.weedow.schemaorg.serializer.serialization.datatype.BooleanSerializer;
import org.schema.model.datatype.Boolean;

@JsonLdDataTypeSerializer
public class CustomBooleanSerializer extends BooleanSerializer {

    public CustomBooleanSerializer() {
        super(Boolean.class);
    }

    @Override
    protected Object getValue(JsonLdDataType<java.lang.Boolean> jsonLdDataType) {
        return jsonLdDataType.getValue() ? "1" : "0";
    }
}