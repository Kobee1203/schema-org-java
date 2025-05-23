/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.DefinedTerm;

/**
 * A set of defined terms, for example a set of categories or a classification scheme, a glossary, dictionary or enumeration.
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/894">https://github.com/schemaorg/schemaorg/issues/894</a>
 * @see <a href="https://schema.org/DefinedTermSet">https://schema.org/DefinedTermSet</a>
 */
public interface DefinedTermSet extends CreativeWork {

    /**
     * A Defined Term contained in this term set.
     *
     * @return {@link DefinedTerm}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/894">https://github.com/schemaorg/schemaorg/issues/894</a>
     */
    List<DefinedTerm> getHasDefinedTermList();

    /**
     * A Defined Term contained in this term set.
     *
     * @return {@link DefinedTerm}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/894">https://github.com/schemaorg/schemaorg/issues/894</a>
     */
    DefinedTerm getHasDefinedTerm();

    /**
     * A Defined Term contained in this term set.
     *
     * @param hasDefinedTerm DefinedTerm value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/894">https://github.com/schemaorg/schemaorg/issues/894</a>
     */
    void addHasDefinedTerm(DefinedTerm hasDefinedTerm);
}
