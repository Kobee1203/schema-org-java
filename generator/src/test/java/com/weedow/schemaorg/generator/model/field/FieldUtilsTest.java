package com.weedow.schemaorg.generator.model.field;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

class FieldUtilsTest {

    @Test
    void supplier() {
        int value1 = 10;
        Supplier<Integer> result1 = FieldUtils.supplier(value1, v -> v + 10);
        Assertions.assertThat(result1.get()).isEqualTo(20);

        String value2 = "Hello";
        Supplier<String> result2 = FieldUtils.supplier(value2, v -> v + " World!");
        Assertions.assertThat(result2.get()).isEqualTo("Hello World!");
    }

    @Test
    void supplier_with_null_value() {
        Supplier<Integer> result1 = FieldUtils.supplier((Integer) null, v -> v + 10);
        Assertions.assertThat(result1.get()).isNull();

        Supplier<String> result2 = FieldUtils.supplier((String) null, v -> v + " World!");
        Assertions.assertThat(result2.get()).isNull();
    }
}