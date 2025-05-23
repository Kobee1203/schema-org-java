/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.datatype.Text;
import spec.model.BusStop;
import spec.model.BusStation;

/**
 * A trip on a commercial bus line.
 *
 * @see <a href="https://schema.org/BusTrip">https://schema.org/BusTrip</a>
 */
public interface BusTrip extends Trip {

    /**
     * The name of the bus (e.g. Bolt Express).
     *
     * @return {@link Text}
     */
    List<Text> getBusNameList();

    /**
     * The name of the bus (e.g. Bolt Express).
     *
     * @return {@link Text}
     */
    Text getBusName();

    /**
     * The name of the bus (e.g. Bolt Express).
     *
     * @param busName Text value to set.
     */
    void addBusName(Text busName);

    /**
     * The unique identifier for the bus.
     *
     * @return {@link Text}
     */
    List<Text> getBusNumberList();

    /**
     * The unique identifier for the bus.
     *
     * @return {@link Text}
     */
    Text getBusNumber();

    /**
     * The unique identifier for the bus.
     *
     * @param busNumber Text value to set.
     */
    void addBusNumber(Text busNumber);

    /**
     * The stop or station from which the bus departs.
     *
     * @return {@link BusStop} or {@link BusStation}
     */
    <T> List<T> getDepartureBusStopList();

    /**
     * The stop or station from which the bus departs.
     *
     * @return {@link BusStop} or {@link BusStation}
     */
    <T> T getDepartureBusStop();

    /**
     * The stop or station from which the bus departs.
     *
     * @param departureBusStop BusStop value to set.
     */
    void addDepartureBusStop(BusStop departureBusStop);
    /**
     * The stop or station from which the bus departs.
     *
     * @param departureBusStop BusStation value to set.
     */
    void addDepartureBusStop(BusStation departureBusStop);

    /**
     * The stop or station from which the bus arrives.
     *
     * @return {@link BusStop} or {@link BusStation}
     */
    <T> List<T> getArrivalBusStopList();

    /**
     * The stop or station from which the bus arrives.
     *
     * @return {@link BusStop} or {@link BusStation}
     */
    <T> T getArrivalBusStop();

    /**
     * The stop or station from which the bus arrives.
     *
     * @param arrivalBusStop BusStop value to set.
     */
    void addArrivalBusStop(BusStop arrivalBusStop);
    /**
     * The stop or station from which the bus arrives.
     *
     * @param arrivalBusStop BusStation value to set.
     */
    void addArrivalBusStop(BusStation arrivalBusStop);
}
