package com.weedow.schemaorg.generator.model.handler.datatype;

import com.weedow.schemaorg.commons.model.JsonLdDataType;
import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.model.BaseType;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.handler.AbstractTypeModelHandler;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.parser.ParserOptions;

import java.util.Map;

/**
 * Model handler for the base Schema.org DataType type.
 * Sets up the base parent type for DataType when not using Java types.
 */
public class BaseDataTypeModelHandlerImpl extends AbstractTypeModelHandler {

    /** Default constructor */
    public BaseDataTypeModelHandlerImpl() {
        // empty
    }

    private static final BaseType BASE_TYPE = new BaseType("java:JsonLdDataType", JsonLdDataType.class, null);

    @Override
    public boolean supports(GraphItem graphItem, ParserOptions options) {
        return SchemaConstants.SCHEMA_DATA_TYPE.equals(graphItem.getId()) && !options.isUsedJavaTypes();
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem, ParserOptions options) {
        super.handle(schemaDefinitions, graphItem, options);

        final Type type = getType(schemaDefinitions, graphItem);
        type.setBaseParent(BASE_TYPE);
    }
}
