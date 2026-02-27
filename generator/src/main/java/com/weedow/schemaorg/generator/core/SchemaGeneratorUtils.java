package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.commons.model.JsonLdFieldTypes;
import com.weedow.schemaorg.commons.model.JsonLdTypeName;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Utility class for resolving class names and computing imports for generated Schema.org types.
 * <p>
 * This class provides methods to:
 * <ul>
 *   <li>Resolve fully qualified class names based on package configuration</li>
 *   <li>Compute required imports for a type based on its properties</li>
 *   <li>Cache import computations for performance</li>
 * </ul>
 */
public final class SchemaGeneratorUtils {

    private SchemaGeneratorUtils() {
    }

    /**
     * Resolves the fully qualified class name for a type.
     * <p>
     * Returns null for types that use Java standard types (primitives, String, etc.).
     * Data types are placed in the dataTypePackage, while regular types use modelPackage.
     *
     * @param modelPackage the package for model interfaces
     * @param dataTypePackage the package for data types
     * @param type the type to resolve
     * @return the fully qualified class name, or null if the type uses a Java standard type
     */
    public static String resolveClassName(String modelPackage, String dataTypePackage, Type type) {
        if(type.isUsedJavaType()) {
            return null;
        }

        final String resolvedPackage = ModelUtils.isDataType(type.getId()) || ModelUtils.isSubDataType(type) ? dataTypePackage : modelPackage;
        return resolvedPackage + "." + type.getName();
    }

    /**
     * Computes the import statements needed for a type based on its direct properties.
     * <p>
     * Includes additional imports specified in the additionalImports list.
     *
     * @param modelPackage the package for model interfaces
     * @param dataTypePackage the package for data types
     * @param type the type to compute imports for
     * @param additionalImports additional imports to include
     * @return a set of fully qualified class names to import
     */
    public static Set<String> getImports(String modelPackage, String dataTypePackage, Type type, List<String> additionalImports) {
        final Set<String> imports = new TreeSet<>(additionalImports);
        type.getProperties()
                .stream()
                .flatMap(property -> property.getTypes().stream())
                .map(propertyType -> resolveClassName(modelPackage, dataTypePackage, propertyType))
                .filter(Objects::nonNull)
                .forEach(imports::add);
        return imports;
    }

    private static final Map<Type, Set<String>> ALL_IMPORTS_BY_TYPE_CACHE = new ConcurrentSkipListMap<>(Comparator.comparing(Type::getId));

    /**
     * Computes all import statements needed for a type including inherited properties.
     * <p>
     * This method recursively collects imports from:
     * <ul>
     *   <li>Direct properties of the type</li>
     *   <li>Properties inherited from parent types</li>
     *   <li>Required JSON-LD annotations (JsonLdTypeName, JsonLdFieldTypes)</li>
     *   <li>Java utility classes (List)</li>
     * </ul>
     * Results are cached for performance.
     *
     * @param modelPackage the package for model interfaces
     * @param dataTypePackage the package for data types
     * @param type the type to compute all imports for
     * @return a set of all fully qualified class names to import (returns a copy to prevent cache modification)
     */
    public static Set<String> getAllImports(String modelPackage, String dataTypePackage, Type type) {
        Set<String> allImports = ALL_IMPORTS_BY_TYPE_CACHE.computeIfAbsent(type, t -> {
            final Set<String> imports = getImports(modelPackage, dataTypePackage, t, Collections.emptyList());
            if (!t.getParents().isEmpty()) {
                t.getParents().stream()
                        .flatMap(parentType -> getAllImports(modelPackage, dataTypePackage, parentType).stream())
                        .forEach(imports::add);
            }
            Optional.ofNullable(resolveClassName(modelPackage, dataTypePackage, t)).ifPresent(imports::add);
            imports.add(JsonLdTypeName.class.getName());
            if (t.getAllProperties().stream().anyMatch(property -> property.getTypes().size() > 1)) {
                imports.add(JsonLdFieldTypes.class.getName());
            }
            imports.add(java.util.List.class.getName());
            return imports;
        });
        // Copy the Set to prevent the modifications in the cached Set
        return new TreeSet<>(allImports);
    }

    /**
     * Clears the import computation cache.
     * <p>
     * Should be called between generation runs to ensure fresh computations.
     */
    public static void clearCache() {
        ALL_IMPORTS_BY_TYPE_CACHE.clear();
    }
}
