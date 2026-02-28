package com.weedow.schemaorg.generator.model.field;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import lombok.NonNull;
import lombok.Value;

import java.util.List;
import java.util.function.Supplier;

import static com.weedow.schemaorg.generator.model.field.FieldUtils.supplier;

/**
 * Represents a field definition for a Schema.org property.
 * Provides lazy-evaluated field type information.
 */
@Value
public class Field {

    /**
     * The property name.
     *
     * @return The property name
     */
    String name;

    /**
     * The field name derived from the property name.
     *
     * @return The field name derived from the property name
     */
    String fieldName;
    /**
     * Supplier for the field type.
     *
     * @return Supplier for the field type
     */
    @NonNull
    Supplier<String> fieldType;
    /**
     * Supplier for the field type as a list.
     *
     * @return Supplier for the field type as a list
     */
    @NonNull
    Supplier<String> fieldTypeAsList;

    /**
     * Creates a new Field with the specified name and types.
     *
     * @param name the property name
     * @param types list of types this field can have
     */
    public Field(String name, List<Type> types) {
        this.name = name;

        this.fieldName = ModelUtils.getFieldName(name);
        this.fieldType = supplier(types, t -> t.size() > 1 ? "Object" : t.get(0).getName());
        this.fieldTypeAsList = supplier(types, t -> t.size() > 1 ? "List<Object>" : "List<" + t.get(0).getName() + ">");
    }

    /**
     * Gets the field type name.
     *
     * @return the field type, or Object for multiple types
     */
    public String getFieldType() {
        return fieldType.get();
    }

    /**
     * Gets the field type as a list type.
     *
     * @return the field type as a list, or List&lt;Object&gt; for multiple types
     */
    public String getFieldTypeAsList() {
        return fieldTypeAsList.get();
    }
}
