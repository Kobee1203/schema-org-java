package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * The source or cause of the item.
 */
@Data
public class Source {

    /**
     * Source id.
     *
     * @return The source id
     * @param id The source id
     */
    @JsonProperty("@id")
    private String id;
}
