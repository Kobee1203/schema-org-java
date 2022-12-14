package com.weedow.schemaorg.serializer.deserialization.spec;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.util.function.BiFunction;

public interface DeserializerFunction extends BiFunction<JavaType, JsonDeserializer<?>, JsonDeserializer<JsonLdDataType<?>>> {
}
