package com.weedow.schemaorg.serializer.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.serializer.JsonLdConstants;
import com.weedow.schemaorg.serializer.JsonLdException;
import com.weedow.schemaorg.serializer.JsonLdSerializerOptions;
import com.weedow.schemaorg.serializer.JsonMapperUtils;

import java.util.List;
import java.util.Optional;

public class JsonLdSerializerImpl implements JsonLdSerializer {

    private final ObjectMapper objectMapper;

    public JsonLdSerializerImpl() {
        this(JsonLdSerializerOptions.builder().build());
    }

    public JsonLdSerializerImpl(JsonLdSerializerOptions options) {
        this.objectMapper = objectMapper(options);
    }

    private static ObjectMapper objectMapper(JsonLdSerializerOptions options) {
        JsonMapper.Builder builder = JsonMapperUtils.getJsonMapperBuilder();

        if (options.isPrettyPrint()) {
            builder.enable(SerializationFeature.INDENT_OUTPUT);
        }

        return builder.build();
    }

    @Override
    public String serialize(JsonLdNode object) throws JsonLdException {
        try {
            fixContext(object);
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonLdException(String.format("JSON-LD serialization internal error for type %s.", getTypeName(object)), e);
        }
    }

    @Override
    public String serialize(List<? extends JsonLdNode> objects) throws JsonLdException {
        try {
            Optional.ofNullable(objects).ifPresent(things -> things.forEach(JsonLdSerializerImpl::fixContext));
            return objectMapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            throw new JsonLdException(String.format("JSON-LD serialization internal error for type %s.", getTypeName(objects)), e);
        }
    }

    private static void fixContext(JsonLdNode object) {
        if (object != null) {
            final String context = object.getContext();
            object.setContext(context != null ? context : JsonLdConstants.CORE_NAMESPACE);
        }
    }

    private static String getTypeName(JsonLdNode object) {
        return object != null ? object.getType() : null;
    }

    private static String getTypeName(List<? extends JsonLdNode> objects) {
        if (objects != null && !objects.isEmpty()) {
            return objects.iterator().next().getType();
        }
        return null;
    }
}
