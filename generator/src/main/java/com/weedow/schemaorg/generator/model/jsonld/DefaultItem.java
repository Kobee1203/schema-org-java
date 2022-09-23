package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type", visible = true, defaultImpl = DefaultItem.class)
public class DefaultItem extends GraphItem {
}
