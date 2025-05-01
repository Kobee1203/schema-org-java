package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializer;
import com.weedow.schemaorg.serializer.serialization.datatype.CssSelectorTypeSerializer;
import org.schema.model.datatype.CssSelectorType;

@JsonLdDataTypeSerializer
public class CustomCssSelectorTypeSerializer extends CssSelectorTypeSerializer {

    public CustomCssSelectorTypeSerializer() {
        super(CssSelectorType.class);
    }

    @Override
    protected Object getValue(JsonLdDataType<String> jsonLdDataType) {
        return jsonLdDataType.getValue() + " #element-id";
    }
}