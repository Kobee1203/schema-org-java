package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.commons.model.JsonLdTypeName;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

public final class SchemaGeneratorUtils {

    private SchemaGeneratorUtils() {
    }

    public static String resolveClassName(String modelPackage, String dataTypePackage, Type type) {
        final String resolvedPackage = ModelUtils.isDataType(type.getId()) || ModelUtils.isSubDataType(type) ? dataTypePackage : modelPackage;
        return resolvedPackage + "." + type.getName();
    }

    public static Set<String> getImports(String modelPackage, String dataTypePackage, Type type, List<String> additionalImports) {
        final Set<String> imports = new LinkedHashSet<>(additionalImports);
        type.getProperties()
                .stream()
                .flatMap(property -> property.getTypes().stream())
                .forEach(propertyType -> imports.add(resolveClassName(modelPackage, dataTypePackage, propertyType)));
        return imports;
    }

    private static final Map<Type, Set<String>> ALL_IMPORTS_BY_TYPE_CACHE = new ConcurrentSkipListMap<>(Comparator.comparing(Type::getId));

    public static Set<String> getAllImports(String modelPackage, String dataTypePackage, Type type) {
        Set<String> allImports = ALL_IMPORTS_BY_TYPE_CACHE.computeIfAbsent(type, t -> {
            final Set<String> imports = getImports(modelPackage, dataTypePackage, t, Collections.emptyList());
            if (!t.getParents().isEmpty()) {
                t.getParents().stream()
                        .flatMap(parentType -> getAllImports(modelPackage, dataTypePackage, parentType).stream())
                        .forEach(imports::add);
            }
            imports.add(resolveClassName(modelPackage, dataTypePackage, type));
            imports.add(JsonLdTypeName.class.getName());
            return imports;
        });
        // Copy the Set to prevent the modifications in the cached Set
        return new LinkedHashSet<>(allImports);
    }

    public static void clearCache() {
        ALL_IMPORTS_BY_TYPE_CACHE.clear();
    }
}
