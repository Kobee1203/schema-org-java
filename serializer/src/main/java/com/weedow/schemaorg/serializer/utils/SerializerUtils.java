package com.weedow.schemaorg.serializer.utils;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.weedow.schemaorg.commons.model.JsonLdDataType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Utility class for serialization operations.
 * Provides helper methods for working with types and reflection in serialization context.
 */
public final class SerializerUtils {

    private static final Map<Class<? extends JsonLdDataType<?>>, Type> CACHE = new ConcurrentHashMap<>();

    private SerializerUtils() {
    }

    /**
     * Finds a class by name using the provided type factory.
     *
     * @param className the fully qualified class name
     * @param typeFactory the Jackson type factory
     * @return the Class object, or null if not found
     */
    public static Class<?> findClass(String className, TypeFactory typeFactory) {
        Class<?> clazz;
        try {
            clazz = typeFactory.findClass(className);
        } catch (ClassNotFoundException e) {
            clazz = null;
        }
        return clazz;
    }

    /**
     * Gets the Java type wrapped by a JsonLdDataType instance.
     *
     * @param jsonLdDataType the data type instance
     * @return the wrapped Java type
     */
    @SuppressWarnings("unchecked")
    public static Type getJavaType(JsonLdDataType<?> jsonLdDataType) {
        Class<? extends JsonLdDataType<?>> dataTypeClass = (Class<? extends JsonLdDataType<?>>) jsonLdDataType.getClass();
        return getJavaType(dataTypeClass);
    }

    /**
     * Gets the Java type wrapped by a JsonLdDataType class.
     *
     * @param dataTypeClass the data type class
     * @return the wrapped Java type
     */
    public static Type getJavaType(Class<? extends JsonLdDataType<?>> dataTypeClass) {
        return CACHE.computeIfAbsent(dataTypeClass, clazz -> getTypeParameter(clazz.getGenericSuperclass()));
    }

    /**
     * Extracts the type parameter from a generic type.
     *
     * @param genericType the generic type to extract from
     * @return the type parameter, or null if not found
     */
    public static Type getTypeParameter(Type genericType) {
        Type type = null;
        while (genericType != null && !(genericType instanceof ParameterizedType)) {
            genericType = ((Class<?>) genericType).getGenericSuperclass();
        }
        if (genericType != null) {
            Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
            if (actualTypeArguments.length > 0) {
                type = actualTypeArguments[0];
            }
        }
        return type;
    }
}
