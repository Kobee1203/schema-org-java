package com.weedow.schemaorg.serializer.spec;

import com.weedow.schemaorg.commons.model.SchemaDataType;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.converter.Converter;

public record DataTypeSpecification(
        SchemaDataType dataType,
        SerializerFunction serializerFunction,
        DeserializerFunction deserializerFunction,
        Converter<Object, JsonLdDataType<?>> converter
) {
}
