package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;

import java.util.List;
import java.util.Map;

import static com.weedow.schemaorg.generator.SchemaModelGeneratorConstants.SCHEMA_PREFIX;

public class EnumerationMemberModelHandlerImpl implements ModelHandler {

    @Override
    public boolean supports(GraphItem graphItem) {
        final List<String> types = graphItem.getTypes();
        return types.stream().allMatch(s -> s.startsWith(SCHEMA_PREFIX)) && !types.contains("schema:DataType");
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        graphItem.getTypes().forEach(enumerationTypeId -> {
            final Type type = ModelUtils.getType(schemaDefinitions, enumerationTypeId);
            type.addEnumerationMember(graphItem.getLabel().getValue());
        });
    }
}
