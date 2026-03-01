package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.SchemaConstants;
import com.weedow.schemaorg.generator.logging.LogMarkers;
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

import static com.weedow.schemaorg.generator.logging.Emojis.WARNING;

/**
 * Handler for Schema.org property definitions (rdf:Property).
 * <p>
 * This handler processes properties that define relationships between Schema.org types.
 * For each property, it creates:
 * <ul>
 *   <li>A {@link Field} containing the field metadata and type information</li>
 *   <li>An {@link Accessor} defining getter methods for the property</li>
 *   <li>{@link Mutator}s defining setter/adder methods for each possible property type</li>
 * </ul>
 *
 * <p>The handler also:
 * <ul>
 *   <li>Skips deprecated/superseded properties</li>
 *   <li>Deduplicates Java types when using primitive/standard Java types</li>
 *   <li>Associates properties with their domain types via domainIncludes</li>
 * </ul>
 */
public class PropertyModelHandlerImpl implements ModelHandler {

    /** Default constructor */
    public PropertyModelHandlerImpl() {
        // empty
    }

    private static final Logger LOG = LoggerFactory.getLogger(PropertyModelHandlerImpl.class);

    @Override
    public boolean supports(GraphItem graphItem, ParserOptions options) {
        final String id = graphItem.getId();
        final List<String> types = graphItem.getTypes();
        return id.startsWith(SchemaConstants.SCHEMA_PREFIX) && types.contains("rdf:Property");
    }

    @Override
    public void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem, ParserOptions options) {
        List<Type> propertyTypes = deduplicateJavaTypes(ModelUtils.getPropertyTypes(schemaDefinitions, graphItem), options);
        if (propertyTypes.isEmpty()) {
            LOG.info(LogMarkers.WARNING, WARNING, "** DEPRECATED ** {} is superseded by {}", graphItem.getId(), graphItem.getSupersededBy().getId());
            return;
        }

        final String name = graphItem.getLabel().getValue();
        final String description = graphItem.getComment().getValue();
        final List<String> partOf = ModelUtils.getPartOf(graphItem);
        final List<String> source = ModelUtils.getSource(graphItem);
        final List<String> contributor = ModelUtils.getContributor(graphItem);

        final Field field = new Field(
                name,
                propertyTypes
        );

        final Accessor accessor = new Accessor(
                name,
                description,
                partOf,
                source,
                contributor,
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
                                contributor,
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

    /**
     * Deduplicates property types when using Java standard types.
     * <p>
     * When {@code usedJavaTypes} is enabled, multiple Schema.org types may map to the same
     * Java type (e.g., Integer, Float, Number all map to java.lang.Number). This method
     * removes duplicates, keeping only one representative type for each unique Java type.
     *
     * @param propertyTypes the list of property types to deduplicate
     * @param options the parser options indicating whether Java types are used
     * @return a list of unique property types
     */
    private List<Type> deduplicateJavaTypes(List<Type> propertyTypes, ParserOptions options) {
        if (!options.isUsedJavaTypes()) {
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
