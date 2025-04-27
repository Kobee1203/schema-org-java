package com.weedow.schemaorg.serializer.deserialization.spec;

import com.weedow.schemaorg.commons.model.SchemaDataType;
import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.converter.Converter;

public record DataTypeSpecification(
        SchemaDataType dataType,
        DeserializerFunction deserializerFunction,
        Converter<Object, JsonLdDataType<?>> converter
) {
}
