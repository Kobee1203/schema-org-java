package com.weedow.schemaorg.generator.core.filter;

import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;
import com.weedow.schemaorg.generator.model.Type;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.weedow.schemaorg.generator.logging.Emojis.ARCHIVED;
import static com.weedow.schemaorg.generator.logging.LogMarkers.WARNING;

/**
 * Default implementation of {@link SchemaDefinitionFilter}.
 * <p>
 * This filter:
 * <ul>
 *   <li>Removes archived/retired types that have no name</li>
 *   <li>When specific model IDs are provided, includes only those types plus all their dependencies
 *       (parent types, property types, and enumeration subtypes)</li>
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
    public Map<String, Type> filter(Map<String, Type> schemaDefinitions, List<String> modelIds) {
        Map<String, Type> filteredSchemaDefinitions = new HashMap<>(schemaDefinitions);

        // Filters types without a “name.” These types have been retired from the vocabulary, but their IDs are still referenced by some properties (e.g. schema:DeliveryTimeSettings).
        filteredSchemaDefinitions = filteredSchemaDefinitions.entrySet().stream()
                .filter(entry -> {
                    Type type = entry.getValue();
                    if (type.getName() == null || type.getName().isEmpty()) {
                        LOG.info(WARNING, ARCHIVED, "** ARCHIVED **" + " {} has been retired from the vocabulary (see https://schema.org/docs/attic.home.html)", type.getId());
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (modelIds != null && !modelIds.isEmpty()) {
            filteredSchemaDefinitions = modelIds.stream()
                    // Fix model id (format 'schema:xxx')
                    .map(modelId -> modelId.contains(":") ? modelId : SchemaConstants.SCHEMA_PREFIX + modelId)
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

        // Unmodifiable Map
        return Collections.unmodifiableMap(filteredSchemaDefinitions);
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
                types.addAll(type.getSubTypes());
            }
        }
    }
}
