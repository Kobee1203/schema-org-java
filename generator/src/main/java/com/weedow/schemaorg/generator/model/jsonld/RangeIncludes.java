package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Relates a property to a class that constitutes (one of) the expected type(s) for values of the property.
 */
@Data
public class RangeIncludes {

    /**
     * Type id.
     *
     * @return The type id
     * @param id The type id
     */
    @JsonProperty("@id")
    private String id;
}
