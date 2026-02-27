package com.weedow.schemaorg.generator.reader;

import java.io.IOException;

/**
 * Exception thrown when an error occurs while reading a Schema.org definition.
 * <p>
 * This exception wraps underlying {@link IOException}s that may occur during
 * the parsing of Schema.org JSON-LD files.
 */
public class SchemaDefinitionReaderException extends IOException {
    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the detail message explaining the error
     * @param e the underlying IOException that caused this exception
     */
    public SchemaDefinitionReaderException(String message, IOException e) {
        super(message, e);
    }
}
