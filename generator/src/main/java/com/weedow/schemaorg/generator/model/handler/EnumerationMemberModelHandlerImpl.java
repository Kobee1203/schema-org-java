package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Model handler for Schema.org enumeration member items.
 * Associates enumeration values with their parent enumeration types.
 */
@NoArgsConstructor
public class EnumerationMemberModelHandlerImpl implements ModelHandler {

    @Override
    public boolean supports(GraphItem graphItem, ParserOptions options) {
        final String id = graphItem.getId();
        final List<String> types = graphItem.getTypes();
        return id.startsWith(SchemaConstants.SCHEMA_PREFIX)
                && types.stream().allMatch(s -> s.startsWith(SchemaConstants.SCHEMA_PREFIX)) && !types.contains(SchemaConstants.SCHEMA_DATA_TYPE);
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem, ParserOptions options) {
        graphItem.getTypes().forEach(enumerationTypeId -> {
            final Type type = ModelUtils.getType(schemaDefinitions, enumerationTypeId);
            type.addEnumerationMember(graphItem.getLabel().getValue());
        });
    }
}
