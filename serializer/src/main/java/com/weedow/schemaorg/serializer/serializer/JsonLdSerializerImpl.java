package com.weedow.schemaorg.serializer.serializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;

import java.util.List;
import java.util.Optional;

public class JsonLdSerializerImpl implements JsonLdSerializer {

    private final ObjectMapper objectMapper;

    public JsonLdSerializerImpl() {
        this.objectMapper = objectMapper();
    }

    private static ObjectMapper objectMapper() {
        return JsonMapper.builder()
                // Register support for Java 8 date/time types (specified in JSR-310 specification)
                .findAndAddModules()
                .enable(JsonGenerator.Feature.IGNORE_UNKNOWN)
                .enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .addMixIn(JsonLdNodeImpl.class, JsonLdNodeMixIn.class)
                .build();
    }

    @Override
    public String serialize(JsonLdNode object) throws JsonLdException {
        try {
            fixContext(object);
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonLdException(String.format("JSON-LD serialize internal error for type %s.", getTypeName(object)), e);
        }
    }

    @Override
    public String serialize(List<? extends JsonLdNode> objects) throws JsonLdException {
        try {
            Optional.ofNullable(objects).ifPresent(things -> things.forEach(JsonLdSerializerImpl::fixContext));
            return objectMapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            throw new JsonLdException(String.format("JSON-LD serialize internal error for type %s.", getTypeName(objects)), e);
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
