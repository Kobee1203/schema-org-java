package com.weedow.schemaorg.generator.core.filter;

import com.weedow.schemaorg.generator.model.Type;

import java.util.List;
import java.util.Map;

public interface SchemaDefinitionFilter {

    Map<String, Type> filter(Map<String, Type> schemaDefinitions, List<String> modelIds);
}
