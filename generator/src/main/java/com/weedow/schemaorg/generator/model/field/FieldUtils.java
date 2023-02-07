package com.weedow.schemaorg.generator.model.field;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public final class FieldUtils {

    private FieldUtils() {
    }

    public static <T, R> Supplier<R> supplier(T obj, Function<T, R> function) {
        return () -> Optional.ofNullable(obj).map(function).orElse(null);
    }
}
