package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.parser.ParserOptions;

import java.util.Map;

/**
 * Interface for handlers that process Schema.org graph items into type definitions.
 */
public interface ModelHandler {

    /**
     * Determines whether this handler supports processing the given graph item.
     *
     * @param graphItem the graph item to check
     * @param options parser options for configuration
     * @return true if this handler supports the graph item, false otherwise
     */
    boolean supports(GraphItem graphItem, ParserOptions options);

    /**
     * Handles the processing of a Schema.org graph item into a type definition.
     *
     * @param schemaDefinitions map of type IDs to Type objects
     * @param graphItem the graph item to process
     * @param options parser options for configuration
     */
    void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem, ParserOptions options);

}
