package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.generator.model.BaseType;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.SubClassOf;

import java.util.List;
import java.util.Map;

public class ClassModelHandlerImpl extends AbstractTypeModelHandler {

    private static final BaseType BASE_TYPE = new BaseType("java:JsonLdNode", JsonLdNode.class, JsonLdNodeImpl.class);

    @Override
    public boolean supports(GraphItem graphItem) {
        final String id = graphItem.getId();
        final List<String> types = graphItem.getTypes();
        final List<SubClassOf> subClassOf = graphItem.getSubClassOf();
        return !"schema:DataType".equals(id)
                && types.contains("rdfs:Class") && !types.contains("schema:DataType")
                && (subClassOf == null || subClassOf.stream().noneMatch(subClass -> ModelHandlerUtils.isDataType(subClass.getId())));
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        super.handle(schemaDefinitions, graphItem);

        final Type type = getType(schemaDefinitions, graphItem);
        type.setBaseParent(BASE_TYPE);
    }
}
