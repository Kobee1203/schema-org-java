package com.weedow.schemaorg.serializer.deserialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.io.IOException;

public class JsonLdDataTypeDeserializer extends StdDeserializer<JsonLdDataType<?>> {

    public JsonLdDataTypeDeserializer() {
        super(JsonLdDataType.class);
    }

    @Override
    public JsonLdDataType<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        TreeNode treeNode = p.getCodec().readTree(p);
        return null;
    }
}
