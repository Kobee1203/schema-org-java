/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.datatype.Text;

/**
 * Indicates a range of postal codes, usually defined as the set of valid codes between [[postalCodeBegin]] and [[postalCodeEnd]], inclusively.
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/2506">https://github.com/schemaorg/schemaorg/issues/2506</a>
 * @see <a href="https://schema.org/PostalCodeRangeSpecification">https://schema.org/PostalCodeRangeSpecification</a>
 */
public interface PostalCodeRangeSpecification extends StructuredValue {

    /**
     * First postal code in a range (included).
     *
     * @return {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2506">https://github.com/schemaorg/schemaorg/issues/2506</a>
     */
    List<Text> getPostalCodeBeginList();

    /**
     * First postal code in a range (included).
     *
     * @return {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2506">https://github.com/schemaorg/schemaorg/issues/2506</a>
     */
    Text getPostalCodeBegin();

    /**
     * First postal code in a range (included).
     *
     * @param postalCodeBegin Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2506">https://github.com/schemaorg/schemaorg/issues/2506</a>
     */
    void addPostalCodeBegin(Text postalCodeBegin);

    /**
     * Last postal code in the range (included). Needs to be after [[postalCodeBegin]].
     *
     * @return {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2506">https://github.com/schemaorg/schemaorg/issues/2506</a>
     */
    List<Text> getPostalCodeEndList();

    /**
     * Last postal code in the range (included). Needs to be after [[postalCodeBegin]].
     *
     * @return {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2506">https://github.com/schemaorg/schemaorg/issues/2506</a>
     */
    Text getPostalCodeEnd();

    /**
     * Last postal code in the range (included). Needs to be after [[postalCodeBegin]].
     *
     * @param postalCodeEnd Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2506">https://github.com/schemaorg/schemaorg/issues/2506</a>
     */
    void addPostalCodeEnd(Text postalCodeEnd);
}
