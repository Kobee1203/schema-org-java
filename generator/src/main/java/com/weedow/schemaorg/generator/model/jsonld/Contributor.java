package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents a contributor reference from JSON-LD.
 */
@Data
public class Contributor {

    /** Contributor id */
    @JsonProperty("@id")
    private String id;
}
