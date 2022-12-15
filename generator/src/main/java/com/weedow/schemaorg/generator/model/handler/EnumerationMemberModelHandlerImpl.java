package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;

import java.util.List;
import java.util.Map;

public class EnumerationMemberModelHandlerImpl implements ModelHandler {

    @Override
    public boolean supports(GraphItem graphItem) {
        final List<String> types = graphItem.getTypes();
        return types.size() == 1
                && types.get(0).startsWith("schema:")
                && !types.get(0).equals("schema:DataType");
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        final String enumerationTypeId = graphItem.getTypes().get(0);
        final Type type = ModelUtils.getType(schemaDefinitions, enumerationTypeId);

        type.addEnumerationMember(graphItem.getLabel().getValue());
    }
}
