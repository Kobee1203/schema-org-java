/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.HealthAspectEnumeration;

/**
 * [[HealthTopicContent]] is [[WebContent]] that is about some aspect of a health topic, e.g. a condition, its symptoms or treatments. Such content may be comprised of several parts or sections and use different types of media. Multiple instances of [[WebContent]] (and hence [[HealthTopicContent]]) can be related using [[hasPart]] / [[isPartOf]] where there is some kind of content hierarchy, and their content described with [[about]] and [[mentions]] e.g. building upon the existing [[MedicalCondition]] vocabulary.
 *   
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/2374">https://github.com/schemaorg/schemaorg/issues/2374</a>
 * @see <a href="https://schema.org/HealthTopicContent">https://schema.org/HealthTopicContent</a>
 */
public interface HealthTopicContent extends WebContent {

    /**
     * Indicates the aspect or aspects specifically addressed in some [[HealthTopicContent]]. For example, that the content is an overview, or that it talks about treatment, self-care, treatments or their side-effects.
     *
     * @return {@link HealthAspectEnumeration}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2373">https://github.com/schemaorg/schemaorg/issues/2373</a>
     */
    List<HealthAspectEnumeration> getHasHealthAspectList();

    /**
     * Indicates the aspect or aspects specifically addressed in some [[HealthTopicContent]]. For example, that the content is an overview, or that it talks about treatment, self-care, treatments or their side-effects.
     *
     * @return {@link HealthAspectEnumeration}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2373">https://github.com/schemaorg/schemaorg/issues/2373</a>
     */
    HealthAspectEnumeration getHasHealthAspect();

    /**
     * Indicates the aspect or aspects specifically addressed in some [[HealthTopicContent]]. For example, that the content is an overview, or that it talks about treatment, self-care, treatments or their side-effects.
     *
     * @param hasHealthAspect HealthAspectEnumeration value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2373">https://github.com/schemaorg/schemaorg/issues/2373</a>
     */
    void addHasHealthAspect(HealthAspectEnumeration hasHealthAspect);
}
