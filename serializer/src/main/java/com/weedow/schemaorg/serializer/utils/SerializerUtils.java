package com.weedow.schemaorg.serializer.utils;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SerializerUtils {

    private static final Map<Class<? extends JsonLdDataType<?>>, Type> CACHE = new ConcurrentHashMap<>();

    private SerializerUtils() {
    }

    public static Class<?> findClass(String className, TypeFactory typeFactory) {
        Class<?> clazz;
        try {
            clazz = typeFactory.findClass(className);
        } catch (ClassNotFoundException e) {
            clazz = null;
        }
        return clazz;
    }

    @SuppressWarnings("unchecked")
    public static Type getJavaType(JsonLdDataType<?> jsonLdDataType) {
        Class<? extends JsonLdDataType<?>> dataTypeClass = (Class<? extends JsonLdDataType<?>>) jsonLdDataType.getClass();
        return getJavaType(dataTypeClass);
    }

    public static Type getJavaType(Class<? extends JsonLdDataType<?>> dataTypeClass) {
        return CACHE.computeIfAbsent(dataTypeClass, clazz -> {
            Type type = null;
            Type genericSuperclass = clazz.getGenericSuperclass();
            while (genericSuperclass != null && !(genericSuperclass instanceof ParameterizedType)) {
                genericSuperclass = ((Class<?>) genericSuperclass).getGenericSuperclass();
            }
            if (genericSuperclass != null) {
                Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
                if (actualTypeArguments.length > 0) {
                    type = actualTypeArguments[0];
                }
            }
            return type;
        });
    }
}
