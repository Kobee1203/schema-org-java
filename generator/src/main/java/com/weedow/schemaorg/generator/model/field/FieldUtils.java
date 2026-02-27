package com.weedow.schemaorg.generator.model.field;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Utility class for field-related operations.
 */
public final class FieldUtils {

    private FieldUtils() {
    }

    /**
     * Creates a lazy supplier that applies a function to an object.
     *
     * @param <T> the type of the input object
     * @param <R> the type of the result
     * @param obj the input object
     * @param function the function to apply
     * @return a supplier that lazily evaluates the function
     */
    public static <T, R> Supplier<R> supplier(T obj, Function<T, R> function) {
        return () -> Optional.ofNullable(obj).map(function).orElse(null);
    }
}
