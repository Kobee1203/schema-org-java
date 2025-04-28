package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializer;
import com.weedow.schemaorg.serializer.serialization.datatype.TextSerializer;
import org.schema.model.datatype.Text;

@JsonLdDataTypeSerializer(exactType = true)
public class CustomTextSerializer extends TextSerializer {

    public CustomTextSerializer() {
        super(Text.class);
    }

    @Override
    protected Object getValue(JsonLdDataType<String> jsonLdDataType) {
        return "[" + jsonLdDataType.getValue() + "]";
    }
}