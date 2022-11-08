package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.SubClassOf;

import java.util.List;
import java.util.Map;

public abstract class AbstractTypeModelHandler implements ModelHandler {

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        final String typeId = ModelHandlerUtils.getTypeId(graphItem.getId());
        final Type type = ModelHandlerUtils.getType(schemaDefinitions, typeId);
        final String typeName = graphItem.getLabel().getValue();
        type
                .setName(typeName.equals("3DModel") ? "ThreeDimensionalModel" : typeName)
                .setDescription(graphItem.getComment().getValue())
                .setPartOf(ModelHandlerUtils.getPartOf(graphItem))
                .setSource(ModelHandlerUtils.getSource(graphItem));

        final List<SubClassOf> subClassOf = graphItem.getSubClassOf();
        if (subClassOf != null) {
            subClassOf
                    .stream()
                    .map(SubClassOf::getId)
                    .filter(id -> !"rdfs:Class".equals(id))
                    .forEach(id -> type.addParent(ModelHandlerUtils.getType(schemaDefinitions, ModelHandlerUtils.getTypeId(id))));
        }
    }

    protected static Type getType(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        final String typeId = ModelHandlerUtils.getTypeId(graphItem.getId());
        return ModelHandlerUtils.getType(schemaDefinitions, typeId);
    }
}
