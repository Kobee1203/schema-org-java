package com.weedow.schemaorg.generator.model;

import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a Schema.org type (class or enumeration) with its properties and hierarchy.
 * <p>
 * A type contains:
 * <ul>
 *   <li>Basic metadata (id, name, description)</li>
 *   <li>Java type mapping information</li>
 *   <li>Properties defined directly on this type</li>
 *   <li>Parent types and subtypes (inheritance hierarchy)</li>
 *   <li>Enumeration members (if this is an enumeration type)</li>
 *   <li>Source and contributor metadata</li>
 * </ul>
 */
@Data
@Accessors(chain = true)
public final class Type {

    private static final Comparator<Property> PROPERTY_COMPARATOR = Comparator.comparing(Property::getId);

    /**
     * The type identifier (e.g., "schema:Person").
     *
     * @return The type identifier
     */
    private final String id;

    /**
     * The Java type name to use for this Schema.org type.
     *
     * @return The Java type name to use for this Schema.org type
     * @param javaType The Java type name to use for this Schema.org type
     */
    private String javaType;

    /**
     * Whether this type is mapped to a Java primitive or standard type.
     *
     * @return Whether this type is mapped to a Java primitive or standard type
     * @param usedJavaType Whether this type is mapped to a Java primitive or standard type
     */
    private boolean usedJavaType;

    /**
     * Whether this type can be converted to a string representation.
     *
     * @return Whether this type can be converted to a string representation
     * @param stringifiable Whether this type can be converted to a string representation
     */
    private boolean stringifiable;

    /**
     * The type name (e.g., "Person").
     *
     * @return The type name
     * @param name The type name
     */
    private String name;

    /**
     * The description of this type.
     *
     * @return The description of this type
     * @param description The description of this type
     */
    private String description;

    /**
     * The set of properties defined directly on this type.
     *
     * @return The set of properties defined directly on this type
     */
    @Setter(AccessLevel.NONE)
    private final Set<Property> properties = new TreeSet<>(PROPERTY_COMPARATOR);

    /**
     * Cached set of all properties including inherited ones.
     *
     * @return Cached set of all properties including inherited ones
     */
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Set<Property> allProperties;

    /**
     * The base parent type category.
     *
     * @return The base parent type category
     * @param baseParent The base parent type category
     */
    private BaseType baseParent;

    /**
     * The list of parent types this type extends.
     *
     * @return The list of parent types this type extends
     */
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private final List<Type> parents = new ArrayList<>();

    /**
     * The list of subtypes that extend this type.
     *
     * @return The list of subtypes that extend this type
     */
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final List<Type> subTypes = new ArrayList<>();

    /**
     * The partOf identifiers indicating which specifications include this type.
     *
     * @return The partOf identifiers indicating which specifications include this type
     * @param partOf The partOf identifiers indicating which specifications include this type
     */
    private List<String> partOf = new ArrayList<>();

    /**
     * The source identifiers indicating the origin of this type.
     *
     * @return The source identifiers indicating the origin of this type
     * @param source The source identifiers indicating the origin of this type
     */
    private List<String> source = new ArrayList<>();

    /**
     * The contributor identifiers for this type.
     *
     * @return The contributor identifiers for this type
     * @param contributor The contributor identifiers for this type
     */
    private List<String> contributor = new ArrayList<>();

    /**
     * Cached flag indicating whether this is an enumeration type.
     *
     * @return Cached flag indicating whether this is an enumeration type
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean enumerationType;

    /**
     * The list of enumeration member values (if this is an enumeration type).
     *
     * @return The list of enumeration member values (if this is an enumeration type)
     */
    @Setter(AccessLevel.NONE)
    private final List<String> enumerationMembers = new ArrayList<>();

    /**
     * Returns the type identifier without the schema prefix.
     *
     * @return the type ID (e.g., "Person" for "schema:Person")
     */
    public String getTypeId() {
        return id.substring(id.indexOf(':') + 1);
    }

    /**
     * Returns the description split into lines for javadoc formatting.
     *
     * @return an array of description lines
     */
    public String[] getSplitDescription() {
        return ModelUtils.getSplitDescription(description);
    }

    /**
     * Returns all properties including those inherited from parent types.
     * <p>
     * This method computes and caches the result on first access.
     *
     * @return a set containing all properties (direct and inherited)
     */
    public Set<Property> getAllProperties() {
        if (this.allProperties == null) {
            this.allProperties = Stream.concat(
                            properties.stream(),
                            getParents()
                                    .stream()
                                    .flatMap(type -> type.getAllProperties().stream()).filter(prop -> !properties.contains(prop))
                    )
                    .collect(Collectors.toCollection(() -> new TreeSet<>(PROPERTY_COMPARATOR)));
        }
        return this.allProperties;
    }

    /**
     * Adds a property to this type.
     *
     * @param property the property to add
     * @return this type for method chaining
     */
    public Type addProperty(Property property) {
        this.properties.add(property);
        return this;
    }


    /**
     * Returns the properties of this type, optionally filtered by mode and property IDs.
     *
     * @param mode the filter mode (INCLUDE or EXCLUDE)
     * @param propertyIds the list of property IDs to filter
     */
    public void filterProperties(GeneratorOptions.FilterMode mode, List<String> propertyIds) {
        if (propertyIds == null || propertyIds.isEmpty()) {
            return;
        }

        final Set<Pattern> normalizedPropertyIds = propertyIds.stream()
                .map(propertyId -> {
                    String typeName = propertyId.contains("*") ? propertyId : SchemaConstants.typeName(propertyId);
                    String regex = Pattern.quote(typeName).replace("*", "\\E.*\\Q");
                    return Pattern.compile(regex);
                })
                .collect(Collectors.toSet());

        if (mode == GeneratorOptions.FilterMode.EXCLUDE) {
            this.properties.removeIf(property -> normalizedPropertyIds.stream().anyMatch(propertyId -> propertyId.matcher(property.getId()).matches()));
        } else if (mode == GeneratorOptions.FilterMode.INCLUDE) {
            this.properties.removeIf(property -> normalizedPropertyIds.stream().noneMatch(propertyId -> propertyId.matcher(property.getId()).matches()));
        }

        this.allProperties = null;
    }

    /**
     * Adds a parent type to this type's inheritance hierarchy.
     * <p>
     * This also registers this type as a subtype of the parent.
     *
     * @param parent the parent type to add
     * @return this type for method chaining
     */
    public Type addParent(Type parent) {
        this.parents.add(parent);
        parent.addSubType(this);
        return this;
    }

    /**
     * Adds a subtype to this type (called internally by addParent).
     *
     * @param subType the subtype to add
     * @return this type for method chaining
     */
    private Type addSubType(Type subType) {
        this.subTypes.add(subType);
        return this;
    }

    /**
     * Checks whether this type is an enumeration type.
     * <p>
     * A type is considered an enumeration if it or any of its parent types
     * is {@code schema:Enumeration}.
     *
     * @return {@code true} if this is an enumeration type, {@code false} otherwise
     */
    public boolean isEnumerationType() {
        if (this.enumerationType == null) {
            this.enumerationType = isEnumerationType(getParents());
        }
        return this.enumerationType;
    }

    /**
     * Recursively checks if any parent is an enumeration type.
     *
     * @param parents the list of parent types to check
     * @return {@code true} if any parent is an enumeration, {@code false} otherwise
     */
    private static boolean isEnumerationType(List<Type> parents) {
        for (Type parent : parents) {
            if (SchemaConstants.SCHEMA_ENUMERATION.equals(parent.getId()) || isEnumerationType(parent.getParents())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an enumeration member value to this type.
     *
     * @param value the enumeration member value to add
     * @return this type for method chaining
     */
    public Type addEnumerationMember(String value) {
        this.enumerationMembers.add(value);
        return this;
    }

    /**
     * Returns a truncated description for toString() (max 50 chars).
     *
     * @return the truncated description, or null if not available
     */
    @ToString.Include
    private String description() {
        return Optional.ofNullable(description).map(s -> s.substring(0, Math.min(50, s.length()))).orElse(null);
    }

    /**
     * Returns a formatted string of parent type IDs for toString().
     *
     * @return a comma-separated list of parent IDs in brackets
     */
    @ToString.Include
    private String parents() {
        return "[" + parents.stream().filter(Objects::nonNull).map(Type::getId).collect(Collectors.joining(", ")) + "]";
    }

    /**
     * Returns a formatted string of subtype IDs for toString().
     *
     * @return a comma-separated list of subtype IDs in brackets
     */
    @ToString.Include
    private String subTypes() {
        return "[" + subTypes.stream().filter(Objects::nonNull).map(Type::getId).collect(Collectors.joining(", ")) + "]";
    }

    /**
     * Returns a detailed formatted string representation of this type.
     * <p>
     * Includes all type metadata: name, description, parents, subtypes,
     * properties, enumeration members, etc.
     *
     * @return a multi-line formatted string with all type details
     */
    public String toFormattedString() {
        return "---------- " + id + " ----------\n" +
                "name          = " + name + "\n" +
                "usedJavaType  = " + usedJavaType + "\n" +
                "description   = " + description() + "\n" +
                "parents       = " + parents() + "\n" +
                "subTypes      = " + subTypes() + "\n" +
                "partOf        = " + String.join(", ", partOf) + "\n" +
                "source        = " + String.join(", ", source) + "\n" +
                "contributor   = " + String.join(", ", contributor) + "\n" +
                "properties    = " + toFormattedString(properties) + "\n" +
                "enum members  = " + String.join(", ", enumerationMembers) + "\n";
    }

    /**
     * Formats a collection of properties as an indented list.
     *
     * @param properties the properties to format
     * @return a formatted string representation of the properties
     */
    private static String toFormattedString(Collection<Property> properties) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (properties != null && !properties.isEmpty()) {
            sb.append("\n");
            properties.forEach(property -> sb.append("    ").append(property.toFormattedString()).append("\n"));
        }
        sb.append("]");
        return sb.toString();
    }
}
