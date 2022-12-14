package com.weedow.schemaorg.serializer.converter;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.lang.reflect.Type;

/**
 * A converter converts a source object of type {@code S} to a target of type {@code T}.
 *
 * @param <S> the source type
 * @param <T> the target type
 */
public interface Converter<S, T> {

    /**
     * Whether the given jsonLdDataType is supported by the current converter.
     *
     * @param jsonLdDataType JsonLdDataType class
     * @param javaType       Java type related to the JsonLdDataType class
     * @return {@code true} if the converter supports the given jsonLdDataType, {@code false} instead.
     */
    boolean supports(Class<?> sourceType, Class<? extends JsonLdDataType<?>> jsonLdDataType, Type javaType);

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source     the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @param targetType the target type that extends JsonLdDataType
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     */
    T convert(S source, Class<T> targetType);
}
