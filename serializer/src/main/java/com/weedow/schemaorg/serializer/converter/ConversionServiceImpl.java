package com.weedow.schemaorg.serializer.converter;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.deserialization.spec.DataTypeSpecificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConversionServiceImpl implements ConversionService {

    private static final Logger LOG = LoggerFactory.getLogger(ConversionServiceImpl.class);

    private final List<Converter<Object, ? extends JsonLdDataType<?>>> converters = DataTypeSpecificationService.getInstance().getConverters();

    private final Map<ConverterCacheKey, Converter<Object, ? extends JsonLdDataType<?>>> converterCache = new ConcurrentHashMap<>();

    @Override
    public <T extends JsonLdDataType<?>> T convert(Object source, Class<T> targetType) {
        Converter<Object, T> converter = getConverter((source != null ? source.getClass() : null), targetType);
        return converter != null ? converter.convert(source, targetType) : null;
    }

    @SuppressWarnings("unchecked")
    private <T extends JsonLdDataType<?>> Converter<Object, T> getConverter(Class<?> sourceType, Class<T> targetType) {
        final ConverterCacheKey key = new ConverterCacheKey(sourceType, targetType);
        return (Converter<Object, T>) converterCache.computeIfAbsent(key, t -> getJsonLdDataTypeConverter(sourceType, targetType));
    }

    private <T extends JsonLdDataType<?>> Converter<Object, ? extends JsonLdDataType<?>> getJsonLdDataTypeConverter(Class<?> sourceType, Class<T> targetType) {
        for (Converter<Object, ? extends JsonLdDataType<?>> converter : converters) {
            if (converter.supports(targetType, sourceType)) {
                return converter;
            }
        }

        LOG.warn("Could not found a converter for type {}", targetType);

        return null;
    }

    /**
     * Key for use with the converter cache.
     */
    private record ConverterCacheKey(
            Class<?> sourceType,
            Class<?> targetType
    ) implements Comparable<ConverterCacheKey> {

        @Override
        public int compareTo(ConverterCacheKey other) {
            int result = this.sourceType.toString().compareTo(other.sourceType.toString());
            if (result == 0) {
                result = this.targetType.toString().compareTo(other.targetType.toString());
            }
            return result;
        }
    }
}
