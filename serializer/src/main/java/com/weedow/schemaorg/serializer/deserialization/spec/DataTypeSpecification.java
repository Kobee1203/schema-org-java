package com.weedow.schemaorg.serializer.deserialization.spec;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.converter.Converter;

import java.util.Objects;

public final class DataTypeSpecification {

    private final String dataTypeName;
    private final DeserializerFunction deserializerFunction;
    private final Converter<Object, JsonLdDataType<?>> converter;

    public DataTypeSpecification(String dataTypeName, DeserializerFunction deserializerFunction, Converter<Object, JsonLdDataType<?>> converter) {
        this.dataTypeName = dataTypeName;
        this.deserializerFunction = deserializerFunction;
        this.converter = converter;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public DeserializerFunction getDeserializerFunction() {
        return deserializerFunction;
    }

    public Converter<Object, JsonLdDataType<?>> getConverter() {
        return converter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataTypeSpecification that = (DataTypeSpecification) o;
        return Objects.equals(dataTypeName, that.dataTypeName) && Objects.equals(deserializerFunction, that.deserializerFunction) && Objects.equals(converter, that.converter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataTypeName, deserializerFunction, converter);
    }
}
