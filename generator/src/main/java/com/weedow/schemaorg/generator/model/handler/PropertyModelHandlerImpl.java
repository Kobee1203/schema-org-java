package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;
import com.weedow.schemaorg.generator.model.utils.ModelUtils;
import com.weedow.schemaorg.generator.model.Property;
import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.DomainIncludes;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        final Property.Field field = new Property.Field(
                name,
                () -> propertyTypes.size() > 1 ? "Object" : propertyTypes.get(0).getName()
        );

        final Property.Accessor accessor = new Property.Accessor(
                name,
                description,
                partOf,
                source,
                () -> propertyTypes.stream().map(type -> "{@link " + type.getName() + "}").collect(Collectors.joining(" or ")),
                () -> propertyTypes.size() > 1 ? "<T> T" : propertyTypes.get(0).getName(),
                () -> propertyTypes.size() > 1 ? "(T)" : null
        );

        List<Property.Mutator> mutators = propertyTypes
                .stream()
                .flatMap(type -> {
                    List<Property.Mutator> list = new ArrayList<>();

                    list.add(new Property.Mutator(
                            name,
                            description,
                            partOf,
                            source,
                            type::getName,
                            field::getFieldName
                    ));

                    String javaType = ModelUtils.getJavaType(type.getId(), null);
                    if (javaType != null) {
                        list.add(new Property.Mutator(
                                name,
                                description,
                                partOf,
                                source,
                                () -> javaType,
                                () -> type.getName() + ".of(" + field.getFieldName() + ")"
                        ));
                    }

                    return list.stream();
                })
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
