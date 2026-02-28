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

/**
 * Represents a Schema.org property definition with its field, accessor, and mutators.
 * <p>
 * A property defines a relationship between types and includes:
 * <ul>
 *   <li>Field metadata (name, type)</li>
 *   <li>Accessor (getter methods)</li>
 *   <li>Mutators (setter/adder methods)</li>
 *   <li>Associated types that can be used as values</li>
 * </ul>
 */
@Value
public class Property {

    /**
     * The property identifier (e.g., "schema:name").
     *
     * @return The property identifier
     */
    String id;
    /**
     * The field definition containing type information.
     *
     * @return The field definition containing type information
     */
    Field field;
    /**
     * The accessor (getter) definition for this property.
     *
     * @return The accessor (getter) definition for this property
     */
    Accessor accessor;
    /**
     * The list of mutators (setters/adders) for this property.
     *
     * @return The list of mutators (setters/adders) for this property
     */
    List<Mutator> mutators;

    /**
     * The list of types that can be used as values for this property.
     *
     * @return The list of types that can be used as values for this property
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Type> types;

    /**
     * Returns a formatted string representation of the types for toString().
     *
     * @return a comma-separated list of type names in brackets, or null if no types
     */
    @ToString.Include
    private String types() {
        return Optional.ofNullable(types).map(t -> "[" + t.stream().map(Type::getName).collect(Collectors.joining(", ")) + "]").orElse(null);
    }

    /**
     * Returns a detailed formatted string representation of this property.
     * <p>
     * Includes the property ID, name, description (truncated to 50 chars), types,
     * partOf, source, and contributor information.
     *
     * @return a formatted string with all property details
     */
    public String toFormattedString() {
        return new StringJoiner(", ", "Property(", ")")
                .add("id='" + id + "'")
                .add("name='" + Optional.ofNullable(accessor).map(Accessor::getName).orElse(null) + "'")
                .add("description='" + Optional.ofNullable(accessor).map(Accessor::getDescription).map(s -> s.substring(0, Math.min(50, s.length()))).orElse(null) + "'")
                .add("types=" + types())
                .add("partOf=" + Optional.ofNullable(accessor).map(Accessor::getPartOf).orElse(null))
                .add("source=" + Optional.ofNullable(accessor).map(Accessor::getSource).orElse(null))
                .add("contributor=" + Optional.ofNullable(accessor).map(Accessor::getContributor).orElse(null))
                .toString();
    }
}
