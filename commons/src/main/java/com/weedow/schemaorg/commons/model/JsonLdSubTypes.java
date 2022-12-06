package com.weedow.schemaorg.commons.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to indicate subtypes of a Schema.org type, and to associate JSON-LD names used within JSON-LD content.
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonLdSubTypes {

    /**
     * Subtypes of the annotated type.
     *
     * @return Subtypes of the annotated type
     */
    String[] value() default {};
}
