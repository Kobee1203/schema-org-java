package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Label {

    @JsonProperty("@language")
    private String language;

    @JsonProperty("@value")
    private String value;
}
