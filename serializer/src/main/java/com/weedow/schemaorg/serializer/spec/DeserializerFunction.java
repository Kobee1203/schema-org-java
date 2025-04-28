package com.weedow.schemaorg.serializer.spec;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.util.function.Function;

public interface DeserializerFunction extends Function<Class<? extends JsonLdDataType<?>>, JsonDeserializer<? extends JsonLdDataType<?>>> {
}
