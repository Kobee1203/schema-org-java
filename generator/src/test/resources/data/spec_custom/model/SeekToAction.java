/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.datatype.Number;
import spec_custom.model.HyperTocEntry;

/**
 * This is the [[Action]] of navigating to a specific [[startOffset]] timestamp within a [[VideoObject]], typically represented with a URL template structure.
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/2722">https://github.com/schemaorg/schemaorg/issues/2722</a>
 * @see <a href="https://schema.org/SeekToAction">https://schema.org/SeekToAction</a>
 */
public interface SeekToAction extends Action {

    /**
     * The start time of the clip expressed as the number of seconds from the beginning of the work.
     *
     * @return {@link Number} or {@link HyperTocEntry}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2021">https://github.com/schemaorg/schemaorg/issues/2021</a>
     */
    <T> List<T> getStartOffsetList();

    /**
     * The start time of the clip expressed as the number of seconds from the beginning of the work.
     *
     * @return {@link Number} or {@link HyperTocEntry}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2021">https://github.com/schemaorg/schemaorg/issues/2021</a>
     */
    <T> T getStartOffset();

    /**
     * The start time of the clip expressed as the number of seconds from the beginning of the work.
     *
     * @param startOffset Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2021">https://github.com/schemaorg/schemaorg/issues/2021</a>
     */
    void addStartOffset(Number startOffset);
    /**
     * The start time of the clip expressed as the number of seconds from the beginning of the work.
     *
     * @param startOffset HyperTocEntry value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2021">https://github.com/schemaorg/schemaorg/issues/2021</a>
     */
    void addStartOffset(HyperTocEntry startOffset);
}
