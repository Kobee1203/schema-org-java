package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("schema:DataType")
public class DataTypeItem extends ClassItem {

    @Override
    public String toString() {
        return toString("DataTypeItem");
    }
}
