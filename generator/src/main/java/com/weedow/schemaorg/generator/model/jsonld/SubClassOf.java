package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubClassOf {

    @JsonProperty("@id")
    private String id;
}
