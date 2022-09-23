package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;

import java.util.List;
import java.util.Map;

public class DataTypeModelHandlerImpl extends ClassModelHandlerImpl {

    @Override
    public boolean supports(GraphItem graphItem) {
        final List<String> types = graphItem.getTypes();
        return types.contains("rdfs:Class") && types.contains("schema:DataType");
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        super.handle(schemaDefinitions, graphItem);

        final String typeId = ModelHandlerUtils.getTypeId(graphItem.getId());
        final Type type = ModelHandlerUtils.getType(schemaDefinitions, typeId);

        type.setJavaType(ModelHandlerUtils.getJavaType(typeId, null));
        type.addParent(ModelHandlerUtils.getType(schemaDefinitions, ModelHandlerUtils.getTypeId("schema:DataType")));
    }
}
