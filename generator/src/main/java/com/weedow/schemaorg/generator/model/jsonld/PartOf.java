package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * A related resource in which the item is physically or logically included.
 */
@Data
public class PartOf {

    /**
     * Resource id.
     *
     * @return The resource id
     * @param id The resource id
     */
    @JsonProperty("@id")
    private String id;
}
