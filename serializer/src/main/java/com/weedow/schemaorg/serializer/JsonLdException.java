package com.weedow.schemaorg.serializer;

/**
 * Represents the exception related to JSON-LD error during serializing.
 */
public class JsonLdException extends SerializerException {

    /**
     * Constructs a new JsonLdException.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public JsonLdException(String message, Throwable cause) {
        super(message, cause);
    }
}
