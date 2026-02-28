package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents a label with language and value from JSON-LD.
 */
@Data
public class Label {

    /**
     * The language tag of the label (e.g., "en", "fr", "de").
     * <p>
     * Follows the BCP 47 language tag format.
     *
     * @return The language tag of the label
     * @param language The language tag of the label
     */
    @JsonProperty("@language")
    private String language;

    /**
     * The text value of the label in the specified language.
     *
     * @return The text value of the label in the specified language
     * @param value The text value of the label in the specified language
     */
    @JsonProperty("@value")
    private String value;
}
