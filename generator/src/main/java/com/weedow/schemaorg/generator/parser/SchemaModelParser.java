package com.weedow.schemaorg.generator.parser;

import com.weedow.schemaorg.generator.model.Type;

import java.util.Map;

/**
 * Parser for Schema.org definitions that builds a type model.
 * <p>
 * Implementations of this interface are responsible for reading Schema.org JSON-LD definitions
 * and converting them into a structured model of types that can be used for code generation.
 */
public interface SchemaModelParser {

    /**
     * Parses Schema.org definitions according to the specified options and returns a map of types.
     *
     * @param options the parser configuration options (schema version, resource location, custom data types, etc.)
     * @return a map where keys are type identifiers and values are {@link Type} definitions
     */
    Map<String, Type> parse(ParserOptions options);

}
