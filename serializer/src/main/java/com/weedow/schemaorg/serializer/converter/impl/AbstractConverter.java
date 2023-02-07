package com.weedow.schemaorg.serializer.converter.impl;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.serializer.converter.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public abstract class AbstractConverter implements Converter<Object, JsonLdDataType<?>> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractConverter.class);

    private Method instanceMethod;

    @Override
    public JsonLdDataType<?> convert(Object source, Class<JsonLdDataType<?>> targetType) {
        try {
            Object value = getValue(source);
            if (value != null) {
                return (JsonLdDataType<?>) getInstanceMethod(targetType).invoke(null, value);
            }
        } catch (Exception e) {
            LOG.debug("Could not convert {} to target type {}", source, targetType);
        }
        return null;
    }

    protected abstract Object getValue(Object source);

    private Method getInstanceMethod(Class<JsonLdDataType<?>> jsonLdDataType) {
        if (this.instanceMethod == null) {
            this.instanceMethod = Arrays.stream(jsonLdDataType.getDeclaredMethods())
                    .filter(method -> "of".equals(method.getName()) && Modifier.isStatic(method.getModifiers()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Could not find the 'of' method"));
        }
        return this.instanceMethod;
    }
}
