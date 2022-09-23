package com.weedow.schemaorg.generator.reader;

import com.weedow.schemaorg.generator.model.jsonld.SchemaDefinition;

import java.io.InputStream;

public interface SchemaDefinitionReader {

    SchemaDefinition read(InputStream in) throws SchemaDefinitionReaderException;

}
