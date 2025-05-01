package com.weedow.schemaorg.generator.model.utils;

import com.weedow.schemaorg.commons.model.SchemaDataType;
import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.PartOf;
import com.weedow.schemaorg.generator.model.jsonld.RangeIncludes;
import com.weedow.schemaorg.generator.model.jsonld.Source;

import javax.lang.model.SourceVersion;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    public static String getJavaType(String typeId, Map<String, String> customDataTypes, String defaultValue) {
        if (SchemaConstants.SCHEMA_DATA_TYPE.equals(typeId)) {
            return defaultValue;
        }

        if (customDataTypes != null && customDataTypes.containsKey(typeId)) {
            return customDataTypes.get(typeId);
        }

        return DATA_TYPE_MAPPING.getOrDefault(typeId, defaultValue);
    }

    public static boolean isDataType(String typeId) {
        return DATA_TYPE_MAPPING.containsKey(typeId);
    }

    public static boolean isSubDataType(Type type) {
        final List<Type> parents = type.getParents();
        return parents.size() == 1 && isDataType(parents.get(0).getId());
    }

    public static boolean isEnumeration(Type type) {
        return type.isEnumerationType();
    }

    public static List<Type> getPropertyTypes(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        final List<RangeIncludes> rangeIncludes = graphItem.getRangeIncludes();
        return rangeIncludes != null ? rangeIncludes.stream().map(rangIncludes -> getType(schemaDefinitions, rangIncludes.getId())).toList() : Collections.emptyList();
    }

    public static Type getType(Map<String, Type> schemaDefinitions, String typeId) {
        return schemaDefinitions.computeIfAbsent(typeId, Type::new);
    }

    public static String getTypeId(String typeId) {
        return typeId.equals(SchemaConstants.RDFS_CLASS) ? "schema:Class" : typeId;
    }

    public static List<String> getSource(GraphItem graphItem) {
        final List<Source> source = graphItem.getSource();
        return source != null ? source.stream().map(Source::getId).toList() : Collections.emptyList();
    }

    public static List<String> getPartOf(GraphItem graphItem) {
        final List<PartOf> partOf = graphItem.getPartOf();
        return partOf != null ? partOf.stream().map(PartOf::getId).toList() : Collections.emptyList();
    }

    public static String getFieldName(String name) {
        return isJavaKeyword(name) ? name + "_" : name;
    }

    private static boolean isJavaKeyword(String keyword) {
        return keyword != null && SourceVersion.isKeyword(keyword);
    }

    public static String getMethodName(String fieldName, String prefix, String suffix) {
        return prefix + (!prefix.isBlank() ? capitalize(fieldName) : fieldName) + suffix;
    }

    private static String capitalize(String str) {
        return str == null || str.length() <= 1 ? str : str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String[] getSplitDescription(String description) {
        return description != null ? description.replace("\\n", "<br/>").split("\\n") : null;
    }
}
