package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("rdfs:Class")
public class ClassItem extends GraphItem {

    @Override
    public String toString() {
        return toString("ClassItem");
    }
}
