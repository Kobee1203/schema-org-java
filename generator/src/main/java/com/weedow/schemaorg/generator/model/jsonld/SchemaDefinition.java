package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class SchemaDefinition {

    @JsonProperty("@context")
    private Map<String, String> context;

    @JsonProperty("@graph")
    private List<GraphItem> graph;

    public Map<String, String> getContext() {
        return context;
    }

    public void setContext(Map<String, String> context) {
        this.context = context;
    }

    public List<GraphItem> getGraph() {
        return graph;
    }

    public void setGraph(List<GraphItem> graph) {
        this.graph = graph;
    }
}
