package com.weedow.schemaorg.serializer.spec;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.commons.model.SchemaDataType;
import com.weedow.schemaorg.serializer.converter.Converter;

/**
 * Specification for a Schema.org data type including its serializer, deserializer, and converter.
 *
 * @param dataType the Schema.org data type
 * @param serializerFunction function to create a serializer for this data type
 * @param deserializerFunction function to create a deserializer for this data type
 * @param converter converter for this data type
 */
public record DataTypeSpecification(
        SchemaDataType dataType,
        SerializerFunction serializerFunction,
        DeserializerFunction deserializerFunction,
        Converter<Object, JsonLdDataType<?>> converter
) {
}
