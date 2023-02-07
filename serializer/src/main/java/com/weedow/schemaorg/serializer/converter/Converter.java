package com.weedow.schemaorg.serializer.converter;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * A converter converts a source object of type {@code S} to a target of type {@code T}.
 *
 * @param <S> the source type
 * @param <T> the target type
 */
public interface Converter<S, T> {

    /**
     * Whether the current converter supports the conversion from the given {@code jsonLdDataType} to the given {@code targetType}.
     *
     * @param jsonLdDataType JsonLdDataType class
     * @param targetType     Target type
     * @return {@code true} if the converter supports the conversion from {@code jsonLdDataType} to {@code targetType}, {@code false} instead.
     */
    boolean supports(Class<? extends JsonLdDataType<?>> jsonLdDataType, Class<?> targetType);

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source     the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @param targetType the target type that extends JsonLdDataType
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     */
    T convert(S source, Class<T> targetType);
}
