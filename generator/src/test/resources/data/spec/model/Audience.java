/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.datatype.Text;
import spec.model.AdministrativeArea;

/**
 * Intended audience for an item, i.e. the group for whom the item was created.
 *
 * @see <a href="https://schema.org/Audience">https://schema.org/Audience</a>
 */
public interface Audience extends Intangible {

    /**
     * The target group associated with a given audience (e.g. veterans, car owners, musicians, etc.).
     *
     * @return {@link Text}
     */
    List<Text> getAudienceTypeList();

    /**
     * The target group associated with a given audience (e.g. veterans, car owners, musicians, etc.).
     *
     * @return {@link Text}
     */
    Text getAudienceType();

    /**
     * The target group associated with a given audience (e.g. veterans, car owners, musicians, etc.).
     *
     * @param audienceType Text value to set.
     */
    void addAudienceType(Text audienceType);

    /**
     * The geographic area associated with the audience.
     *
     * @return {@link AdministrativeArea}
     */
    List<AdministrativeArea> getGeographicAreaList();

    /**
     * The geographic area associated with the audience.
     *
     * @return {@link AdministrativeArea}
     */
    AdministrativeArea getGeographicArea();

    /**
     * The geographic area associated with the audience.
     *
     * @param geographicArea AdministrativeArea value to set.
     */
    void addGeographicArea(AdministrativeArea geographicArea);
}
