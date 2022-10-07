package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SchemaDefinition {

    @JsonProperty("@context")
    private Map<String, String> context;

    @JsonProperty("@graph")
    private List<GraphItem> graph;
}
