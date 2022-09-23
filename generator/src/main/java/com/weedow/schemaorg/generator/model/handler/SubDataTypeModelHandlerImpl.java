package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.SubClassOf;

import java.util.List;
import java.util.Map;

public class SubDataTypeModelHandlerImpl extends ClassModelHandlerImpl {

    @Override
    public boolean supports(GraphItem graphItem) {
        final List<String> types = graphItem.getTypes();
        final List<SubClassOf> subClassOf = graphItem.getSubClassOf();
        return types.contains("rdfs:Class") && !types.contains("schema:DataType")
                && subClassOf != null && subClassOf.stream().anyMatch(subClass -> ModelHandlerUtils.isDataType(subClass.getId()));
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        super.handle(schemaDefinitions, graphItem);

        final String typeId = ModelHandlerUtils.getTypeId(graphItem.getId());
        final Type type = ModelHandlerUtils.getType(schemaDefinitions, typeId);

        final String parentJavaType = ModelHandlerUtils.getJavaType(type.getParents().get(0).getId(), null);
        type.setJavaType(ModelHandlerUtils.getJavaType(type.getId(), parentJavaType));
    }
}
