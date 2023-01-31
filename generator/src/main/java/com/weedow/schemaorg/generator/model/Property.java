package com.weedow.schemaorg.generator.model;

import com.weedow.schemaorg.generator.model.field.Accessor;
import com.weedow.schemaorg.generator.model.field.Field;
import com.weedow.schemaorg.generator.model.field.Mutator;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
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
}
