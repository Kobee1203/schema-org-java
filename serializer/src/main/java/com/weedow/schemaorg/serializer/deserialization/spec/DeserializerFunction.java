package com.weedow.schemaorg.serializer.deserialization.spec;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.util.function.Function;

public interface DeserializerFunction extends Function<Class<?>, JsonDeserializer<JsonLdDataType<?>>> {
}
