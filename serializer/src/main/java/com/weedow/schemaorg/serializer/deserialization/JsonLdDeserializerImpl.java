package com.weedow.schemaorg.serializer.deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.serializer.JsonLdException;
import com.weedow.schemaorg.serializer.JsonMapperUtils;

public class JsonLdDeserializerImpl implements JsonLdDeserializer {

    private final ObjectMapper objectMapper;

    public JsonLdDeserializerImpl() {
        this.objectMapper = objectMapper();
    }

    private static ObjectMapper objectMapper() {
        JsonMapper.Builder builder = JsonMapperUtils.getJsonMapperBuilder();
        return builder.build();
    }

    @Override
    public <T extends JsonLdNode> T deserialize(String json) throws JsonLdException {
        try {
            return (T) objectMapper.readValue(json, JsonLdNode.class);
        } catch (JsonProcessingException e) {
            throw new JsonLdException(String.format("JSON-LD deserialization internal error: %s.", e.getMessage()), e);
        }
    }
}
