package com.weedow.schemaorg.generator.core.stream;

import com.weedow.schemaorg.generator.model.Type;

import java.util.Map;
import java.util.stream.Stream;

/**
 * Service for creating streams from Schema.org type definitions.
 * <p>
 * This service provides control over whether to use sequential or parallel streams
 * based on configuration (e.g., verbose mode).
 */
public interface StreamService {

    /**
     * Creates a stream of types from the schema definitions.
     * <p>
     * The implementation may return either a sequential or parallel stream
     * depending on configuration settings.
     *
     * @param schemaDefinitions the map of schema type definitions to stream
     * @return a stream of types for processing
     */
    Stream<Type> stream(Map<String, Type> schemaDefinitions);
}
