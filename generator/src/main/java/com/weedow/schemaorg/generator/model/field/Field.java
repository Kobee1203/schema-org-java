package com.weedow.schemaorg.generator.model.field;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import lombok.Value;

import java.util.List;
import java.util.function.Supplier;

import static com.weedow.schemaorg.generator.model.field.FieldUtils.supplier;

@Value
public class Field {

    String name;

    String fieldName;
    Supplier<String> fieldType;
    Supplier<String> fieldTypeAsList;

    public Field(String name, List<Type> types) {
        this.name = name;

        this.fieldName = ModelUtils.getFieldName(name);
        this.fieldType = supplier(types, t -> t.size() > 1 ? "Object" : t.get(0).getName());
        this.fieldTypeAsList = supplier(types, t -> t.size() > 1 ? "List<Object>" : "List<" + t.get(0).getName() + ">");
    }

    public String getFieldType() {
        return fieldType.get();
    }

    public String getFieldTypeAsList() {
        return fieldTypeAsList.get();
    }
}
