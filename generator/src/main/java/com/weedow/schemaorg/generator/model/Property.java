package com.weedow.schemaorg.generator.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Value
public class Property {

    String id;

    String name;

    String fieldName;

    String description;

    @EqualsAndHashCode.Exclude
    List<Type> types;

    List<String> partOf;

    List<String> source;

    public String[] getSplitDescription() {
        return description != null ? description.replace("\\n", "<br/>").split("\\n") : null;
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
