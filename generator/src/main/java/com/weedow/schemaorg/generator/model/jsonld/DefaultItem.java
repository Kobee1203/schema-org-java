package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * Default implementation for unspecialized graph items from JSON-LD.
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type", visible = true, defaultImpl = DefaultItem.class)
public class DefaultItem extends GraphItem {
}
