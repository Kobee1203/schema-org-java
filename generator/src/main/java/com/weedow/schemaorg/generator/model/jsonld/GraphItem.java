package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type", visible = true, defaultImpl = DefaultItem.class)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "rdfs:Class", value = ClassItem.class),
        @JsonSubTypes.Type(name = "rdf:Property", value = PropertyItem.class),
        @JsonSubTypes.Type(name = "schema:DataType", value = DataTypeItem.class)
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

    @JsonProperty("schema:domainIncludes")
    private List<DomainIncludes> domainIncludes;

    @JsonProperty("schema:rangeIncludes")
    private List<RangIncludes> rangeIncludes;

    @JsonProperty("schema:supersededBy")
    private SupersededBy supersededBy;

    @JsonProperty("rdfs:subClassOf")
    private List<SubClassOf> subClassOf;

    @JsonProperty("schema:isPartOf")
    private List<PartOf> partOf;

    @JsonProperty("schema:source")
    private List<Source> source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Comment getComment() {
        return comment;
    }

    @SuppressWarnings("unchecked")
    public void setComment(Object comment) {
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
    }

    public Label getLabel() {
        return label;
    }

    @SuppressWarnings("unchecked")
    public void setLabel(Object label) {
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
    }

    public List<DomainIncludes> getDomainIncludes() {
        return domainIncludes;
    }

    public void setDomainIncludes(List<DomainIncludes> domainIncludes) {
        this.domainIncludes = domainIncludes;
    }

    public List<RangIncludes> getRangeIncludes() {
        return rangeIncludes;
    }

    public void setRangeIncludes(List<RangIncludes> rangeIncludes) {
        this.rangeIncludes = rangeIncludes;
    }

    public SupersededBy getSupersededBy() {
        return supersededBy;
    }

    public GraphItem setSupersededBy(SupersededBy supersededBy) {
        this.supersededBy = supersededBy;
        return this;
    }

    public List<SubClassOf> getSubClassOf() {
        return subClassOf;
    }

    public void setSubClassOf(List<SubClassOf> subClassOf) {
        this.subClassOf = subClassOf;
    }

    public List<PartOf> getPartOf() {
        return partOf;
    }

    public void setPartOf(List<PartOf> partOf) {
        this.partOf = partOf;
    }

    public List<Source> getSource() {
        return source;
    }

    public void setSource(List<Source> source) {
        this.source = source;
    }
}
