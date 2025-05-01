package com.weedow.schemaorg.serializer.deserialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to indicate that a custom deserializer implements specific JSON-LD DataType deserialization.
 * <p>
 * This annotation is used to mark deserializers that are designed to handle the deserialization of
 * specific JSON-LD DataType implementations within the Schema.org deserialization process.
 * <p>
 * Deserializers annotated with {@code JsonLdDataTypeDeserializer} are intended to support the Schema.org
 * JSON-LD deserialization framework and may be registered with mechanisms such as
 * {@link JsonLdDataTypeDeserializerModifier} or similar customization approaches to enhance
 * the deserialization behavior.
 * <p>
 * Applicable to custom deserializers used with {@code JsonLdDataType} objects.
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonLdDataTypeDeserializer {
}
