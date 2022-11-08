package com.weedow.schemaorg.serializer.serializer;

/**
 * Represents the exception related to JSON-LD error during serializing.
 */
public class JsonLdException extends SerializerException {

    public JsonLdException(String message, Throwable cause) {
        super(message, cause);
    }
}
