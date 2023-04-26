package com.weedow.schemaorg.generator.model.field;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import lombok.NonNull;
import lombok.Value;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.weedow.schemaorg.generator.model.field.FieldUtils.supplier;

@Value
public class Accessor {

    String name;
    String description;
    List<String> partOf;
    List<String> source;
    List<String> subPropertyOf;
    @NonNull
    Supplier<String> fieldTypeLinks;
    @NonNull
    Supplier<String> returnFieldType;
    @NonNull
    Supplier<String> returnFieldTypeAsList;
    @NonNull
    Supplier<String> cast;
    @NonNull
    Supplier<String> castAsList;

    String fieldName;
    String getterMethod;
    String firstGetterMethod;
    String[] splitDescription;

    public Accessor(
            String name,
            String description,
            List<String> partOf,
            List<String> source,
            List<String> subPropertyOf,
            List<Type> types
    ) {
        this.name = name;
        this.description = description;
        this.partOf = partOf;
        this.source = source;
        this.subPropertyOf = subPropertyOf;
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

    public String getFieldTypeLinks() {
        return fieldTypeLinks.get();
    }

    public String getReturnFieldType() {
        return returnFieldType.get();
    }

    public String getReturnFieldTypeAsList() {
        return returnFieldTypeAsList.get();
    }

    public String getCast() {
        return cast.get();
    }

    public String getCastAsList() {
        return castAsList.get();
    }
}
