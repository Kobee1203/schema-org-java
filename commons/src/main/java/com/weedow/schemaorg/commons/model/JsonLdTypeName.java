package com.weedow.schemaorg.commons.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used for binding logical name that the annotated class has.
 * <p>
 * Used to set the data type of JSON-LD node or typed value, using the {@code @type} keyword when defining a term within an {@code @context} section.
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonLdTypeName {

    /**
     * Logical type name for annotated type.
     * If missing (or defined as Empty String), defaults to using non-qualified class name as the type.
     *
     * @return logical type name
     */
    String value() default "";
}