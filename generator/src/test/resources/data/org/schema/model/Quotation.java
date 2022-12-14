/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.Person;
import org.schema.model.Organization;

/**
 * A quotation. Often but not necessarily from some written work, attributable to a real world author and - if associated with a fictional character - to any fictional Person. Use [[isBasedOn]] to link to source/origin. The [[recordedIn]] property can be used to reference a Quotation from an [[Event]].
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/271">https://github.com/schemaorg/schemaorg/issues/271</a>
 * @see <a href="https://schema.org/Quotation">https://schema.org/Quotation</a>
 */
public interface Quotation extends CreativeWork {

    /**
     * The (e.g. fictional) character, Person or Organization to whom the quotation is attributed within the containing CreativeWork.
     *
     * @return {@link Person} or {@link Organization}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/271">https://github.com/schemaorg/schemaorg/issues/271</a>
     */
    <T> T getSpokenByCharacter();

    /**
     * The (e.g. fictional) character, Person or Organization to whom the quotation is attributed within the containing CreativeWork.
     *
     * @param spokenByCharacter Person value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/271">https://github.com/schemaorg/schemaorg/issues/271</a>
     */
    void setSpokenByCharacter(Person spokenByCharacter);
    /**
     * The (e.g. fictional) character, Person or Organization to whom the quotation is attributed within the containing CreativeWork.
     *
     * @param spokenByCharacter Organization value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/271">https://github.com/schemaorg/schemaorg/issues/271</a>
     */
    void setSpokenByCharacter(Organization spokenByCharacter);
}
