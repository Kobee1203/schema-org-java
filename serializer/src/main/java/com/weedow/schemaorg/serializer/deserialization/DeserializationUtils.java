package com.weedow.schemaorg.serializer.deserialization;

import com.fasterxml.jackson.databind.type.TypeFactory;

public final class DeserializationUtils {

    private DeserializationUtils() {
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
}
