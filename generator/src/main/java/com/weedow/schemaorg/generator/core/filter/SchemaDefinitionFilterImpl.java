package com.weedow.schemaorg.generator.core.filter;

import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;
import com.weedow.schemaorg.generator.model.Type;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.weedow.schemaorg.generator.logging.Emojis.*;
import static com.weedow.schemaorg.generator.logging.LogMarkers.WARNING;

/**
 * Default implementation of {@link SchemaDefinitionFilter}.
 * <p>
 * This filter:
 * <ul>
 *   <li>Removes archived/retired types that have no name</li>
 *   <li>When specific model IDs are provided, includes only those types plus all their dependencies
 *       (parent types, property types, and enumeration subtypes)</li>
 *   <li>When filters are provided, remove the type properties or the type itself if properties are not specified</li>
 *   <li>Recursively adds all required dependencies to ensure completeness</li>
 * </ul>
 */
public class SchemaDefinitionFilterImpl implements SchemaDefinitionFilter {

    /** Default constructor */
    public SchemaDefinitionFilterImpl() {
        // empty
    }

    private static final Logger LOG = LoggerFactory.getLogger(SchemaDefinitionFilterImpl.class);

    @Override
    public Map<String, Type> filter(Map<String, Type> schemaDefinitions, List<String> modelIds, List<GeneratorOptions.FilterOption> filters) {
        Map<String, Type> filteredSchemaDefinitions = filterTypesWithoutName(schemaDefinitions);

        filteredSchemaDefinitions = applyFilters(filters, filteredSchemaDefinitions);

        filteredSchemaDefinitions = applyModelIds(modelIds, filteredSchemaDefinitions);

        // Unmodifiable Map
        return Collections.unmodifiableMap(filteredSchemaDefinitions);
    }

    /**
     * Filters types without a “name”.
     * These types have been retired from the vocabulary, but their IDs are still referenced by some properties (e.g. schema:DeliveryTimeSettings).
     *
     * @param schemaDefinitions the map of schema type definitions
     * @return a map containing the filtered type definitions
     */
    private static Map<String, Type> filterTypesWithoutName(Map<String, Type> schemaDefinitions) {
        return schemaDefinitions.entrySet().stream()
                .filter(entry -> {
                    Type type = entry.getValue();
                    if (type.getName() == null || type.getName().isEmpty()) {
                        LOG.info(WARNING, ARCHIVED, "** ARCHIVED **" + " {} has been retired from the vocabulary (see https://schema.org/docs/attic.home.html)", type.getId());
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Applies the given filter options to the schema definitions.
     *
     * @param filters the list of filter options to apply
     * @param schemaDefinitions the current map of schema type definitions
     * @return a map containing the filtered type definitions
     */
    private static Map<String, Type> applyFilters(List<GeneratorOptions.FilterOption> filters, Map<String, Type> schemaDefinitions) {
        final Map<String, Type> filteredSchemaDefinitions = new HashMap<>(schemaDefinitions);

        if (filters != null && !filters.isEmpty()) {
            filters.forEach(filterOption -> {
                String typeName = SchemaConstants.typeName(filterOption.getTypeName());
                GeneratorOptions.FilterMode mode = filterOption.getMode();
                List<String> propertyIds = filterOption.getProperties();
                Type type = filteredSchemaDefinitions.get(typeName);
                if (type != null) {
                    if (propertyIds != null && !propertyIds.isEmpty()) {
                        LOG.info(FILTERS, "Filtering properties: {} {} from {}", mode, propertyIds, typeName);
                        type.filterProperties(mode, propertyIds);
                    } else if (mode == GeneratorOptions.FilterMode.EXCLUDE) {
                        LOG.info(FILTERS, "Filtering type {} with mode {}", typeName, mode);
                        filteredSchemaDefinitions.remove(typeName);
                    }
                }
            });
        }

        return filteredSchemaDefinitions;
    }

    /**
     * Filters the schema definitions to include only the specified model IDs and their dependencies.
     *
     * @param modelIds the list of model IDs to include
     * @param schemaDefinitions the current map of schema type definitions
     * @return a map containing only the requested models and their dependencies
     */
    private static Map<String, Type> applyModelIds(List<String> modelIds, Map<String, Type> schemaDefinitions) {
        if (modelIds != null && !modelIds.isEmpty()) {
            LOG.info(MODELS, "Model IDs specified: {}", modelIds);
            return modelIds.stream()
                    // Fix model id (format 'schema:xxx')
                    .map(SchemaConstants::typeName)
                    // Filter existing models, skip models not found
                    .filter(schemaDefinitions::containsKey)
                    .flatMap(modelId -> {
                        Set<Type> types = new LinkedHashSet<>();
                        Type type = schemaDefinitions.get(modelId);
                        addType(types, type);
                        return types.stream();
                    })
                    .collect(Collectors.toMap(Type::getId, Function.identity(), (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        }

        return schemaDefinitions;
    }

    /**
     * Recursively adds a type and all its dependencies to the set.
     * <p>
     * Dependencies include:
     * <ul>
     *   <li>Parent types (inheritance chain)</li>
     *   <li>Property types (types used by all properties)</li>
     *   <li>Subtypes (for enumeration types)</li>
     * </ul>
     *
     * @param types the set to add types to
     * @param type the type to add along with its dependencies
     */
    private static void addType(Set<Type> types, Type type) {
        if (!types.contains(type)) {
            LOG.verbose("adding type: {}", type.getId());
            types.add(type);
            type.getParents().forEach(parent -> addType(types, parent));
            type.getAllProperties().forEach(property -> property.getTypes().forEach(propertyType -> addType(types, propertyType)));
            if (type.isEnumerationType()) {
                type.getSubTypes().forEach(subType -> addType(types, subType));
            }
        }
    }
}
