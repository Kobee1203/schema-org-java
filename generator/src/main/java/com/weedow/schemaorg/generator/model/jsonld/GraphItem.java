package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type", visible = true, defaultImpl = DefaultItem.class)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "rdfs:Class", value = ClassItem.class),
        @JsonSubTypes.Type(name = "rdf:Property", value = PropertyItem.class),
        @JsonSubTypes.Type(name = "schema:DataType", value = DataTypeItem.class) // Doesn't work: @type contains 'schema:DataType' AND 'rdfs:Class' (it's an array not a single value)
})
public abstract class GraphItem {

    private static final Logger LOG = LoggerFactory.getLogger(GraphItem.class);

    @JsonProperty("@id")
    private String id;

    @JsonProperty("@type")
    private List<String> types;

    @JsonProperty("rdfs:comment")
    private Comment comment;

    @JsonProperty("rdfs:label")
    private Label label;

    @JsonProperty("schema:isPartOf")
    private List<PartOf> partOf;

    @JsonProperty("schema:source")
    private List<Source> source;

    //*** [START] Specific Fields for Property items ***//

    @JsonProperty("schema:domainIncludes")
    private List<DomainIncludes> domainIncludes;

    @JsonProperty("schema:rangeIncludes")
    private List<RangeIncludes> rangeIncludes;

    @JsonProperty("schema:supersededBy")
    private SupersededBy supersededBy;

    //*** [END] Specific Fields for Property items ***//

    //*** [START] Specific Fields for Class items ***//

    @JsonProperty("rdfs:subClassOf")
    private List<SubClassOf> subClassOf;

    //*** [END] Specific Fields for Class items ***//

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
            LOG.warn("Type '{}' not handled for 'comment' field", comment.getClass().getCanonicalName());
        }
        this.comment = c;
        return this;
    }

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
            LOG.warn("Type '{}' not handled for 'label' field", label.getClass().getCanonicalName());
        }
        this.label = l;
        return this;
    }
}
