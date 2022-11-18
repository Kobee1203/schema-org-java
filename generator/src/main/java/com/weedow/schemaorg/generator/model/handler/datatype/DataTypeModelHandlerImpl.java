package com.weedow.schemaorg.generator.model.handler.datatype;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.handler.AbstractTypeModelHandler;
import com.weedow.schemaorg.generator.model.handler.ModelHandlerUtils;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;

import java.util.List;
import java.util.Map;

public class DataTypeModelHandlerImpl extends AbstractTypeModelHandler {

    @Override
    public boolean supports(GraphItem graphItem) {
        final List<String> types = graphItem.getTypes();
        return types.contains("rdfs:Class") && types.contains("schema:DataType");
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        super.handle(schemaDefinitions, graphItem);

        final Type type = getType(schemaDefinitions, graphItem);

        type.setJavaType(ModelHandlerUtils.getJavaType(type.getId(), null));
        type.addParent(ModelHandlerUtils.getType(schemaDefinitions, ModelHandlerUtils.getTypeId("schema:DataType")));
    }
}
