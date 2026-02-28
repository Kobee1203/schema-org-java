package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents a contributor reference from JSON-LD.
 */
@Data
public class Contributor {

    /**
     * Contributor id.
     *
     * @return The contributor id
     * @param id The contributor id
     */
    @JsonProperty("@id")
    private String id;
}
