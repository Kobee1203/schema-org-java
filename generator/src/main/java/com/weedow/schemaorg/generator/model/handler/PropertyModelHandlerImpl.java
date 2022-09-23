package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Property;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.DomainIncludes;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class PropertyModelHandlerImpl implements ModelHandler {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyModelHandlerImpl.class);

    @Override
    public boolean supports(GraphItem graphItem) {
        return graphItem.getTypes().contains("rdf:Property");
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem) {
        final List<Type> propertyTypes = ModelHandlerUtils.getPropertyTypes(schemaDefinitions, graphItem);
        if (propertyTypes.isEmpty()) {
            LOG.info("** DEPRECATED ** superseded by {}", graphItem.getSupersededBy().getId());
            return;
        }

        final List<String> partOf = ModelHandlerUtils.getPartOf(graphItem);

        final List<String> source = ModelHandlerUtils.getSource(graphItem);

        final String name = graphItem.getLabel().getValue();
        final Property property = new Property(
                graphItem.getId(),
                name,
                "f" + ModelHandlerUtils.capitalize(name),
                graphItem.getComment().getValue(),
                propertyTypes,
                partOf,
                source
        );

        final List<DomainIncludes> domainIncludes = graphItem.getDomainIncludes();
        domainIncludes.stream().map(DomainIncludes::getId).forEach(id ->
                ModelHandlerUtils.getType(schemaDefinitions, id).addProperty(property)
        );
    }
}
