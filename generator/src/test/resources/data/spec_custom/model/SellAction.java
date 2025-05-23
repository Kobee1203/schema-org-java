/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.Organization;
import spec_custom.model.Person;
import spec_custom.model.WarrantyPromise;

/**
 * The act of taking money from a buyer in exchange for goods or services rendered. An agent sells an object, product, or service to a buyer for a price. Reciprocal of BuyAction.
 *
 * @see <a href="https://schema.org/SellAction">https://schema.org/SellAction</a>
 */
public interface SellAction extends TradeAction {

    /**
     * A sub property of participant. The participant/person/organization that bought the object.
     *
     * @return {@link Organization} or {@link Person}
     */
    <T> List<T> getBuyerList();

    /**
     * A sub property of participant. The participant/person/organization that bought the object.
     *
     * @return {@link Organization} or {@link Person}
     */
    <T> T getBuyer();

    /**
     * A sub property of participant. The participant/person/organization that bought the object.
     *
     * @param buyer Organization value to set.
     */
    void addBuyer(Organization buyer);
    /**
     * A sub property of participant. The participant/person/organization that bought the object.
     *
     * @param buyer Person value to set.
     */
    void addBuyer(Person buyer);

    /**
     * The warranty promise(s) included in the offer.
     *
     * @return {@link WarrantyPromise}
     */
    List<WarrantyPromise> getWarrantyPromiseList();

    /**
     * The warranty promise(s) included in the offer.
     *
     * @return {@link WarrantyPromise}
     */
    WarrantyPromise getWarrantyPromise();

    /**
     * The warranty promise(s) included in the offer.
     *
     * @param warrantyPromise WarrantyPromise value to set.
     */
    void addWarrantyPromise(WarrantyPromise warrantyPromise);
}
