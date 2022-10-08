package com.weedow.schemaorg.generator.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Accessors(chain = true)
public final class Type {

    private final String id;

    private String javaType;

    private String name;

    private String description;

    @Setter(AccessLevel.NONE)
    private final Set<Property> properties = new LinkedHashSet<>();

    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Set<Property> allProperties;

    @Setter(AccessLevel.NONE)
    private final List<Type> parents = new ArrayList<>();

    private List<String> partOf = new ArrayList<>();

    private List<String> source = new ArrayList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean enumerationType;

    @Setter(AccessLevel.NONE)
    private final List<String> enumerationMembers = new ArrayList<>();

    public String[] getSplitDescription() {
        return description != null ? description.replace("\\n", "<br/>").split("\\n") : null;
    }

    public Set<Property> getAllProperties() {
        if (this.allProperties == null) {
            this.allProperties = Stream.concat(
                            properties.stream(),
                            getParents()
                                    .stream()
                                    .flatMap(type -> type.getAllProperties().stream()).filter(prop -> !properties.contains(prop))
                    )
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }
        return this.allProperties;
    }

    public Type addProperty(Property property) {
        this.properties.add(property);
        return this;
    }

    public Type addParent(Type parent) {
        this.parents.add(parent);
        return this;
    }

    public boolean isEnumerationType() {
        if (this.enumerationType == null) {
            this.enumerationType = isEnumerationType(getParents());
        }
        return this.enumerationType;
    }

    private static boolean isEnumerationType(List<Type> parents) {
        for (Type parent : parents) {
            if ("schema:Enumeration".equals(parent.getId()) || isEnumerationType(parent.getParents())) {
                return true;
            }
        }
        return false;
    }

    public List<String> getEnumerationMembers() {
        return enumerationMembers;
    }

    public Type addEnumerationMember(String value) {
        this.enumerationMembers.add(value);
        return this;
    }

    @Override
    public String toString() {
        return "---------- " + id + " ----------\n" +
                "name        = " + name + "\n" +
                "description = " + Optional.ofNullable(description).map(s -> s.substring(0, Math.min(50, s.length()))).orElse(null) + "\n" +
                "parents     = " + parents.stream().map(Type::getId).collect(Collectors.joining(", ")) + "\n" +
                "partOf      = " + String.join(", ", partOf) + "\n" +
                "source      = " + String.join(", ", source) + "\n" +
                "properties  = " + toString(properties) + "\n";
    }

    private static String toString(Collection<Property> properties) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (properties != null && !properties.isEmpty()) {
            sb.append("\n");
            properties.forEach(property -> sb.append("    ").append(property.toString()).append("\n"));
        }
        sb.append("]");
        return sb.toString();
    }
}
