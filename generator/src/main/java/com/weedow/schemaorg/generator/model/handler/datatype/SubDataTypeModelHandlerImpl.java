package com.weedow.schemaorg.generator.model.handler.datatype;

import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.handler.AbstractTypeModelHandler;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.SubClassOf;
import com.weedow.schemaorg.generator.parser.ParserOptions;

import java.util.List;
import java.util.Map;

public class SubDataTypeModelHandlerImpl extends AbstractTypeModelHandler {

    @Override
    public boolean supports(GraphItem graphItem, ParserOptions options) {
        final List<String> types = graphItem.getTypes();
        final List<SubClassOf> subClassOf = graphItem.getSubClassOf();
        return types.contains(SchemaConstants.RDFS_CLASS) && !types.contains(SchemaConstants.SCHEMA_DATA_TYPE)
                && subClassOf != null && subClassOf.stream().anyMatch(subClass -> ModelUtils.isDataType(subClass.getId()));
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem, ParserOptions options) {
        super.handle(schemaDefinitions, graphItem, options);

        final Type type = getType(schemaDefinitions, graphItem);

        final String parentJavaType = !type.getParents().isEmpty() ? ModelUtils.getJavaType(type.getParents().get(0).getId(), options.getCustomDataTypes(), null) : null;
        final String javaType = ModelUtils.getJavaType(type.getId(), options.getCustomDataTypes(), parentJavaType);

        type.setJavaType(javaType);

        if (options.isUsedJavaTypes()) {
            type.setName(javaType);
            type.setUsedJavaType(true);
        }

        if(String.class.getName().equals(parentJavaType) && !parentJavaType.equals(javaType)) {
            type.setStringifiable(true);
        }
    }
}
