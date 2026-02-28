package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Used to state that one class is a subclass of another.
 */
@Data
public class SubClassOf {

    /**
     * Type id.
     *
     * @return The type id
     * @param id The type id
     */
    @JsonProperty("@id")
    private String id;
}
