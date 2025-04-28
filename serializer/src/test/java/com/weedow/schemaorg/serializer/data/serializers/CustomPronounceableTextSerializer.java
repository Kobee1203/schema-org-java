package com.weedow.schemaorg.serializer.data.serializers;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializer;
import com.weedow.schemaorg.serializer.serialization.datatype.PronounceableTextSerializer;
import org.schema.model.datatype.PronounceableText;

@JsonLdDataTypeSerializer
public class CustomPronounceableTextSerializer extends PronounceableTextSerializer {

    public CustomPronounceableTextSerializer() {
        super(PronounceableText.class);
    }

    @Override
    protected Object getValue(JsonLdDataType<String> jsonLdDataType) {
        return "speak:" + jsonLdDataType.getValue();
    }
}