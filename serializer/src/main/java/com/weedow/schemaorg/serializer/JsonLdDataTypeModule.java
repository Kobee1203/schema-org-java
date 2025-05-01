package com.weedow.schemaorg.serializer;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDataTypeDeserializerModifier;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializerModifier;
import java.io.Serial;

public class JsonLdDataTypeModule extends SimpleModule {

    @Serial
    private static final long serialVersionUID = 1L;

    public JsonLdDataTypeModule() {
        super("JsonLdDataType Module");
        setDeserializerModifier(new JsonLdDataTypeDeserializerModifier());
        setSerializerModifier(new JsonLdDataTypeSerializerModifier());
    }
}