/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.GeoCoordinates;
import spec.model.datatype.Number;
import spec.model.datatype.Text;
import spec.model.Distance;

/**
 * A GeoCircle is a GeoShape representing a circular geographic area. As it is a GeoShape
 *           it provides the simple textual property 'circle', but also allows the combination of postalCode alongside geoRadius.
 *           The center of the circle can be indicated via the 'geoMidpoint' property, or more approximately using 'address', 'postalCode'.
 *        
 *
 * @see <a href="https://schema.org/GeoCircle">https://schema.org/GeoCircle</a>
 */
public interface GeoCircle extends GeoShape {

    /**
     * Indicates the GeoCoordinates at the centre of a GeoShape, e.g. GeoCircle.
     *
     * @return {@link GeoCoordinates}
     */
    List<GeoCoordinates> getGeoMidpointList();

    /**
     * Indicates the GeoCoordinates at the centre of a GeoShape, e.g. GeoCircle.
     *
     * @return {@link GeoCoordinates}
     */
    GeoCoordinates getGeoMidpoint();

    /**
     * Indicates the GeoCoordinates at the centre of a GeoShape, e.g. GeoCircle.
     *
     * @param geoMidpoint GeoCoordinates value to set.
     */
    void addGeoMidpoint(GeoCoordinates geoMidpoint);

    /**
     * Indicates the approximate radius of a GeoCircle (metres unless indicated otherwise via Distance notation).
     *
     * @return {@link Number} or {@link Text} or {@link Distance}
     */
    <T> List<T> getGeoRadiusList();

    /**
     * Indicates the approximate radius of a GeoCircle (metres unless indicated otherwise via Distance notation).
     *
     * @return {@link Number} or {@link Text} or {@link Distance}
     */
    <T> T getGeoRadius();

    /**
     * Indicates the approximate radius of a GeoCircle (metres unless indicated otherwise via Distance notation).
     *
     * @param geoRadius Number value to set.
     */
    void addGeoRadius(Number geoRadius);
    /**
     * Indicates the approximate radius of a GeoCircle (metres unless indicated otherwise via Distance notation).
     *
     * @param geoRadius Text value to set.
     */
    void addGeoRadius(Text geoRadius);
    /**
     * Indicates the approximate radius of a GeoCircle (metres unless indicated otherwise via Distance notation).
     *
     * @param geoRadius Distance value to set.
     */
    void addGeoRadius(Distance geoRadius);
}
