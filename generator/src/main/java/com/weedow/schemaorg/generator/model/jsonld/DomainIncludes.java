package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Relates a property to a class that is (one of) the type(s) the property is expected to be used on.
 */
@Data
public class DomainIncludes {

    /**
     * Type id.
     *
     * @return The type id
     * @param id The type id
     */
    @JsonProperty("@id")
    private String id;
}
