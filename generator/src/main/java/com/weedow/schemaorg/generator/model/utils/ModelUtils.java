package com.weedow.schemaorg.generator.model.utils;

import com.weedow.schemaorg.commons.model.SchemaDataType;
import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.*;

import javax.lang.model.SourceVersion;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Utility methods for working with Schema.org model types and properties.
 */
public final class ModelUtils {

    private static final Map<String, String> DATA_TYPE_MAPPING = Map.of(
            SchemaConstants.SCHEMA_DATA_TYPE, "-",
            SchemaConstants.SCHEMA_PREFIX + SchemaDataType.BOOLEAN.getName(), java.lang.Boolean.class.getName(),
            SchemaConstants.SCHEMA_PREFIX + SchemaDataType.TEXT.getName(), java.lang.String.class.getName(),
            SchemaConstants.SCHEMA_PREFIX + SchemaDataType.URL.getName(), java.net.URL.class.getName(),
            SchemaConstants.SCHEMA_PREFIX + SchemaDataType.NUMBER.getName(), java.lang.Number.class.getName(),
            SchemaConstants.SCHEMA_PREFIX + SchemaDataType.INTEGER.getName(), java.lang.Integer.class.getName(),
            SchemaConstants.SCHEMA_PREFIX + SchemaDataType.FLOAT.getName(), java.lang.Float.class.getName(),
            SchemaConstants.SCHEMA_PREFIX + SchemaDataType.DATE.getName(), java.time.LocalDate.class.getName(),
            SchemaConstants.SCHEMA_PREFIX + SchemaDataType.TIME.getName(), java.time.LocalTime.class.getName(),
            SchemaConstants.SCHEMA_PREFIX + SchemaDataType.DATE_TIME.getName(), java.time.LocalDateTime.class.getName()
    );

    private ModelUtils() {
    }

    /**
     * Returns the Java type name for a given Schema.org type identifier.
     *
     * @param typeId          the Schema.org type identifier
     * @param customDataTypes custom data type mappings
     * @param defaultValue    the default value to return if no mapping exists
     * @return the Java type name
     */
    public static String getJavaType(String typeId, Map<String, String> customDataTypes, String defaultValue) {
        if (SchemaConstants.SCHEMA_DATA_TYPE.equals(typeId)) {
            return defaultValue;
        }

        if (customDataTypes != null && customDataTypes.containsKey(typeId)) {
            return customDataTypes.get(typeId);
        }

        return DATA_TYPE_MAPPING.getOrDefault(typeId, defaultValue);
    }

    /**
     * Checks if the given type identifier represents a Schema.org data type.
     *
     * @param typeId the Schema.org type identifier
     * @return {@code true} if the type is a data type, {@code false} otherwise
     */
    public static boolean isDataType(String typeId) {
        return DATA_TYPE_MAPPING.containsKey(typeId);
    }

    /**
     * Checks if the given type is a sub-type of a Schema.org data type.
     *
     * @param type the type to check
     * @return {@code true} if the type is a sub-data type, {@code false} otherwise
     */
    public static boolean isSubDataType(Type type) {
        final List<Type> parents = type.getParents();
        return parents.size() == 1 && isDataType(parents.get(0).getId());
    }

    /**
     * Checks if the given type is an enumeration.
     *
     * @param type the type to check
     * @return {@code true} if the type is an enumeration, {@code false} otherwise
     */
    public static boolean isEnumeration(Type type) {
        return type.isEnumerationType();
    }

    /**
     * Returns the property types defined in a graph item's range includes.
     *
     * @param schemaDefinitions the schema definitions map
     * @param graphItem         the graph item
     * @return the list of property types
     */
    public static List<Type> getPropertyTypes(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        final List<RangeIncludes> rangeIncludes = graphItem.getRangeIncludes();
        return rangeIncludes != null ? rangeIncludes.stream()
                .filter(rangeInclude -> rangeInclude.getId().startsWith(SchemaConstants.SCHEMA_PREFIX))
                .map(rangeInclude -> getType(schemaDefinitions, rangeInclude.getId())).toList() : Collections.emptyList();
    }

    /**
     * Returns the type for the given type identifier, creating it if necessary.
     *
     * @param schemaDefinitions the schema definitions map
     * @param typeId            the type identifier
     * @return the type
     */
    public static Type getType(Map<String, Type> schemaDefinitions, String typeId) {
        return schemaDefinitions.computeIfAbsent(typeId, Type::new);
    }

    /**
     * Returns the normalized type identifier, converting RDFS class to Schema.org class.
     *
     * @param typeId the type identifier
     * @return the normalized type identifier
     */
    public static String getTypeId(String typeId) {
        return typeId.equals(SchemaConstants.RDFS_CLASS) ? "schema:Class" : typeId;
    }

    /**
     * Returns the source identifiers from a graph item.
     *
     * @param graphItem the graph item
     * @return the list of source identifiers
     */
    public static List<String> getSource(GraphItem graphItem) {
        final List<Source> source = graphItem.getSource();
        return source != null ? source.stream().map(Source::getId).toList() : Collections.emptyList();
    }

    /**
     * Returns the partOf identifiers from a graph item.
     *
     * @param graphItem the graph item
     * @return the list of partOf identifiers
     */
    public static List<String> getPartOf(GraphItem graphItem) {
        final List<PartOf> partOf = graphItem.getPartOf();
        return partOf != null ? partOf.stream().map(PartOf::getId).toList() : Collections.emptyList();
    }

    /**
     * Returns the contributor identifiers from a graph item.
     *
     * @param graphItem the graph item
     * @return the list of contributor identifiers
     */
    public static List<String> getContributor(GraphItem graphItem) {
        final List<Contributor> contributor = graphItem.getContributor();
        return contributor != null ? contributor.stream().map(Contributor::getId).toList() : Collections.emptyList();
    }

    /**
     * Returns a valid Java field name, appending an underscore if the name is a Java keyword.
     *
     * @param name the field name
     * @return the valid Java field name
     */
    public static String getFieldName(String name) {
        return isJavaKeyword(name) ? name + "_" : name;
    }

    private static boolean isJavaKeyword(String keyword) {
        return keyword != null && SourceVersion.isKeyword(keyword);
    }

    /**
     * Returns a method name by combining a prefix, field name, and suffix.
     *
     * @param fieldName the field name
     * @param prefix    the method name prefix
     * @param suffix    the method name suffix
     * @return the method name
     */
    public static String getMethodName(String fieldName, String prefix, String suffix) {
        return prefix + (!prefix.isBlank() ? capitalize(fieldName) : fieldName) + suffix;
    }

    private static String capitalize(String str) {
        return str == null || str.length() <= 1 ? str : str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * Splits a description by newlines, converting them to HTML breaks.
     *
     * @param description the description to split
     * @return the array of split description lines
     */
    public static String[] getSplitDescription(String description) {
        return description != null ? description.replace("\\n", "<br/>").split("\\n") : null;
    }
}
