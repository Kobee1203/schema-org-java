package com.weedow.schemaorg.serializer.spec;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.util.function.Function;

public interface SerializerFunction extends Function<Class<? extends JsonLdDataType<?>>, JsonSerializer<? extends JsonLdDataType<?>>> {
}
