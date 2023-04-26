package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;
import com.weedow.schemaorg.generator.model.Property;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.field.Accessor;
import com.weedow.schemaorg.generator.model.field.Field;
import com.weedow.schemaorg.generator.model.field.Mutator;
import com.weedow.schemaorg.generator.model.jsonld.DomainIncludes;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.weedow.schemaorg.generator.SchemaModelGeneratorConstants.SCHEMA_PREFIX;

public class PropertyModelHandlerImpl implements ModelHandler {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyModelHandlerImpl.class);

    @Override
    public boolean supports(GraphItem graphItem) {
        return graphItem.getTypes().contains("rdf:Property");
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        final List<Type> propertyTypes = ModelUtils.getPropertyTypes(schemaDefinitions, graphItem);
        if (propertyTypes.isEmpty()) {
            LOG.info("** DEPRECATED ** superseded by {}", graphItem.getSupersededBy().getId());
            return;
        }

        final String name = graphItem.getLabel().getValue();
        final String description = graphItem.getComment().getValue();
        final List<String> partOf = ModelUtils.getPartOf(graphItem);
        final List<String> source = ModelUtils.getSource(graphItem);
        // TODO Improve the handling of "subPropertyOf".
        // Use the property label present in the schema definition rather than the substring after 'schema:'
        final List<String> subPropertyOf = ModelUtils.getSubPropertyOf(graphItem)
                .stream()
                .map(id -> {
                    final int pos = id.indexOf(SCHEMA_PREFIX);
                    if (pos == -1) {
                        return id;
                    }
                    return id.substring(pos + SCHEMA_PREFIX.length());
                })
                .collect(Collectors.toList());

        final Field field = new Field(
                name,
                propertyTypes
        );

        final Accessor accessor = new Accessor(
                name,
                description,
                partOf,
                source,
                subPropertyOf,
                propertyTypes
        );

        List<Mutator> mutators = propertyTypes
                .stream()
                .map(type ->
                        new Mutator(
                                name,
                                description,
                                partOf,
                                source,
                                subPropertyOf,
                                type::getName,
                                field::getFieldName
                        ))
                .collect(Collectors.toList());

        final Property property = new Property(
                graphItem.getId(),
                field,
                accessor,
                mutators,
                propertyTypes
        );

        final List<DomainIncludes> domainIncludes = graphItem.getDomainIncludes();
        domainIncludes.stream().map(DomainIncludes::getId).forEach(id ->
                ModelUtils.getType(schemaDefinitions, id).addProperty(property)
        );
    }
}
