/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.Reservation;

/**
 * A group of multiple reservations with common values for all sub-reservations.
 *
 * @see <a href="https://schema.org/ReservationPackage">https://schema.org/ReservationPackage</a>
 */
public interface ReservationPackage extends Reservation {

    /**
     * The individual reservations included in the package. Typically a repeated property.
     *
     * @return {@link Reservation}
     */
    List<Reservation> getSubReservationList();

    /**
     * The individual reservations included in the package. Typically a repeated property.
     *
     * @return {@link Reservation}
     */
    Reservation getSubReservation();

    /**
     * The individual reservations included in the package. Typically a repeated property.
     *
     * @param subReservation Reservation value to set.
     */
    void addSubReservation(Reservation subReservation);
}
