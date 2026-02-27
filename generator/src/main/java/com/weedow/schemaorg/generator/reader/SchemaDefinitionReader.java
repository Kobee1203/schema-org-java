package com.weedow.schemaorg.generator.reader;

import com.weedow.schemaorg.generator.model.jsonld.SchemaDefinition;

import java.io.InputStream;

/**
 * Interface to read a Schema.org content and return a SchemaDefinition.
 */
public interface SchemaDefinitionReader {

    /**
     * Reads the given input representing a Schema.org content and returns a SchemaDefinition.
     *
     * @param in Schema.org content
     * @return SchemaDefinition
     * @throws SchemaDefinitionReaderException when the given input cannot be read.
     */
    SchemaDefinition read(InputStream in) throws SchemaDefinitionReaderException;

}
