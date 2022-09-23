package com.weedow.schemaorg.generator.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Type {

    private final String id;

    private String javaType;

    private String name;

    private String description;

    private final Set<Property> properties = new LinkedHashSet<>();
    private Set<Property> allProperties;

    private final List<Type> parents = new ArrayList<>();

    private Boolean enumerationType;
    private final List<String> enumerationMembers = new ArrayList<>();

    private List<String> partOf = new ArrayList<>();

    private List<String> source = new ArrayList<>();

    public Type(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getJavaType() {
        return javaType;
    }

    public Type setJavaType(String javaType) {
        this.javaType = javaType;
        return this;
    }

    public String getName() {
        return name;
    }

    public Type setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public String[] getSplitDescription() {
        return description != null ? description.replace("\\n", "<br/>").split("\\n") : null;
    }

    public Type setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public Set<Property> getAllProperties() {
        if (this.allProperties == null) {
            this.allProperties = Stream.concat(
                            properties.stream(),
                            getParents()
                                    .stream()
                                    .flatMap(type -> type.getAllProperties().stream()).filter(prop -> !properties.contains(prop))
                    )
                    .collect(Collectors.toSet());
        }
        return this.allProperties;
    }

    public Type addProperty(Property property) {
        this.properties.add(property);
        return this;
    }

    public List<Type> getParents() {
        return parents;
    }

    public Type addParent(Type parent) {
        this.parents.add(parent);
        return this;
    }

    public boolean isEnumerationType() {
        if(this.enumerationType == null) {
            this.enumerationType = isEnumerationType(getParents());
        }
        return this.enumerationType;
    }

    private static boolean isEnumerationType(List<Type> parents) {
        for (Type parent : parents) {
            if("schema:Enumeration".equals(parent.getId()) || isEnumerationType(parent.getParents())) {
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

    public List<String> getPartOf() {
        return partOf;
    }

    public Type setPartOf(List<String> partOf) {
        this.partOf = partOf;
        return this;
    }

    public List<String> getSource() {
        return source;
    }

    public Type setSource(List<String> source) {
        this.source = source;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(id, type.id)
                && Objects.equals(name, type.name)
                && Objects.equals(description, type.description)
                && Objects.equals(properties, type.properties)
                && Objects.equals(parents, type.parents)
                && Objects.equals(partOf, type.partOf)
                && Objects.equals(source, type.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, properties, parents, partOf, source);
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
