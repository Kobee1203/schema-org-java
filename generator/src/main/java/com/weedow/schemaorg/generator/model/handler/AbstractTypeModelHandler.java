package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.SubClassOf;

import java.util.List;
import java.util.Map;

public abstract class AbstractTypeModelHandler implements ModelHandler {

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        final String typeId = ModelUtils.getTypeId(graphItem.getId());
        final Type type = ModelUtils.getType(schemaDefinitions, typeId);
        final String typeName = graphItem.getLabel().getValue();
        type
                .setName(typeName.equals("3DModel") ? "ThreeDimensionalModel" : typeName)
                .setDescription(graphItem.getComment().getValue())
                .setPartOf(ModelUtils.getPartOf(graphItem))
                .setSource(ModelUtils.getSource(graphItem));

        final List<SubClassOf> subClassOf = graphItem.getSubClassOf();
        if (subClassOf != null) {
            subClassOf
                    .stream()
                    .map(SubClassOf::getId)
                    .filter(id -> !"rdfs:Class".equals(id))
                    .forEach(id -> type.addParent(ModelUtils.getType(schemaDefinitions, ModelUtils.getTypeId(id))));
        }
    }

    protected static Type getType(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        final String typeId = ModelUtils.getTypeId(graphItem.getId());
        return ModelUtils.getType(schemaDefinitions, typeId);
    }
}
