package com.weedow.schemaorg.serializer.deserialization.spec;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.converter.Converter;

public record DataTypeSpecification(
        String dataTypeName,
        DeserializerFunction deserializerFunction,
        Converter<Object, JsonLdDataType<?>> converter
) {
}
