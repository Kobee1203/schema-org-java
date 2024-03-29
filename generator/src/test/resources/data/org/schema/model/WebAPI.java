/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import java.util.List;
import org.schema.model.datatype.URL;
import org.schema.model.CreativeWork;

/**
 * An application programming interface accessible over Web/Internet technologies.
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/1423">https://github.com/schemaorg/schemaorg/issues/1423</a>
 * @see <a href="https://schema.org/WebAPI">https://schema.org/WebAPI</a>
 */
public interface WebAPI extends Service {

    /**
     * Further documentation describing the Web API in more detail.
     *
     * @return {@link URL} or {@link CreativeWork}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1423">https://github.com/schemaorg/schemaorg/issues/1423</a>
     */
    <T> List<T> getDocumentationList();

    /**
     * Further documentation describing the Web API in more detail.
     *
     * @return {@link URL} or {@link CreativeWork}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1423">https://github.com/schemaorg/schemaorg/issues/1423</a>
     */
    <T> T getDocumentation();

    /**
     * Further documentation describing the Web API in more detail.
     *
     * @param documentation URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1423">https://github.com/schemaorg/schemaorg/issues/1423</a>
     */
    void addDocumentation(URL documentation);
    /**
     * Further documentation describing the Web API in more detail.
     *
     * @param documentation CreativeWork value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1423">https://github.com/schemaorg/schemaorg/issues/1423</a>
     */
    void addDocumentation(CreativeWork documentation);
}
