package com.weedow.schemaorg.generator.model.handler.datatype;

import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.handler.AbstractTypeModelHandler;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.parser.ParserOptions;

import java.util.List;
import java.util.Map;

public class DataTypeModelHandlerImpl extends AbstractTypeModelHandler {

    @Override
    public boolean supports(GraphItem graphItem, ParserOptions options) {
        final List<String> types = graphItem.getTypes();
        return types.contains(SchemaConstants.RDFS_CLASS) && types.contains(SchemaConstants.SCHEMA_DATA_TYPE);
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem, ParserOptions options) {
        super.handle(schemaDefinitions, graphItem, options);

        final Type type = getType(schemaDefinitions, graphItem);

        String javaType = ModelUtils.getJavaType(type.getId(), null);
        if(options.isUsedJavaTypes()) {
            type.setName(javaType);
            type.setUsedJavaType(true);
        } else {
            type.setJavaType(javaType);
            type.addParent(ModelUtils.getType(schemaDefinitions, ModelUtils.getTypeId(SchemaConstants.SCHEMA_DATA_TYPE)));
        }
    }
}
