package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

/**
 * Represents a Schema.org DataType item from the JSON-LD specification.
 */
@Data
@JsonTypeName("schema:DataType")
public class DataTypeItem extends ClassItem {
}
