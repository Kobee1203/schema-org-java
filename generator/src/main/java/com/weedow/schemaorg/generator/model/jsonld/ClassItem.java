package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

/**
 * Represents a Schema.org Class item from the JSON-LD specification.
 */
@Data
@JsonTypeName("rdfs:Class")
public class ClassItem extends GraphItem {
}
