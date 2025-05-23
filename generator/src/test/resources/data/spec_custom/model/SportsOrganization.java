/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.datatype.Text;
import spec_custom.model.datatype.URL;

/**
 * Represents the collection of all sports organizations, including sports teams, governing bodies, and sports associations.
 *
 * @see <a href="https://schema.org/SportsOrganization">https://schema.org/SportsOrganization</a>
 */
public interface SportsOrganization extends Organization {

    /**
     * A type of sport (e.g. Baseball).
     *
     * @return {@link Text} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1951">https://github.com/schemaorg/schemaorg/issues/1951</a>
     */
    <T> List<T> getSportList();

    /**
     * A type of sport (e.g. Baseball).
     *
     * @return {@link Text} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1951">https://github.com/schemaorg/schemaorg/issues/1951</a>
     */
    <T> T getSport();

    /**
     * A type of sport (e.g. Baseball).
     *
     * @param sport Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1951">https://github.com/schemaorg/schemaorg/issues/1951</a>
     */
    void addSport(Text sport);
    /**
     * A type of sport (e.g. Baseball).
     *
     * @param sport URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1951">https://github.com/schemaorg/schemaorg/issues/1951</a>
     */
    void addSport(URL sport);
}
