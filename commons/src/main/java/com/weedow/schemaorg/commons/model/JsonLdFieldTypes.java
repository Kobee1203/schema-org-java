package com.weedow.schemaorg.commons.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to indicate the types allowed for a field.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonLdFieldTypes {

    /**
     * Allowed types of annotated field.
     *
     * @return Allowed types of annotated field
     */
    Class<?>[] value() default {};
}
