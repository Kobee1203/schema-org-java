package com.weedow.schemaorg.generator.reader;

import java.io.IOException;

public class SchemaDefinitionReaderException extends IOException {
    public SchemaDefinitionReaderException(String message, IOException e) {
        super(message, e);
    }
}
