package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents a comment annotation with language and value from JSON-LD.
 */
@Data
public class Comment {

    /**
     * Language of the comment.
     *
     * @return The language of the comment
     * @param language The language of the comment
     */
    @JsonProperty("@language")
    private String language;

    /**
     * Comment value.
     *
     * @return The comment value
     * @param value The comment value
     */
    @JsonProperty("@value")
    private String value;
}
