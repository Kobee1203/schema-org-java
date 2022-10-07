package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RangeIncludes {

    @JsonProperty("@id")
    private String id;
}
