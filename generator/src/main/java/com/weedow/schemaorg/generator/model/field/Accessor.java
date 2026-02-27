package com.weedow.schemaorg.generator.model.field;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import lombok.NonNull;
import lombok.Value;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.weedow.schemaorg.generator.model.field.FieldUtils.supplier;

/**
 * Represents an accessor (getter) method for a Schema.org property field.
 * Provides lazy-evaluated field type information and method naming.
 */
@Value
public class Accessor {

    /** The property name. */
    String name;
    /** The property description. */
    String description;
    /** The partOf identifiers indicating which types this property is part of. */
    List<String> partOf;
    /** The source identifiers indicating the origin of this property. */
    List<String> source;
    /** The contributor identifiers for this property. */
    List<String> contributor;
    /** Supplier for the field type as Javadoc links. */
    @NonNull
    Supplier<String> fieldTypeLinks;
    /** Supplier for the return field type (single value). */
    @NonNull
    Supplier<String> returnFieldType;
    /** Supplier for the return field type as a list. */
    @NonNull
    Supplier<String> returnFieldTypeAsList;
    /** Supplier for the cast expression for single value. */
    @NonNull
    Supplier<String> cast;
    /** Supplier for the cast expression for list value. */
    @NonNull
    Supplier<String> castAsList;

    /** The field name derived from the property name. */
    String fieldName;
    /** The getter method name for list values. */
    String getterMethod;
    /** The getter method name for the first value. */
    String firstGetterMethod;
    /** The description split into lines for javadoc formatting. */
    String[] splitDescription;

    /**
     * Creates a new Accessor with the specified property information.
     *
     * @param name the property name
     * @param description the property description
     * @param partOf list of specification parts this property belongs to
     * @param source list of sources for this property
     * @param contributor list of contributors for this property
     * @param types list of types this property can have
     */
    public Accessor(
            String name,
            String description,
            List<String> partOf,
            List<String> source,
            List<String> contributor,
            List<Type> types
    ) {
        this.name = name;
        this.description = description;
        this.partOf = partOf;
        this.source = source;
        this.contributor = contributor;
        this.fieldTypeLinks = supplier(types, t -> t.stream().map(type -> "{@link " + type.getName() + "}").collect(Collectors.joining(" or ")));
        this.returnFieldType = supplier(types, t -> t.size() > 1 ? "<T> T" : t.get(0).getName());
        this.returnFieldTypeAsList = supplier(types, t -> t.size() > 1 ? "<T> List<T>" : "List<" + t.get(0).getName() + ">");
        this.cast = supplier(types, t -> t.size() > 1 ? "(T)" : null);
        this.castAsList = supplier(types, t -> t.size() > 1 ? "(List<T>)" : null);

        this.fieldName = ModelUtils.getFieldName(name);
        this.getterMethod = ModelUtils.getMethodName(name, "get", "List");
        this.firstGetterMethod = ModelUtils.getMethodName(name, "get", "");
        this.splitDescription = ModelUtils.getSplitDescription(description);
    }

    /**
     * Gets the field type as Javadoc links.
     *
     * @return string containing link references to the field types
     */
    public String getFieldTypeLinks() {
        return fieldTypeLinks.get();
    }

    /**
     * Gets the return field type for single value getter.
     *
     * @return the field type name, or generic type for multiple types
     */
    public String getReturnFieldType() {
        return returnFieldType.get();
    }

    /**
     * Gets the return field type for list getter.
     *
     * @return the field type as a list, or generic list type for multiple types
     */
    public String getReturnFieldTypeAsList() {
        return returnFieldTypeAsList.get();
    }

    /**
     * Gets the cast expression for single value getter.
     *
     * @return the cast expression, or null if no cast needed
     */
    public String getCast() {
        return cast.get();
    }

    /**
     * Gets the cast expression for list getter.
     *
     * @return the cast expression for list, or null if no cast needed
     */
    public String getCastAsList() {
        return castAsList.get();
    }
}
