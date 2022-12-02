package com.weedow.schemaorg.serializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDataTypeDeserializer;
import com.weedow.schemaorg.serializer.deserialization.JsonLdTypeFactory;
import com.weedow.schemaorg.serializer.serialization.JsonLdDataTypeSerializer;

import java.io.IOException;

public final class JsonMapperUtils {

    private JsonMapperUtils() {
    }

    public static JsonMapper.Builder getJsonMapperBuilder() {
        TypeFactory typeFactory = new JsonLdTypeFactory();

        JsonMapper.Builder builder = JsonMapper.builder()
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
                .activateDefaultTypingAsProperty(
                        BasicPolymorphicTypeValidator.builder()
                                .allowIfBaseType(Object.class)
                                .allowIfSubTypeIsArray()
                                .build(),
                        ObjectMapper.DefaultTyping.NON_FINAL,
                        "@type"
                )
                .typeFactory(typeFactory)
                .addMixIn(JsonLdNodeImpl.class, JsonLdNodeMixIn.class);

        SimpleModule dataTypeModule = new SimpleModule("JsonLdDataType Module")
                .addSerializer(new JsonLdDataTypeSerializer())
                .addDeserializer(JsonLdDataType.class, new JsonLdDataTypeDeserializer());
        builder.addModule(dataTypeModule);

        builder.addModule(
                new SimpleModule("Text").addKeyDeserializer(JsonLdDataType.class, new KeyDeserializer() {
                    @Override
                    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
                        return null;
                    }
                })
        );
        try {
            builder.addModule(
                    new SimpleModule("Text").addDeserializer((Class<Object>)Class.forName("org.schema.model.datatype.Text"), new JsonDeserializer<Object>() {
                        @Override
                        public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
                            return null;
                        }
                    })
            );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            builder.addModule(
                    new SimpleModule("Text").addDeserializer((Class<Object>)Class.forName("org.schema.model.datatype.Text"), new JsonDeserializer<Object>() {
                        @Override
                        public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
                            return null;
                        }
                    })
            );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return builder;
    }
}
