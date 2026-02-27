package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Used to state that one class is a subclass of another.
 */
@Data
public class SubClassOf {

    /** Type id */
    @JsonProperty("@id")
    private String id;
}
