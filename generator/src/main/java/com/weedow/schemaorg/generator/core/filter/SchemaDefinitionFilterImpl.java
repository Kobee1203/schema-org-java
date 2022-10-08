package com.weedow.schemaorg.generator.core.filter;

import com.weedow.schemaorg.generator.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SchemaDefinitionFilterImpl implements SchemaDefinitionFilter {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaDefinitionFilterImpl.class);

    @Override
    public Map<String, Type> filter(Map<String, Type> schemaDefinitions, List<String> modelIds) {
        Map<String, Type> filteredSchemaDefinitions = new HashMap<>(schemaDefinitions);
        if (modelIds != null && !modelIds.isEmpty()) {
            filteredSchemaDefinitions = modelIds.stream()
                    // Fix model id (format 'schema:xxx')
                    .map(modelId -> modelId.contains(":") ? modelId : "schema:" + modelId)
                    // Filter existing models, skip models not found
                    .filter(modelId -> {
                        final String typeId = modelId.contains(":") ? modelId : "schema:" + modelId;
                        return schemaDefinitions.containsKey(typeId);
                    })
                    .flatMap(modelId -> {
                        Set<Type> types = new LinkedHashSet<>();
                        Type type = schemaDefinitions.get(modelId);
                        addType(types, type);
                        return types.stream();
                    })
                    .collect(Collectors.toUnmodifiableMap(Type::getId, Function.identity(), (oldValue, newValue) -> oldValue));
        }

        return filteredSchemaDefinitions;
    }

    private static void addType(Set<Type> types, Type type) {
        if (!types.contains(type)) {
            LOG.debug("adding type: {}", type.getId());
            types.add(type);
            type.getParents().forEach(parent -> addType(types, parent));
            type.getAllProperties().forEach(property -> property.getTypes().forEach(propertyType -> addType(types, propertyType)));
        }
    }
}
