package com.weedow.schemaorg.generator.model.field;

import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import lombok.Value;

import java.util.List;
import java.util.function.Supplier;

/**
 * Represents a mutator (setter/adder) for a Schema.org property field.
 */
@Value
public class Mutator {

    /**
     * The property name.
     *
     * @return The property name
     */
    String name;
    /**
     * The property description.
     *
     * @return The property description
     */
    String description;
    /**
     * The partOf identifiers indicating which types this property is part of.
     *
     * @return The partOf identifiers indicating which types this property is part of
     */
    List<String> partOf;
    /**
     * The source identifiers indicating the origin of this property.
     *
     * @return The source identifiers indicating the origin of this property
     */
    List<String> source;
    /**
     * The contributor identifiers for this property.
     *
     * @return The contributor identifiers for this property
     */
    List<String> contributor;
    /**
     * Supplier for the parameter type.
     *
     * @return Supplier for the parameter type
     */
    Supplier<String> paramType;
    /**
     * Supplier for the parameter value.
     *
     * @return Supplier for the parameter value
     */
    Supplier<String> paramValue;

    /**
     * The field name derived from the property name.
     *
     * @return The field name derived from the property name
     */
    String fieldName;
    /**
     * The setter method name.
     *
     * @return The setter method name
     */
    String setterMethod;
    /**
     * The adder method name for collection properties.
     *
     * @return The adder method name for collection properties
     */
    String addMethod;
    /**
     * The description split into lines for javadoc formatting.
     *
     * @return The description split into lines for javadoc formatting
     */
    String[] splitDescription;

    /**
     * Creates a new mutator with the specified properties.
     *
     * @param name        the property name
     * @param description the property description
     * @param partOf      the partOf identifiers
     * @param source      the source identifiers
     * @param contributor the contributor identifiers
     * @param paramType   the parameter type supplier
     * @param paramValue  the parameter value supplier
     */
    public Mutator(
            String name,
            String description,
            List<String> partOf,
            List<String> source,
            List<String> contributor,
            Supplier<String> paramType,
            Supplier<String> paramValue
    ) {
        this.name = name;
        this.description = description;
        this.partOf = partOf;
        this.source = source;
        this.contributor = contributor;
        this.paramType = paramType;
        this.paramValue = paramValue;

        this.fieldName = ModelUtils.getFieldName(name);
        this.setterMethod = ModelUtils.getMethodName(name, "set", "");
        this.addMethod = ModelUtils.getMethodName(name, "add", "");
        this.splitDescription = ModelUtils.getSplitDescription(description);
    }

    /**
     * Returns the parameter type from the supplier.
     *
     * @return the parameter type, or {@code null} if not available
     */
    public String getParamType() {
        return paramType != null ? paramType.get() : null;
    }

    /**
     * Returns the parameter value from the supplier.
     *
     * @return the parameter value, or {@code null} if not available
     */
    public String getParamValue() {
        return paramValue != null ? paramValue.get() : null;
    }
}
