/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.QualitativeValue;
import org.schema.model.datatype.Text;
import org.schema.model.datatype.DateTime;
import org.schema.model.datatype.Time;
import org.schema.model.datatype.Integer;
import org.schema.model.QuantitativeValue;

/**
 * A reservation for lodging at a hotel, motel, inn, etc.<br/><br/>Note: This type is for information about actual reservations, e.g. in confirmation emails or HTML pages with individual confirmations of reservations.
 *
 * @see <a href="https://schema.org/LodgingReservation">https://schema.org/LodgingReservation</a>
 */
public interface LodgingReservation extends Reservation {

    /**
     * Textual description of the unit type (including suite vs. room, size of bed, etc.).
     *
     * @return {@link QualitativeValue} or {@link Text}
     */
    <T> T getLodgingUnitType();

    /**
     * Textual description of the unit type (including suite vs. room, size of bed, etc.).
     *
     * @param lodgingUnitType QualitativeValue value to set.
     */
    void setLodgingUnitType(QualitativeValue lodgingUnitType);
    /**
     * Textual description of the unit type (including suite vs. room, size of bed, etc.).
     *
     * @param lodgingUnitType Text value to set.
     */
    void setLodgingUnitType(Text lodgingUnitType);

    /**
     * A full description of the lodging unit.
     *
     * @return {@link Text}
     */
    Text getLodgingUnitDescription();

    /**
     * A full description of the lodging unit.
     *
     * @param lodgingUnitDescription Text value to set.
     */
    void setLodgingUnitDescription(Text lodgingUnitDescription);

    /**
     * The latest someone may check out of a lodging establishment.
     *
     * @return {@link DateTime} or {@link Time}
     */
    <T> T getCheckoutTime();

    /**
     * The latest someone may check out of a lodging establishment.
     *
     * @param checkoutTime DateTime value to set.
     */
    void setCheckoutTime(DateTime checkoutTime);
    /**
     * The latest someone may check out of a lodging establishment.
     *
     * @param checkoutTime Time value to set.
     */
    void setCheckoutTime(Time checkoutTime);

    /**
     * The number of adults staying in the unit.
     *
     * @return {@link Integer} or {@link QuantitativeValue}
     */
    <T> T getNumAdults();

    /**
     * The number of adults staying in the unit.
     *
     * @param numAdults Integer value to set.
     */
    void setNumAdults(Integer numAdults);
    /**
     * The number of adults staying in the unit.
     *
     * @param numAdults QuantitativeValue value to set.
     */
    void setNumAdults(QuantitativeValue numAdults);

    /**
     * The earliest someone may check into a lodging establishment.
     *
     * @return {@link DateTime} or {@link Time}
     */
    <T> T getCheckinTime();

    /**
     * The earliest someone may check into a lodging establishment.
     *
     * @param checkinTime DateTime value to set.
     */
    void setCheckinTime(DateTime checkinTime);
    /**
     * The earliest someone may check into a lodging establishment.
     *
     * @param checkinTime Time value to set.
     */
    void setCheckinTime(Time checkinTime);

    /**
     * The number of children staying in the unit.
     *
     * @return {@link Integer} or {@link QuantitativeValue}
     */
    <T> T getNumChildren();

    /**
     * The number of children staying in the unit.
     *
     * @param numChildren Integer value to set.
     */
    void setNumChildren(Integer numChildren);
    /**
     * The number of children staying in the unit.
     *
     * @param numChildren QuantitativeValue value to set.
     */
    void setNumChildren(QuantitativeValue numChildren);
}
