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
import com.weedow.schemaorg.generator.parser.ParserOptions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PropertyModelHandlerImpl implements ModelHandler {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyModelHandlerImpl.class);

    @Override
    public boolean supports(GraphItem graphItem, ParserOptions options) {
        return graphItem.getTypes().contains("rdf:Property");
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem, ParserOptions options) {
        List<Type> propertyTypes = deduplicateJavaTypes(ModelUtils.getPropertyTypes(schemaDefinitions, graphItem), options);
        if (propertyTypes.isEmpty()) {
            LOG.info("** DEPRECATED ** superseded by {}", graphItem.getSupersededBy().getId());
            return;
        }

        final String name = graphItem.getLabel().getValue();
        final String description = graphItem.getComment().getValue();
        final List<String> partOf = ModelUtils.getPartOf(graphItem);
        final List<String> source = ModelUtils.getSource(graphItem);

        final Field field = new Field(
                name,
                propertyTypes
        );

        final Accessor accessor = new Accessor(
                name,
                description,
                partOf,
                source,
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
                                type::getName,
                                field::getFieldName
                        ))
                .toList();

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

    private List<Type> deduplicateJavaTypes(List<Type> propertyTypes, ParserOptions options) {
        if(!options.isUsedJavaTypes()) {
            return propertyTypes;
        }

        Map<String, Type> usedJavaTypeMap = new LinkedHashMap<>();

        List<Type> uniquePropertyTypes = new ArrayList<>();

        for (Type type : propertyTypes) {
            if (type.isUsedJavaType()) {
                usedJavaTypeMap.putIfAbsent(type.getName(), type);
            } else {
                uniquePropertyTypes.add(type);
            }
        }

        uniquePropertyTypes.addAll(usedJavaTypeMap.values());

        return uniquePropertyTypes;
    }
}
