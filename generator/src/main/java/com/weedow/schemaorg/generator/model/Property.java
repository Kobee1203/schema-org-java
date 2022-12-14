package com.weedow.schemaorg.generator.model;

import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Value
public class Property {

    String id;
    Field field;
    Accessor accessor;
    List<Mutator> mutators;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Type> types;

    @ToString.Include
    private String types() {
        return Optional.ofNullable(types).map(t -> "[" + t.stream().map(Type::getName).collect(Collectors.joining(", ")) + "]").orElse(null);
    }

    public String toFormattedString() {
        return new StringJoiner(", ", "Property(", ")")
                .add("id='" + id + "'")
                .add("name='" + Optional.ofNullable(accessor).map(Accessor::getName).orElse(null) + "'")
                .add("description='" + Optional.ofNullable(accessor).map(Accessor::getDescription).map(s -> s.substring(0, Math.min(50, s.length()))).orElse(null) + "'")
                .add("types=" + types())
                .add("partOf=" + Optional.ofNullable(accessor).map(Accessor::getPartOf).orElse(null))
                .add("source=" + Optional.ofNullable(accessor).map(Accessor::getSource).orElse(null))
                .toString();
    }

    @Value
    public static class Field {

        String name;
        Supplier<String> fieldType;

        public String getFieldName() {
            return ModelUtils.getFieldName(name);
        }

        public String getFieldType() {
            return fieldType != null ? fieldType.get() : null;
        }
    }

    @Value
    public static class Accessor {

        String name;
        String description;
        List<String> partOf;
        List<String> source;
        Supplier<String> fieldTypeLinks;
        Supplier<String> returnFieldType;
        Supplier<String> cast;

        public String getFieldName() {
            return ModelUtils.getFieldName(name);
        }

        public String getMethodName() {
            return "get" + ModelUtils.capitalize(name);
        }

        public String[] getSplitDescription() {
            return ModelUtils.getSplitDescription(description);
        }

        public String getFieldTypeLinks() {
            return fieldTypeLinks != null ? fieldTypeLinks.get() : null;
        }

        public String getReturnFieldType() {
            return returnFieldType != null ? returnFieldType.get() : null;
        }

        public String getCast() {
            return cast != null ? cast.get() : null;
        }
    }

    @Value
    public static class Mutator {

        String name;
        String description;
        List<String> partOf;
        List<String> source;

        @Getter(AccessLevel.NONE)
        Supplier<String> paramType;

        @Getter(AccessLevel.NONE)
        Supplier<String> paramValue;

        public String getFieldName() {
            return ModelUtils.getFieldName(name);
        }

        public String getMethodName() {
            return "set" + ModelUtils.capitalize(name);
        }

        public String[] getSplitDescription() {
            return ModelUtils.getSplitDescription(description);
        }

        public String getParamType() {
            return paramType != null ? paramType.get() : null;
        }

        public String getParamValue() {
            return paramValue != null ? paramValue.get() : null;
        }
    }
}
