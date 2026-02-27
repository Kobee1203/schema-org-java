package com.weedow.schemaorg.serializer;

/**
 * Base exception for serialization and deserialization errors.
 */
public class SerializerException extends Exception {

    /**
     * Constructs a new SerializerException.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public SerializerException(String message, Throwable cause) {
        super(message, cause);
    }
}
