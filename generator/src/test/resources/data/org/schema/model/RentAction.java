/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.Person;
import org.schema.model.Organization;
import org.schema.model.RealEstateAgent;

/**
 * The act of giving money in return for temporary use, but not ownership, of an object such as a vehicle or property. For example, an agent rents a property from a landlord in exchange for a periodic payment.
 *
 * @see <a href="https://schema.org/RentAction">https://schema.org/RentAction</a>
 */
public interface RentAction extends TradeAction {

    /**
     * A sub property of participant. The owner of the real estate property.
     *
     * @return {@link Person} or {@link Organization}
     */
    <T> T getLandlord();

    /**
     * A sub property of participant. The owner of the real estate property.
     *
     * @param landlord Person value to set.
     */
    void setLandlord(Person landlord);
    /**
     * A sub property of participant. The owner of the real estate property.
     *
     * @param landlord Organization value to set.
     */
    void setLandlord(Organization landlord);

    /**
     * A sub property of participant. The real estate agent involved in the action.
     *
     * @return {@link RealEstateAgent}
     */
    RealEstateAgent getRealEstateAgent();

    /**
     * A sub property of participant. The real estate agent involved in the action.
     *
     * @param realEstateAgent RealEstateAgent value to set.
     */
    void setRealEstateAgent(RealEstateAgent realEstateAgent);
}
