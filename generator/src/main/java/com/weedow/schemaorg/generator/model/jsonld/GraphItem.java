package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Abstract base class representing a graph item from Schema.org JSON-LD specification.
 * Contains common properties shared by classes, properties, and data types.
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type", visible = true, defaultImpl = DefaultItem.class)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "rdfs:Class", value = ClassItem.class),
        @JsonSubTypes.Type(name = "rdf:Property", value = PropertyItem.class),
        @JsonSubTypes.Type(name = "schema:DataType", value = DataTypeItem.class) // Doesn't work: @type contains 'schema:DataType' AND 'rdfs:Class' (it's an array not a single value)
})
public abstract class GraphItem {

    private static final Logger LOG = LoggerFactory.getLogger(GraphItem.class);

    /**
     * Item id.
     *
     * @return The item id
     * @param id The item id
     */
    @JsonProperty("@id")
    private String id;

    /**
     * Item types.
     *
     * @return The item types
     * @param types The item types
     */
    @JsonProperty("@type")
    private List<String> types;

    /**
     * Item-related comment.
     *
     * @return The item-related comment
     * @param comment The item-related comment
     */
    @JsonProperty("rdfs:comment")
    private Comment comment;

    /**
     * Item label.
     *
     * @return The item label
     * @param label The item label
     */
    @JsonProperty("rdfs:label")
    private Label label;

    /**
     * Indicates what the item is part of.
     *
     * @return What the item is part of
     * @param partOf What the item is part of
     */
    @JsonProperty("schema:isPartOf")
    private List<PartOf> partOf;

    /**
     * References that define the vocabulary's structure.
     *
     * @return The references that define the vocabulary's structure
     * @param source The references that define the vocabulary's structure
     */
    @JsonProperty("schema:source")
    private List<Source> source;

    /**
     * Contributors for this item.
     *
     * @return The contributors for this item
     * @param contributor The contributors for this item
     */
    @JsonProperty("schema:contributor")
    private List<Contributor> contributor;

    //*** [START] Specific Fields for Property items ***//

    /**
     * Classes that is (one of) the type(s) the property is expected to be used on.
     *
     * @return The classes that is (one of) the type(s) the property is expected to be used on
     * @param domainIncludes The classes that is (one of) the type(s) the property is expected to be used on
     */
    @JsonProperty("schema:domainIncludes")
    private List<DomainIncludes> domainIncludes;

    /**
     * Classes that constitutes (one of) the expected type(s) for values of the property.
     *
     * @return The classes that constitutes (one of) the expected type(s) for values of the property
     * @param rangeIncludes The classes that constitutes (one of) the expected type(s) for values of the property
     */
    @JsonProperty("schema:rangeIncludes")
    private List<RangeIncludes> rangeIncludes;

    /**
     * A related resource that is superseded by this item.
     *
     * @return The related resource that is superseded by this item
     * @param supersededBy The related resource that is superseded by this item
     */
    @JsonProperty("schema:supersededBy")
    private SupersededBy supersededBy;

    //*** [END] Specific Fields for Property items ***//

    //*** [START] Specific Fields for Class items ***//

    /**
     * Subclasses of this item.
     *
     * @return The subclasses of this item
     * @param subClassOf The subclasses of this item
     */
    @JsonProperty("rdfs:subClassOf")
    private List<SubClassOf> subClassOf;

    //*** [END] Specific Fields for Class items ***//

    /**
     * Sets the comment field from either a String or a Map containing language and value.
     *
     * @param comment the comment value
     * @return this GraphItem for method chaining
     */
    @SuppressWarnings("unchecked")
    public GraphItem setComment(Object comment) {
        final Comment c = new Comment();
        if (comment instanceof String) {
            c.setLanguage("en");
            c.setValue(comment.toString());
        } else if (comment instanceof Map) {
            final Map<String, String> commentMap = (Map<String, String>) comment;
            c.setLanguage(commentMap.get("@language"));
            c.setValue(commentMap.get("@value"));
        } else {
            LOG.warn("Type '{}' not handled for 'comment' field", comment.getClass().getName());
        }
        this.comment = c;
        return this;
    }

    /**
     * Sets the label field from either a String or a Map containing language and value.
     *
     * @param label the label value
     * @return this GraphItem for method chaining
     */
    @SuppressWarnings("unchecked")
    public GraphItem setLabel(Object label) {
        final Label l = new Label();
        if (label instanceof String) {
            l.setLanguage("en");
            l.setValue(label.toString());
        } else if (label instanceof Map) {
            final Map<String, String> labelMap = (Map<String, String>) label;
            l.setLanguage(labelMap.get("@language"));
            l.setValue(labelMap.get("@value"));
        } else {
            LOG.warn("Type '{}' not handled for 'label' field", label.getClass().getName());
        }
        this.label = l;
        return this;
    }
}
