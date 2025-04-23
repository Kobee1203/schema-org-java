package com.weedow.schemaorg.serializer.deserialization;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.AsPropertyTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonLdCollectionDeserializer extends StdDeserializer<List<?>> {

    private final DeserializationConfig config;
    private final JavaType contentType;
    private final transient JsonDeserializer<?> deserializer;
    private final transient JsonDeserializer<?> singleValueDeserializer;

    public JsonLdCollectionDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer, JsonDeserializer<?> singleValueDeserializer) {
        super(List.class);
        this.config = config;
        this.contentType = beanDesc.getType().getContentType();
        this.deserializer = deserializer;
        this.singleValueDeserializer = singleValueDeserializer;
    }

    @Override
    public List<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return (List<?>) deserializer.deserialize(p, ctxt);
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        if (p.isExpectedStartArrayToken()) {
            return deserializeWithType(p);
        } else if (singleValueDeserializer != null) {
            ClassNameIdResolver typeIdResolver = ClassNameIdResolver.construct(contentType, config, config.getPolymorphicTypeValidator());
            TypeDeserializer asPropertyTypeDeserializer = new AsPropertyTypeDeserializer(
                    contentType,
                    typeIdResolver,
                    typeDeserializer.getPropertyName(),
                    false,
                    ctxt.constructType(typeDeserializer.getDefaultImpl()),
                    JsonTypeInfo.As.PROPERTY,
                    true
            );
            // Use Arrays.asList because we may need to replace the value in the list, and Arrays.asList(...) allows calling the List.set(index, Object) method. See DeserializerPostProcessorImpl
            // noinspection ArraysAsListWithZeroOrOneArgument
            return Arrays.asList(singleValueDeserializer.deserializeWithType(p, ctxt, asPropertyTypeDeserializer));
        } else {
            return deserializeWithType(p);
        }
    }

    private List<Object> deserializeWithType(JsonParser p) throws IOException {
        final Class<?> rawClass = contentType.getRawClass();

        final ObjectCodec objectCodec = p.getCodec();
        final JsonNode listOrObjectNode = objectCodec.readTree(p);

        final List<Object> result = new ArrayList<>();
        if (listOrObjectNode.isArray()) {
            for (JsonNode node : listOrObjectNode) {
                result.add(objectCodec.treeToValue(node, rawClass));
            }
        } else {
            result.add(objectCodec.treeToValue(listOrObjectNode, rawClass));
        }
        return result;
    }
}
