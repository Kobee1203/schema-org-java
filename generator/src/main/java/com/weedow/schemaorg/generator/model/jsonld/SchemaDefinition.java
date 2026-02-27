package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Represents a Schema.org JSON-LD definition containing the context and graph structure.
 * <p>
 * This class maps to the root structure of a Schema.org vocabulary definition file.
 *
 * @see <a href="https://schema.org/">https://schema.org/</a>
 * @see <a href="https://json-ld.org/">https://json-ld.org/</a>
 */
@Data
public class SchemaDefinition {

    /**
     * The JSON-LD context that defines the vocabulary mappings.
     * <p>
     * Maps property names to their corresponding Schema.org URIs.
     */
    @JsonProperty("@context")
    private Map<String, String> context;

    /**
     * The graph containing all Schema.org types and properties definitions.
     * <p>
     * This list contains all classes, properties, data types, and enumerations defined in the Schema.org vocabulary.
     */
    @JsonProperty("@graph")
    private List<GraphItem> graph;
}
