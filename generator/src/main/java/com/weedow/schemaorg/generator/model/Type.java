package com.weedow.schemaorg.generator.model;

import com.weedow.schemaorg.generator.model.utils.ModelUtils;
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

    private boolean usedJavaType = false;

    private String name;

    private String description;

    @Setter(AccessLevel.NONE)
    private final Set<Property> properties = new LinkedHashSet<>();

    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Set<Property> allProperties;

    private BaseType baseParent;

    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private final List<Type> parents = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final List<Type> subTypes = new ArrayList<>();

    private List<String> partOf = new ArrayList<>();

    private List<String> source = new ArrayList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean enumerationType;

    @Setter(AccessLevel.NONE)
    private final List<String> enumerationMembers = new ArrayList<>();

    public String getTypeId() {
        return id.substring(id.indexOf(':') + 1);
    }

    public String[] getSplitDescription() {
        return ModelUtils.getSplitDescription(description);
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
        parent.addSubType(this);
        return this;
    }

    private Type addSubType(Type subType) {
        this.subTypes.add(subType);
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

    public Type addEnumerationMember(String value) {
        this.enumerationMembers.add(value);
        return this;
    }

    @ToString.Include
    private String description() {
        return Optional.ofNullable(description).map(s -> s.substring(0, Math.min(50, s.length()))).orElse(null);
    }

    @ToString.Include
    private String parents() {
        return "[" + parents.stream().filter(Objects::nonNull).map(Type::getId).collect(Collectors.joining(", ")) + "]";
    }

    @ToString.Include
    private String subTypes() {
        return "[" + subTypes.stream().filter(Objects::nonNull).map(Type::getId).collect(Collectors.joining(", ")) + "]";
    }

    public String toFormattedString() {
        return "---------- " + id + " ----------\n" +
                "name          = " + name + "\n" +
                "usedJavaType  = " + usedJavaType + "\n" +
                "description   = " + description() + "\n" +
                "parents       = " + parents() + "\n" +
                "subTypes      = " + subTypes() + "\n" +
                "partOf        = " + String.join(", ", partOf) + "\n" +
                "source        = " + String.join(", ", source) + "\n" +
                "properties    = " + toFormattedString(properties) + "\n" +
                "enum members  = " + String.join(", ", enumerationMembers) + "\n";
    }

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
