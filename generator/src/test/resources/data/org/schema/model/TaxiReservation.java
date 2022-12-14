/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.datatype.Integer;
import org.schema.model.QuantitativeValue;
import org.schema.model.datatype.DateTime;
import org.schema.model.Place;

/**
 * A reservation for a taxi.<br/><br/>Note: This type is for information about actual reservations, e.g. in confirmation emails or HTML pages with individual confirmations of reservations. For offers of tickets, use [[Offer]].
 *
 * @see <a href="https://schema.org/TaxiReservation">https://schema.org/TaxiReservation</a>
 */
public interface TaxiReservation extends Reservation {

    /**
     * Number of people the reservation should accommodate.
     *
     * @return {@link Integer} or {@link QuantitativeValue}
     */
    <T> T getPartySize();

    /**
     * Number of people the reservation should accommodate.
     *
     * @param partySize Integer value to set.
     */
    void setPartySize(Integer partySize);
    /**
     * Number of people the reservation should accommodate.
     *
     * @param partySize QuantitativeValue value to set.
     */
    void setPartySize(QuantitativeValue partySize);

    /**
     * When a taxi will pickup a passenger or a rental car can be picked up.
     *
     * @return {@link DateTime}
     */
    DateTime getPickupTime();

    /**
     * When a taxi will pickup a passenger or a rental car can be picked up.
     *
     * @param pickupTime DateTime value to set.
     */
    void setPickupTime(DateTime pickupTime);

    /**
     * Where a taxi will pick up a passenger or a rental car can be picked up.
     *
     * @return {@link Place}
     */
    Place getPickupLocation();

    /**
     * Where a taxi will pick up a passenger or a rental car can be picked up.
     *
     * @param pickupLocation Place value to set.
     */
    void setPickupLocation(Place pickupLocation);
}
