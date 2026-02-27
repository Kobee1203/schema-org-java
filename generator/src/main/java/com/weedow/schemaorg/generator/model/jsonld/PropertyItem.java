package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

/**
 * Represents a Schema.org property definition in the JSON-LD graph.
 * <p>
 * Properties define the relationships between Schema.org types and their values.
 * They correspond to RDF properties in the Schema.org vocabulary.
 *
 * @see <a href="https://schema.org/Property">https://schema.org/Property</a>
 */
@Data
@JsonTypeName("rdf:Property")
public class PropertyItem extends GraphItem {
}
