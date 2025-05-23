package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.model.BaseType;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.SubClassOf;
import com.weedow.schemaorg.generator.parser.ParserOptions;

import java.util.List;
import java.util.Map;

public class ClassModelHandlerImpl extends AbstractTypeModelHandler {

    private static final BaseType BASE_TYPE = new BaseType("java:JsonLdNode", JsonLdNode.class, JsonLdNodeImpl.class);

    @Override
    public boolean supports(GraphItem graphItem, ParserOptions options) {
        final String id = graphItem.getId();
        final List<String> types = graphItem.getTypes();
        final List<SubClassOf> subClassOf = graphItem.getSubClassOf();
        return !SchemaConstants.SCHEMA_DATA_TYPE.equals(id)
                && types.contains(SchemaConstants.RDFS_CLASS) && !types.contains(SchemaConstants.SCHEMA_DATA_TYPE)
                && (subClassOf == null || subClassOf.stream().noneMatch(subClass -> ModelUtils.isDataType(subClass.getId())));
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem, ParserOptions options) {
        super.handle(schemaDefinitions, graphItem, options);

        final Type type = getType(schemaDefinitions, graphItem);
        type.setBaseParent(BASE_TYPE);
    }
}
