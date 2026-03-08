package com.weedow.schemaorg.generator.core.filter;

import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.model.Type;

import java.util.List;
import java.util.Map;

/**
 * Service for filtering Schema.org type definitions before code generation.
 * <p>
 * This filter can remove retired/archived types and optionally restrict generation
 * to a specific subset of types by ID.
 */
public interface SchemaDefinitionFilter {

    /**
     * Filters schema definitions based on the provided criteria.
     * <p>
     * The filtering process:
     * <ul>
     *   <li>Removes types without names (retired/archived types)</li>
     *   <li>If modelIds is provided and not empty, includes only specified types plus their dependencies</li>
     *   <li>If filters is provided and not empty, filters the specified types or type properties</li>
     *   <li>Returns an unmodifiable map of the filtered definitions</li>
     * </ul>
     *
     * @param schemaDefinitions the complete map of schema type definitions
     * @param modelIds optional list of specific type IDs to include (null or empty for all types)
     * @param filters Optional list of type filters to apply (null or empty for no filters)
     * @return an unmodifiable map containing the filtered type definitions
     */
    Map<String, Type> filter(Map<String, Type> schemaDefinitions, List<String> modelIds, List<GeneratorOptions.FilterOption> filters);
}
