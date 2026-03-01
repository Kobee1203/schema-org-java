package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.jsonld.SubClassOf;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import com.weedow.schemaorg.generator.parser.ParserOptions;

import java.util.List;
import java.util.Map;

/**
 * Abstract base class for type model handlers that provides common handling logic for Schema.org type definitions.
 */
public abstract class AbstractTypeModelHandler implements ModelHandler {

    /** Default constructor */
    protected AbstractTypeModelHandler() {
        // empty
    }

    /**
     * Handles the processing of a Schema.org graph item into a type definition.
     *
     * @param schemaDefinitions map of type IDs to Type objects
     * @param graphItem         the graph item to process
     * @param options           parser options for configuration
     */
    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem, ParserOptions options) {
        final String typeId = ModelUtils.getTypeId(graphItem.getId());
        final Type type = ModelUtils.getType(schemaDefinitions, typeId);
        final String typeName = graphItem.getLabel().getValue();
        type
                .setName(typeName.equals("3DModel") ? "ThreeDimensionalModel" : typeName)
                .setDescription(graphItem.getComment().getValue())
                .setPartOf(ModelUtils.getPartOf(graphItem))
                .setSource(ModelUtils.getSource(graphItem))
                .setContributor(ModelUtils.getContributor(graphItem));

        final List<SubClassOf> subClassOf = graphItem.getSubClassOf();
        if (subClassOf != null) {
            subClassOf
                    .stream()
                    .map(SubClassOf::getId)
                    .filter(id -> id.startsWith(SchemaConstants.SCHEMA_PREFIX))
                    .forEach(id -> type.addParent(ModelUtils.getType(schemaDefinitions, ModelUtils.getTypeId(id))));
        }
    }

    /**
     * Retrieves the Type object for a given graph item.
     *
     * @param schemaDefinitions map of type IDs to Type objects
     * @param graphItem         the graph item whose type to retrieve
     * @return the Type object for the graph item
     */
    protected static Type getType(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        final String typeId = ModelUtils.getTypeId(graphItem.getId());
        return ModelUtils.getType(schemaDefinitions, typeId);
    }
}
