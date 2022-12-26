package com.weedow.schemaorg.commons.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// Not much to test, but exercise to prevent code coverage tool from showing red
class ClassUtilsTest {

    @Test
    void getDefaultClassLoader() {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        Assertions.assertThat(classLoader.getName()).isEqualTo("app");
    }

    @Test
    void getDefaultClassLoader_when_contact_class_loader_is_null() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(null);
            ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            Assertions.assertThat(classLoader.getName()).isEqualTo("app");
        } finally {
            Thread.currentThread().setContextClassLoader(contextClassLoader);
        }
    }

}