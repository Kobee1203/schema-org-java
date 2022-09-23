package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SupersededBy {

    @JsonProperty("@id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DomainIncludes{" +
                "id='" + id + '\'' +
                '}';
    }
}
