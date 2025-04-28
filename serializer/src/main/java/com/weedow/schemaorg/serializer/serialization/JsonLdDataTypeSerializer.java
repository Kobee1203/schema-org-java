package com.weedow.schemaorg.serializer.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to indicate that a custom serializer implements specific JSON-LD DataType serialization.
 * <p>
 * This annotation is used to mark serializers that are designed to handle the serialization of
 * specific JSON-LD DataType implementations within the Schema.org serialization process.
 * <p>
 * Serializers annotated with {@code JsonLdDataTypeSerializer} are intended to support the Schema.org
 * JSON-LD serialization framework and may be registered with mechanisms such as
 * {@link JsonLdDataTypeSerializerModifier} or similar customization approaches to enhance
 * the serialization behavior.
 * <p>
 * Applicable to custom serializers used with {@code JsonLdDataType} objects.
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonLdDataTypeSerializer {

    /**
     * Indicates whether the custom serializer strictly handles the exact match of the data type
     * associated with this serializer.
     *
     * @return true if the serializer strictly handles exact type matching; false otherwise.
     */
    boolean exactType() default false;
}
