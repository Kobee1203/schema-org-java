package com.weedow.schemaorg.generator.model.utils;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.PartOf;
import com.weedow.schemaorg.generator.model.jsonld.RangeIncludes;
import com.weedow.schemaorg.generator.model.jsonld.Source;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ModelUtils {

    private static final String SCHEMA_DATA_TYPE = "schema:DataType";

    private static final Map<String, String> DATA_TYPE_MAPPING = Map.of(
            SCHEMA_DATA_TYPE, "-",
            "schema:Boolean", "java.lang.Boolean",
            "schema:Text", "java.lang.String",
            "schema:URL", "java.net.URL",
            "schema:Number", "java.lang.Number",
            "schema:Integer", "java.lang.Integer",
            "schema:Float", "java.lang.Float",
            "schema:Date", "java.time.LocalDate",
            "schema:Time", "java.time.LocalTime",
            "schema:DateTime", "java.time.LocalDateTime"
    );

    private ModelUtils() {
    }

    public static String getJavaType(String typeId, String defaultValue) {
        return !SCHEMA_DATA_TYPE.equals(typeId) ? DATA_TYPE_MAPPING.getOrDefault(typeId, defaultValue) : defaultValue;
    }

    public static boolean isDataType(String typeId) {
        return DATA_TYPE_MAPPING.containsKey(typeId);
    }

    public static boolean isSubDataType(Type type) {
        final List<Type> parents = type.getParents();
        return parents.size() == 1 && isDataType(parents.get(0).getId());
    }

    public static boolean isEnumeration(Type type) {
        return type.isEnumerationType() && !type.getEnumerationMembers().isEmpty();
    }

    public static List<Type> getPropertyTypes(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        final List<RangeIncludes> rangeIncludes = graphItem.getRangeIncludes();
        return rangeIncludes != null ? rangeIncludes.stream().map(rangIncludes -> getType(schemaDefinitions, rangIncludes.getId())).collect(Collectors.toList()) : Collections.emptyList();
    }

    public static Type getType(Map<String, Type> schemaDefinitions, String typeId) {
        return schemaDefinitions.computeIfAbsent(typeId, Type::new);
    }

    public static String getTypeId(String typeId) {
        return typeId.equals("rdfs:Class") ? "schema:Class" : typeId;
    }

    public static List<String> getSource(GraphItem graphItem) {
        final List<Source> source = graphItem.getSource();
        return source != null ? source.stream().map(Source::getId).collect(Collectors.toList()) : Collections.emptyList();
    }

    public static List<String> getPartOf(GraphItem graphItem) {
        final List<PartOf> partOf = graphItem.getPartOf();
        return partOf != null ? partOf.stream().map(PartOf::getId).collect(Collectors.toList()) : Collections.emptyList();
    }

    public static String capitalize(String str) {
        return str == null || str.length() <= 1 ? str : str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String[] getSplitDescription(String description) {
        return description != null ? description.replace("\\n", "<br/>").split("\\n") : null;
    }
}
