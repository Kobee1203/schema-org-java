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
 * The act of giving money to a seller in exchange for goods or services rendered. An agent buys an object, product, or service from a seller for a price. Reciprocal of SellAction.
 *
 * @see <a href="https://schema.org/BuyAction">https://schema.org/BuyAction</a>
 */
public interface BuyAction extends TradeAction {

    /**
     * An entity which offers (sells / leases / lends / loans) the services / goods.  A seller may also be a provider.
     *
     * @return {@link Organization} or {@link Person}
     */
    <T> List<T> getSellerList();

    /**
     * An entity which offers (sells / leases / lends / loans) the services / goods.  A seller may also be a provider.
     *
     * @return {@link Organization} or {@link Person}
     */
    <T> T getSeller();

    /**
     * An entity which offers (sells / leases / lends / loans) the services / goods.  A seller may also be a provider.
     *
     * @param seller Organization value to set.
     */
    void addSeller(Organization seller);
    /**
     * An entity which offers (sells / leases / lends / loans) the services / goods.  A seller may also be a provider.
     *
     * @param seller Person value to set.
     */
    void addSeller(Person seller);

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

    /**
     * 'vendor' is an earlier term for 'seller'.
     *
     * @return {@link Organization} or {@link Person}
     */
    <T> List<T> getVendorList();

    /**
     * 'vendor' is an earlier term for 'seller'.
     *
     * @return {@link Organization} or {@link Person}
     */
    <T> T getVendor();

    /**
     * 'vendor' is an earlier term for 'seller'.
     *
     * @param vendor Organization value to set.
     */
    void addVendor(Organization vendor);
    /**
     * 'vendor' is an earlier term for 'seller'.
     *
     * @param vendor Person value to set.
     */
    void addVendor(Person vendor);
}
