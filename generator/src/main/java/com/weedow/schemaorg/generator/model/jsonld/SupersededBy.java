package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/** Relates a term (i.e. a property, class or enumeration) to one that supersedes it. */
@Data
public class SupersededBy {

    /**
     * Type id.
     *
     * @return The type id
     * @param id The type id
     */
    @JsonProperty("@id")
    private String id;
}
