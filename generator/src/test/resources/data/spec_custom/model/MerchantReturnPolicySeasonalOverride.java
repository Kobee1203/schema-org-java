/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.MerchantReturnEnumeration;
import spec_custom.model.datatype.DateTime;
import spec_custom.model.datatype.Integer;
import spec_custom.model.datatype.Date;

/**
 * A seasonal override of a return policy, for example used for holidays.
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/2880">https://github.com/schemaorg/schemaorg/issues/2880</a>
 * @see <a href="https://schema.org/MerchantReturnPolicySeasonalOverride">https://schema.org/MerchantReturnPolicySeasonalOverride</a>
 */
public interface MerchantReturnPolicySeasonalOverride extends Intangible {

    /**
     * Specifies an applicable return policy (from an enumeration).
     *
     * @return {@link MerchantReturnEnumeration}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2288">https://github.com/schemaorg/schemaorg/issues/2288</a>
     */
    List<MerchantReturnEnumeration> getReturnPolicyCategoryList();

    /**
     * Specifies an applicable return policy (from an enumeration).
     *
     * @return {@link MerchantReturnEnumeration}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2288">https://github.com/schemaorg/schemaorg/issues/2288</a>
     */
    MerchantReturnEnumeration getReturnPolicyCategory();

    /**
     * Specifies an applicable return policy (from an enumeration).
     *
     * @param returnPolicyCategory MerchantReturnEnumeration value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2288">https://github.com/schemaorg/schemaorg/issues/2288</a>
     */
    void addReturnPolicyCategory(MerchantReturnEnumeration returnPolicyCategory);

    /**
     * Specifies either a fixed return date or the number of days (from the delivery date) that a product can be returned. Used when the [[returnPolicyCategory]] property is specified as [[MerchantReturnFiniteReturnWindow]].
     *
     * @return {@link DateTime} or {@link Integer} or {@link Date}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2288">https://github.com/schemaorg/schemaorg/issues/2288</a>
     */
    <T> List<T> getMerchantReturnDaysList();

    /**
     * Specifies either a fixed return date or the number of days (from the delivery date) that a product can be returned. Used when the [[returnPolicyCategory]] property is specified as [[MerchantReturnFiniteReturnWindow]].
     *
     * @return {@link DateTime} or {@link Integer} or {@link Date}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2288">https://github.com/schemaorg/schemaorg/issues/2288</a>
     */
    <T> T getMerchantReturnDays();

    /**
     * Specifies either a fixed return date or the number of days (from the delivery date) that a product can be returned. Used when the [[returnPolicyCategory]] property is specified as [[MerchantReturnFiniteReturnWindow]].
     *
     * @param merchantReturnDays DateTime value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2288">https://github.com/schemaorg/schemaorg/issues/2288</a>
     */
    void addMerchantReturnDays(DateTime merchantReturnDays);
    /**
     * Specifies either a fixed return date or the number of days (from the delivery date) that a product can be returned. Used when the [[returnPolicyCategory]] property is specified as [[MerchantReturnFiniteReturnWindow]].
     *
     * @param merchantReturnDays Integer value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2288">https://github.com/schemaorg/schemaorg/issues/2288</a>
     */
    void addMerchantReturnDays(Integer merchantReturnDays);
    /**
     * Specifies either a fixed return date or the number of days (from the delivery date) that a product can be returned. Used when the [[returnPolicyCategory]] property is specified as [[MerchantReturnFiniteReturnWindow]].
     *
     * @param merchantReturnDays Date value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2288">https://github.com/schemaorg/schemaorg/issues/2288</a>
     */
    void addMerchantReturnDays(Date merchantReturnDays);

    /**
     * The start date and time of the item (in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601)).
     *
     * @return {@link DateTime} or {@link Date}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2486">https://github.com/schemaorg/schemaorg/issues/2486</a>
     */
    <T> List<T> getStartDateList();

    /**
     * The start date and time of the item (in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601)).
     *
     * @return {@link DateTime} or {@link Date}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2486">https://github.com/schemaorg/schemaorg/issues/2486</a>
     */
    <T> T getStartDate();

    /**
     * The start date and time of the item (in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601)).
     *
     * @param startDate DateTime value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2486">https://github.com/schemaorg/schemaorg/issues/2486</a>
     */
    void addStartDate(DateTime startDate);
    /**
     * The start date and time of the item (in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601)).
     *
     * @param startDate Date value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2486">https://github.com/schemaorg/schemaorg/issues/2486</a>
     */
    void addStartDate(Date startDate);

    /**
     * The end date and time of the item (in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601)).
     *
     * @return {@link DateTime} or {@link Date}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2486">https://github.com/schemaorg/schemaorg/issues/2486</a>
     */
    <T> List<T> getEndDateList();

    /**
     * The end date and time of the item (in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601)).
     *
     * @return {@link DateTime} or {@link Date}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2486">https://github.com/schemaorg/schemaorg/issues/2486</a>
     */
    <T> T getEndDate();

    /**
     * The end date and time of the item (in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601)).
     *
     * @param endDate DateTime value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2486">https://github.com/schemaorg/schemaorg/issues/2486</a>
     */
    void addEndDate(DateTime endDate);
    /**
     * The end date and time of the item (in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601)).
     *
     * @param endDate Date value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2486">https://github.com/schemaorg/schemaorg/issues/2486</a>
     */
    void addEndDate(Date endDate);
}
