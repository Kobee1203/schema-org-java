package com.weedow.schemaorg.generator.model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Property {

    private final String id;

    private final String name;

    private final String fieldName;

    private final String description;

    private final List<Type> types;

    private final List<String> partOf;

    private final List<String> source;

    public Property(String id, String name, String fieldName, String description, List<Type> types, List<String> partOf, List<String> source) {
        this.id = id;
        this.name = name;
        this.fieldName = fieldName;
        this.types = types;
        this.description = description;
        this.partOf = partOf;
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getDescription() {
        return description;
    }

    public String[] getSplitDescription() {
        return description != null ? description.replace("\\n", "<br/>").split("\\n") : null;
    }

    public List<Type> getTypes() {
        return types;
    }

    public String getParamFieldType() {
        return this.types.size() > 1 ? "Object" : types.get(0).getName();
    }

    public String getReturnFieldType() {
        return this.types.size() > 1 ? "<T> T" : types.get(0).getName();
    }

    public String getReturnJavadoc() {
        return this.types.stream().map(type -> "{@link " + type.getName() + "}").collect(Collectors.joining(" or "));
    }

    public List<String> getPartOf() {
        return partOf;
    }

    public List<String> getSource() {
        return source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(id, property.id)
                && Objects.equals(name, property.name)
                && Objects.equals(fieldName, property.fieldName)
                && Objects.equals(description, property.description)
                && Objects.equals(partOf, property.partOf)
                && Objects.equals(source, property.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fieldName, description, partOf, source);
    }

    @Override
    public String toString() {
        return "Property{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + Optional.ofNullable(description).map(s -> s.substring(0, Math.min(50, s.length()))).orElse(null) + '\'' +
                ", types=[" + types.stream().map(Type::getName).collect(Collectors.joining(", ")) + "]" +
                ", partOf=" + partOf +
                ", source=" + source +
                '}';
    }
}
