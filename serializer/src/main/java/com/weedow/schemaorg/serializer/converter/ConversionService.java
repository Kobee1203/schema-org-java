package com.weedow.schemaorg.serializer.converter;

import com.weedow.schemaorg.commons.model.JsonLdDataType;

/**
 * A service interface for type conversion. This is the entry point into the convert system.
 */
public interface ConversionService {

    /**
     * Convert the given {@code source} to the specified {@code targetType}.
     *
     * @param <T>        target type
     * @param source     the source object to convert (may be {@code null})
     * @param targetType the target type to convert to (required)
     * @return the converted object, an instance of targetType
     */
    <T extends JsonLdDataType<?>> T convert(Object source, Class<T> targetType);
}