package com.weedow.schemaorg.generator.model.handler.datatype;

import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.handler.AbstractTypeModelHandler;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.SubClassOf;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import com.weedow.schemaorg.generator.parser.ParserOptions;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Handler for Schema.org types that are subclasses of data types.
 * <p>
 * This handler processes classes that extend Schema.org data types (such as Text, Number, etc.)
 * but are not themselves marked as DataType. It:
 * <ul>
 *   <li>Maps the type to the appropriate Java type based on its parent data type</li>
 *   <li>Optionally uses Java primitive/standard types if configured</li>
 *   <li>Marks types as stringifiable if they extend String but have a different Java type</li>
 * </ul>
 *
 * <p>Example: A type like "URL" that extends "Text" would be processed by this handler.
 */
@NoArgsConstructor
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
