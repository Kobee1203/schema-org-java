package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.jsonld.*;

public final class ModelHandlerTestUtils {

    private ModelHandlerTestUtils() {
    }

    public static Label label(String language, String value) {
        Label label = new Label();
        label.setLanguage(language);
        label.setValue(value);
        return label;
    }

    public static Comment comment(String language, String value) {
        Comment comment = new Comment();
        comment.setLanguage(language);
        comment.setValue(value);
        return comment;
    }

    public static SubClassOf subClassOf(String id) {
        SubClassOf subClassOf = new SubClassOf();
        subClassOf.setId(id);
        return subClassOf;
    }

    public static PartOf partOf(String id) {
        PartOf partOf = new PartOf();
        partOf.setId(id);
        return partOf;
    }

    public static RangeIncludes rangeInclude(String id) {
        RangeIncludes rangeIncludes = new RangeIncludes();
        rangeIncludes.setId(id);
        return rangeIncludes;
    }

    public static DomainIncludes domainInclude(String id) {
        DomainIncludes domainIncludes = new DomainIncludes();
        domainIncludes.setId(id);
        return domainIncludes;
    }

    public static Source source(String id) {
        Source source = new Source();
        source.setId(id);
        return source;
    }

    public static SubPropertyOf subPropertyOf(String id) {
        SubPropertyOf subPropertyOf = new SubPropertyOf();
        subPropertyOf.setId(id);
        return subPropertyOf;
    }

    public static SupersededBy supersededBy(String id) {
        SupersededBy supersededBy = new SupersededBy();
        supersededBy.setId(id);
        return supersededBy;
    }
}
