package com.weedow.schemaorg.serializer.serialization;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.serializer.JsonLdConstants;
import com.weedow.schemaorg.serializer.JsonLdException;
import com.weedow.schemaorg.serializer.JsonLdNodeMixIn;
import com.weedow.schemaorg.serializer.JsonLdSerializerOptions;

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
        JsonMapper.Builder builder = JsonMapper.builder()
                // Register support for Java 8 date/time types (specified in JSR-310 specification)
                .findAndAddModules()
                .enable(JsonGenerator.Feature.IGNORE_UNKNOWN)
                .enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
                //.propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
                //.enable(MapperFeature.USE_STD_BEAN_NAMING)
                .visibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .addMixIn(JsonLdNodeImpl.class, JsonLdNodeMixIn.class)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        SimpleModule dataTypeModule = new SimpleModule("JsonLdDataType Module")
                .addSerializer(new JsonLdDataTypeSerializer());
        builder.addModule(dataTypeModule);

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
