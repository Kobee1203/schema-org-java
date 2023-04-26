package com.weedow.schemaorg.generator.model.field;

import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import lombok.Value;

import java.util.List;
import java.util.function.Supplier;

@Value
public class Mutator {

    String name;
    String description;
    List<String> partOf;
    List<String> source;
    List<String> subPropertyOf;
    Supplier<String> paramType;
    Supplier<String> paramValue;

    String fieldName;
    String setterMethod;
    String addMethod;
    String[] splitDescription;

    public Mutator(
            String name,
            String description,
            List<String> partOf,
            List<String> source,
            List<String> subPropertyOf,
            Supplier<String> paramType,
            Supplier<String> paramValue
    ) {
        this.name = name;
        this.description = description;
        this.partOf = partOf;
        this.source = source;
        this.subPropertyOf = subPropertyOf;
        this.paramType = paramType;
        this.paramValue = paramValue;

        this.fieldName = ModelUtils.getFieldName(name);
        this.setterMethod = ModelUtils.getMethodName(name, "set", "");
        this.addMethod = ModelUtils.getMethodName(name, "add", "");
        this.splitDescription = ModelUtils.getSplitDescription(description);
    }

    public String getParamType() {
        return paramType != null ? paramType.get() : null;
    }

    public String getParamValue() {
        return paramValue != null ? paramValue.get() : null;
    }
}
