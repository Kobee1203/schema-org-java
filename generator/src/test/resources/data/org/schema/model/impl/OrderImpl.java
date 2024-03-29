/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model.impl;

import org.schema.model.OrderStatus;
import org.schema.model.datatype.Boolean;
import org.schema.model.datatype.Text;
import org.schema.model.Person;
import org.schema.model.Organization;
import org.schema.model.datatype.Date;
import org.schema.model.datatype.DateTime;
import org.schema.model.datatype.Number;
import org.schema.model.Offer;
import org.schema.model.Invoice;
import org.schema.model.PaymentMethod;
import org.schema.model.ParcelDelivery;
import org.schema.model.Product;
import org.schema.model.Service;
import org.schema.model.OrderItem;
import org.schema.model.PostalAddress;
import org.schema.model.datatype.URL;
import org.schema.model.Action;
import org.schema.model.CreativeWork;
import org.schema.model.Event;
import org.schema.model.PropertyValue;
import org.schema.model.ImageObject;
import org.schema.model.Thing;
import com.weedow.schemaorg.commons.model.JsonLdTypeName;
import com.weedow.schemaorg.commons.model.JsonLdFieldTypes;
import java.util.List;
import org.schema.model.Intangible;
import org.schema.model.Order;

/**
 * An order is a confirmation of a transaction (a receipt), which can contain multiple line items, each represented by an Offer that has been accepted by the customer.
 *
 * @see <a href="https://schema.org/Order">https://schema.org/Order</a>
 */
@JsonLdTypeName("Order")
public class OrderImpl extends com.weedow.schemaorg.commons.model.JsonLdNodeImpl implements Order {

    private List<OrderStatus> orderStatus;

    /**
     * The current status of the order.
     *
     * @return {@link OrderStatus}
     */
    @Override
    public List<OrderStatus> getOrderStatusList() {
        return orderStatus;
    }

    /**
     * The current status of the order.
     *
     * @return {@link OrderStatus}
     */
    @Override
    public OrderStatus getOrderStatus() {
        return getFirst(orderStatus);
    }

    /**
     * The current status of the order.
     *
     * @param orderStatus OrderStatus value to set.
     */
    @Override
    public void addOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = add(this.orderStatus, orderStatus);
    }

    private List<Boolean> isGift;

    /**
     * Indicates whether the offer was accepted as a gift for someone other than the buyer.
     *
     * @return {@link Boolean}
     */
    @Override
    public List<Boolean> getIsGiftList() {
        return isGift;
    }

    /**
     * Indicates whether the offer was accepted as a gift for someone other than the buyer.
     *
     * @return {@link Boolean}
     */
    @Override
    public Boolean getIsGift() {
        return getFirst(isGift);
    }

    /**
     * Indicates whether the offer was accepted as a gift for someone other than the buyer.
     *
     * @param isGift Boolean value to set.
     */
    @Override
    public void addIsGift(Boolean isGift) {
        this.isGift = add(this.isGift, isGift);
    }

    private List<Text> confirmationNumber;

    /**
     * A number that confirms the given order or payment has been received.
     *
     * @return {@link Text}
     */
    @Override
    public List<Text> getConfirmationNumberList() {
        return confirmationNumber;
    }

    /**
     * A number that confirms the given order or payment has been received.
     *
     * @return {@link Text}
     */
    @Override
    public Text getConfirmationNumber() {
        return getFirst(confirmationNumber);
    }

    /**
     * A number that confirms the given order or payment has been received.
     *
     * @param confirmationNumber Text value to set.
     */
    @Override
    public void addConfirmationNumber(Text confirmationNumber) {
        this.confirmationNumber = add(this.confirmationNumber, confirmationNumber);
    }

    @JsonLdFieldTypes({ Person.class, Organization.class })
    private List<Object> broker;

    /**
     * An entity that arranges for an exchange between a buyer and a seller.  In most cases a broker never acquires or releases ownership of a product or service involved in an exchange.  If it is not clear whether an entity is a broker, seller, or buyer, the latter two terms are preferred.
     *
     * @return {@link Person} or {@link Organization}
     */
    @Override
    public <T> List<T> getBrokerList() {
        return (List<T>) broker;
    }

    /**
     * An entity that arranges for an exchange between a buyer and a seller.  In most cases a broker never acquires or releases ownership of a product or service involved in an exchange.  If it is not clear whether an entity is a broker, seller, or buyer, the latter two terms are preferred.
     *
     * @return {@link Person} or {@link Organization}
     */
    @Override
    public <T> T getBroker() {
        return (T) getFirst(broker);
    }

    /**
     * An entity that arranges for an exchange between a buyer and a seller.  In most cases a broker never acquires or releases ownership of a product or service involved in an exchange.  If it is not clear whether an entity is a broker, seller, or buyer, the latter two terms are preferred.
     *
     * @param broker Person value to set.
     */
    @Override
    public void addBroker(Person broker) {
        this.broker = add(this.broker, broker);
    }
    /**
     * An entity that arranges for an exchange between a buyer and a seller.  In most cases a broker never acquires or releases ownership of a product or service involved in an exchange.  If it is not clear whether an entity is a broker, seller, or buyer, the latter two terms are preferred.
     *
     * @param broker Organization value to set.
     */
    @Override
    public void addBroker(Organization broker) {
        this.broker = add(this.broker, broker);
    }

    @JsonLdFieldTypes({ Date.class, DateTime.class })
    private List<Object> paymentDueDate;

    /**
     * The date that payment is due.
     *
     * @return {@link Date} or {@link DateTime}
     */
    @Override
    public <T> List<T> getPaymentDueDateList() {
        return (List<T>) paymentDueDate;
    }

    /**
     * The date that payment is due.
     *
     * @return {@link Date} or {@link DateTime}
     */
    @Override
    public <T> T getPaymentDueDate() {
        return (T) getFirst(paymentDueDate);
    }

    /**
     * The date that payment is due.
     *
     * @param paymentDueDate Date value to set.
     */
    @Override
    public void addPaymentDueDate(Date paymentDueDate) {
        this.paymentDueDate = add(this.paymentDueDate, paymentDueDate);
    }
    /**
     * The date that payment is due.
     *
     * @param paymentDueDate DateTime value to set.
     */
    @Override
    public void addPaymentDueDate(DateTime paymentDueDate) {
        this.paymentDueDate = add(this.paymentDueDate, paymentDueDate);
    }

    @JsonLdFieldTypes({ Organization.class, Person.class })
    private List<Object> seller;

    /**
     * An entity which offers (sells / leases / lends / loans) the services / goods.  A seller may also be a provider.
     *
     * @return {@link Organization} or {@link Person}
     */
    @Override
    public <T> List<T> getSellerList() {
        return (List<T>) seller;
    }

    /**
     * An entity which offers (sells / leases / lends / loans) the services / goods.  A seller may also be a provider.
     *
     * @return {@link Organization} or {@link Person}
     */
    @Override
    public <T> T getSeller() {
        return (T) getFirst(seller);
    }

    /**
     * An entity which offers (sells / leases / lends / loans) the services / goods.  A seller may also be a provider.
     *
     * @param seller Organization value to set.
     */
    @Override
    public void addSeller(Organization seller) {
        this.seller = add(this.seller, seller);
    }
    /**
     * An entity which offers (sells / leases / lends / loans) the services / goods.  A seller may also be a provider.
     *
     * @param seller Person value to set.
     */
    @Override
    public void addSeller(Person seller) {
        this.seller = add(this.seller, seller);
    }

    @JsonLdFieldTypes({ Number.class, Text.class })
    private List<Object> discount;

    /**
     * Any discount applied (to an Order).
     *
     * @return {@link Number} or {@link Text}
     */
    @Override
    public <T> List<T> getDiscountList() {
        return (List<T>) discount;
    }

    /**
     * Any discount applied (to an Order).
     *
     * @return {@link Number} or {@link Text}
     */
    @Override
    public <T> T getDiscount() {
        return (T) getFirst(discount);
    }

    /**
     * Any discount applied (to an Order).
     *
     * @param discount Number value to set.
     */
    @Override
    public void addDiscount(Number discount) {
        this.discount = add(this.discount, discount);
    }
    /**
     * Any discount applied (to an Order).
     *
     * @param discount Text value to set.
     */
    @Override
    public void addDiscount(Text discount) {
        this.discount = add(this.discount, discount);
    }

    private List<Text> discountCurrency;

    /**
     * The currency of the discount.<br/><br/>Use standard formats: [ISO 4217 currency format](http://en.wikipedia.org/wiki/ISO_4217), e.g. "USD"; [Ticker symbol](https://en.wikipedia.org/wiki/List_of_cryptocurrencies) for cryptocurrencies, e.g. "BTC"; well known names for [Local Exchange Trading Systems](https://en.wikipedia.org/wiki/Local_exchange_trading_system) (LETS) and other currency types, e.g. "Ithaca HOUR".
     *
     * @return {@link Text}
     */
    @Override
    public List<Text> getDiscountCurrencyList() {
        return discountCurrency;
    }

    /**
     * The currency of the discount.<br/><br/>Use standard formats: [ISO 4217 currency format](http://en.wikipedia.org/wiki/ISO_4217), e.g. "USD"; [Ticker symbol](https://en.wikipedia.org/wiki/List_of_cryptocurrencies) for cryptocurrencies, e.g. "BTC"; well known names for [Local Exchange Trading Systems](https://en.wikipedia.org/wiki/Local_exchange_trading_system) (LETS) and other currency types, e.g. "Ithaca HOUR".
     *
     * @return {@link Text}
     */
    @Override
    public Text getDiscountCurrency() {
        return getFirst(discountCurrency);
    }

    /**
     * The currency of the discount.<br/><br/>Use standard formats: [ISO 4217 currency format](http://en.wikipedia.org/wiki/ISO_4217), e.g. "USD"; [Ticker symbol](https://en.wikipedia.org/wiki/List_of_cryptocurrencies) for cryptocurrencies, e.g. "BTC"; well known names for [Local Exchange Trading Systems](https://en.wikipedia.org/wiki/Local_exchange_trading_system) (LETS) and other currency types, e.g. "Ithaca HOUR".
     *
     * @param discountCurrency Text value to set.
     */
    @Override
    public void addDiscountCurrency(Text discountCurrency) {
        this.discountCurrency = add(this.discountCurrency, discountCurrency);
    }

    @JsonLdFieldTypes({ Organization.class, Person.class })
    private List<Object> customer;

    /**
     * Party placing the order or paying the invoice.
     *
     * @return {@link Organization} or {@link Person}
     */
    @Override
    public <T> List<T> getCustomerList() {
        return (List<T>) customer;
    }

    /**
     * Party placing the order or paying the invoice.
     *
     * @return {@link Organization} or {@link Person}
     */
    @Override
    public <T> T getCustomer() {
        return (T) getFirst(customer);
    }

    /**
     * Party placing the order or paying the invoice.
     *
     * @param customer Organization value to set.
     */
    @Override
    public void addCustomer(Organization customer) {
        this.customer = add(this.customer, customer);
    }
    /**
     * Party placing the order or paying the invoice.
     *
     * @param customer Person value to set.
     */
    @Override
    public void addCustomer(Person customer) {
        this.customer = add(this.customer, customer);
    }

    private List<DateTime> paymentDue;

    /**
     * The date that payment is due.
     *
     * @return {@link DateTime}
     */
    @Override
    public List<DateTime> getPaymentDueList() {
        return paymentDue;
    }

    /**
     * The date that payment is due.
     *
     * @return {@link DateTime}
     */
    @Override
    public DateTime getPaymentDue() {
        return getFirst(paymentDue);
    }

    /**
     * The date that payment is due.
     *
     * @param paymentDue DateTime value to set.
     */
    @Override
    public void addPaymentDue(DateTime paymentDue) {
        this.paymentDue = add(this.paymentDue, paymentDue);
    }

    private List<Offer> acceptedOffer;

    /**
     * The offer(s) -- e.g., product, quantity and price combinations -- included in the order.
     *
     * @return {@link Offer}
     */
    @Override
    public List<Offer> getAcceptedOfferList() {
        return acceptedOffer;
    }

    /**
     * The offer(s) -- e.g., product, quantity and price combinations -- included in the order.
     *
     * @return {@link Offer}
     */
    @Override
    public Offer getAcceptedOffer() {
        return getFirst(acceptedOffer);
    }

    /**
     * The offer(s) -- e.g., product, quantity and price combinations -- included in the order.
     *
     * @param acceptedOffer Offer value to set.
     */
    @Override
    public void addAcceptedOffer(Offer acceptedOffer) {
        this.acceptedOffer = add(this.acceptedOffer, acceptedOffer);
    }

    private List<Text> paymentMethodId;

    /**
     * An identifier for the method of payment used (e.g. the last 4 digits of the credit card).
     *
     * @return {@link Text}
     */
    @Override
    public List<Text> getPaymentMethodIdList() {
        return paymentMethodId;
    }

    /**
     * An identifier for the method of payment used (e.g. the last 4 digits of the credit card).
     *
     * @return {@link Text}
     */
    @Override
    public Text getPaymentMethodId() {
        return getFirst(paymentMethodId);
    }

    /**
     * An identifier for the method of payment used (e.g. the last 4 digits of the credit card).
     *
     * @param paymentMethodId Text value to set.
     */
    @Override
    public void addPaymentMethodId(Text paymentMethodId) {
        this.paymentMethodId = add(this.paymentMethodId, paymentMethodId);
    }

    @JsonLdFieldTypes({ Organization.class, Person.class })
    private List<Object> merchant;

    /**
     * 'merchant' is an out-dated term for 'seller'.
     *
     * @return {@link Organization} or {@link Person}
     */
    @Override
    public <T> List<T> getMerchantList() {
        return (List<T>) merchant;
    }

    /**
     * 'merchant' is an out-dated term for 'seller'.
     *
     * @return {@link Organization} or {@link Person}
     */
    @Override
    public <T> T getMerchant() {
        return (T) getFirst(merchant);
    }

    /**
     * 'merchant' is an out-dated term for 'seller'.
     *
     * @param merchant Organization value to set.
     */
    @Override
    public void addMerchant(Organization merchant) {
        this.merchant = add(this.merchant, merchant);
    }
    /**
     * 'merchant' is an out-dated term for 'seller'.
     *
     * @param merchant Person value to set.
     */
    @Override
    public void addMerchant(Person merchant) {
        this.merchant = add(this.merchant, merchant);
    }

    private List<Invoice> partOfInvoice;

    /**
     * The order is being paid as part of the referenced Invoice.
     *
     * @return {@link Invoice}
     */
    @Override
    public List<Invoice> getPartOfInvoiceList() {
        return partOfInvoice;
    }

    /**
     * The order is being paid as part of the referenced Invoice.
     *
     * @return {@link Invoice}
     */
    @Override
    public Invoice getPartOfInvoice() {
        return getFirst(partOfInvoice);
    }

    /**
     * The order is being paid as part of the referenced Invoice.
     *
     * @param partOfInvoice Invoice value to set.
     */
    @Override
    public void addPartOfInvoice(Invoice partOfInvoice) {
        this.partOfInvoice = add(this.partOfInvoice, partOfInvoice);
    }

    private List<Text> orderNumber;

    /**
     * The identifier of the transaction.
     *
     * @return {@link Text}
     */
    @Override
    public List<Text> getOrderNumberList() {
        return orderNumber;
    }

    /**
     * The identifier of the transaction.
     *
     * @return {@link Text}
     */
    @Override
    public Text getOrderNumber() {
        return getFirst(orderNumber);
    }

    /**
     * The identifier of the transaction.
     *
     * @param orderNumber Text value to set.
     */
    @Override
    public void addOrderNumber(Text orderNumber) {
        this.orderNumber = add(this.orderNumber, orderNumber);
    }

    private List<PaymentMethod> paymentMethod;

    /**
     * The name of the credit card or other method of payment for the order.
     *
     * @return {@link PaymentMethod}
     */
    @Override
    public List<PaymentMethod> getPaymentMethodList() {
        return paymentMethod;
    }

    /**
     * The name of the credit card or other method of payment for the order.
     *
     * @return {@link PaymentMethod}
     */
    @Override
    public PaymentMethod getPaymentMethod() {
        return getFirst(paymentMethod);
    }

    /**
     * The name of the credit card or other method of payment for the order.
     *
     * @param paymentMethod PaymentMethod value to set.
     */
    @Override
    public void addPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = add(this.paymentMethod, paymentMethod);
    }

    private List<Text> discountCode;

    /**
     * Code used to redeem a discount.
     *
     * @return {@link Text}
     */
    @Override
    public List<Text> getDiscountCodeList() {
        return discountCode;
    }

    /**
     * Code used to redeem a discount.
     *
     * @return {@link Text}
     */
    @Override
    public Text getDiscountCode() {
        return getFirst(discountCode);
    }

    /**
     * Code used to redeem a discount.
     *
     * @param discountCode Text value to set.
     */
    @Override
    public void addDiscountCode(Text discountCode) {
        this.discountCode = add(this.discountCode, discountCode);
    }

    private List<ParcelDelivery> orderDelivery;

    /**
     * The delivery of the parcel related to this order or order item.
     *
     * @return {@link ParcelDelivery}
     */
    @Override
    public List<ParcelDelivery> getOrderDeliveryList() {
        return orderDelivery;
    }

    /**
     * The delivery of the parcel related to this order or order item.
     *
     * @return {@link ParcelDelivery}
     */
    @Override
    public ParcelDelivery getOrderDelivery() {
        return getFirst(orderDelivery);
    }

    /**
     * The delivery of the parcel related to this order or order item.
     *
     * @param orderDelivery ParcelDelivery value to set.
     */
    @Override
    public void addOrderDelivery(ParcelDelivery orderDelivery) {
        this.orderDelivery = add(this.orderDelivery, orderDelivery);
    }

    @JsonLdFieldTypes({ Product.class, Service.class, OrderItem.class })
    private List<Object> orderedItem;

    /**
     * The item ordered.
     *
     * @return {@link Product} or {@link Service} or {@link OrderItem}
     */
    @Override
    public <T> List<T> getOrderedItemList() {
        return (List<T>) orderedItem;
    }

    /**
     * The item ordered.
     *
     * @return {@link Product} or {@link Service} or {@link OrderItem}
     */
    @Override
    public <T> T getOrderedItem() {
        return (T) getFirst(orderedItem);
    }

    /**
     * The item ordered.
     *
     * @param orderedItem Product value to set.
     */
    @Override
    public void addOrderedItem(Product orderedItem) {
        this.orderedItem = add(this.orderedItem, orderedItem);
    }
    /**
     * The item ordered.
     *
     * @param orderedItem Service value to set.
     */
    @Override
    public void addOrderedItem(Service orderedItem) {
        this.orderedItem = add(this.orderedItem, orderedItem);
    }
    /**
     * The item ordered.
     *
     * @param orderedItem OrderItem value to set.
     */
    @Override
    public void addOrderedItem(OrderItem orderedItem) {
        this.orderedItem = add(this.orderedItem, orderedItem);
    }

    private List<PostalAddress> billingAddress;

    /**
     * The billing address for the order.
     *
     * @return {@link PostalAddress}
     */
    @Override
    public List<PostalAddress> getBillingAddressList() {
        return billingAddress;
    }

    /**
     * The billing address for the order.
     *
     * @return {@link PostalAddress}
     */
    @Override
    public PostalAddress getBillingAddress() {
        return getFirst(billingAddress);
    }

    /**
     * The billing address for the order.
     *
     * @param billingAddress PostalAddress value to set.
     */
    @Override
    public void addBillingAddress(PostalAddress billingAddress) {
        this.billingAddress = add(this.billingAddress, billingAddress);
    }

    private List<URL> paymentUrl;

    /**
     * The URL for sending a payment.
     *
     * @return {@link URL}
     */
    @Override
    public List<URL> getPaymentUrlList() {
        return paymentUrl;
    }

    /**
     * The URL for sending a payment.
     *
     * @return {@link URL}
     */
    @Override
    public URL getPaymentUrl() {
        return getFirst(paymentUrl);
    }

    /**
     * The URL for sending a payment.
     *
     * @param paymentUrl URL value to set.
     */
    @Override
    public void addPaymentUrl(URL paymentUrl) {
        this.paymentUrl = add(this.paymentUrl, paymentUrl);
    }

    @JsonLdFieldTypes({ DateTime.class, Date.class })
    private List<Object> orderDate;

    /**
     * Date order was placed.
     *
     * @return {@link DateTime} or {@link Date}
     */
    @Override
    public <T> List<T> getOrderDateList() {
        return (List<T>) orderDate;
    }

    /**
     * Date order was placed.
     *
     * @return {@link DateTime} or {@link Date}
     */
    @Override
    public <T> T getOrderDate() {
        return (T) getFirst(orderDate);
    }

    /**
     * Date order was placed.
     *
     * @param orderDate DateTime value to set.
     */
    @Override
    public void addOrderDate(DateTime orderDate) {
        this.orderDate = add(this.orderDate, orderDate);
    }
    /**
     * Date order was placed.
     *
     * @param orderDate Date value to set.
     */
    @Override
    public void addOrderDate(Date orderDate) {
        this.orderDate = add(this.orderDate, orderDate);
    }

    private List<Action> potentialAction;

    /**
     * Indicates a potential Action, which describes an idealized action in which this thing would play an 'object' role.
     *
     * @return {@link Action}
     */
    @Override
    public List<Action> getPotentialActionList() {
        return potentialAction;
    }

    /**
     * Indicates a potential Action, which describes an idealized action in which this thing would play an 'object' role.
     *
     * @return {@link Action}
     */
    @Override
    public Action getPotentialAction() {
        return getFirst(potentialAction);
    }

    /**
     * Indicates a potential Action, which describes an idealized action in which this thing would play an 'object' role.
     *
     * @param potentialAction Action value to set.
     */
    @Override
    public void addPotentialAction(Action potentialAction) {
        this.potentialAction = add(this.potentialAction, potentialAction);
    }

    @JsonLdFieldTypes({ URL.class, CreativeWork.class })
    private List<Object> mainEntityOfPage;

    /**
     * Indicates a page (or other CreativeWork) for which this thing is the main entity being described. See [background notes](/docs/datamodel.html#mainEntityBackground) for details.
     *
     * @return {@link URL} or {@link CreativeWork}
     */
    @Override
    public <T> List<T> getMainEntityOfPageList() {
        return (List<T>) mainEntityOfPage;
    }

    /**
     * Indicates a page (or other CreativeWork) for which this thing is the main entity being described. See [background notes](/docs/datamodel.html#mainEntityBackground) for details.
     *
     * @return {@link URL} or {@link CreativeWork}
     */
    @Override
    public <T> T getMainEntityOfPage() {
        return (T) getFirst(mainEntityOfPage);
    }

    /**
     * Indicates a page (or other CreativeWork) for which this thing is the main entity being described. See [background notes](/docs/datamodel.html#mainEntityBackground) for details.
     *
     * @param mainEntityOfPage URL value to set.
     */
    @Override
    public void addMainEntityOfPage(URL mainEntityOfPage) {
        this.mainEntityOfPage = add(this.mainEntityOfPage, mainEntityOfPage);
    }
    /**
     * Indicates a page (or other CreativeWork) for which this thing is the main entity being described. See [background notes](/docs/datamodel.html#mainEntityBackground) for details.
     *
     * @param mainEntityOfPage CreativeWork value to set.
     */
    @Override
    public void addMainEntityOfPage(CreativeWork mainEntityOfPage) {
        this.mainEntityOfPage = add(this.mainEntityOfPage, mainEntityOfPage);
    }

    @JsonLdFieldTypes({ Event.class, CreativeWork.class })
    private List<Object> subjectOf;

    /**
     * A CreativeWork or Event about this Thing.
     *
     * @return {@link Event} or {@link CreativeWork}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1670">https://github.com/schemaorg/schemaorg/issues/1670</a>
     */
    @Override
    public <T> List<T> getSubjectOfList() {
        return (List<T>) subjectOf;
    }

    /**
     * A CreativeWork or Event about this Thing.
     *
     * @return {@link Event} or {@link CreativeWork}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1670">https://github.com/schemaorg/schemaorg/issues/1670</a>
     */
    @Override
    public <T> T getSubjectOf() {
        return (T) getFirst(subjectOf);
    }

    /**
     * A CreativeWork or Event about this Thing.
     *
     * @param subjectOf Event value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1670">https://github.com/schemaorg/schemaorg/issues/1670</a>
     */
    @Override
    public void addSubjectOf(Event subjectOf) {
        this.subjectOf = add(this.subjectOf, subjectOf);
    }
    /**
     * A CreativeWork or Event about this Thing.
     *
     * @param subjectOf CreativeWork value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1670">https://github.com/schemaorg/schemaorg/issues/1670</a>
     */
    @Override
    public void addSubjectOf(CreativeWork subjectOf) {
        this.subjectOf = add(this.subjectOf, subjectOf);
    }

    private List<URL> url;

    /**
     * URL of the item.
     *
     * @return {@link URL}
     */
    @Override
    public List<URL> getUrlList() {
        return url;
    }

    /**
     * URL of the item.
     *
     * @return {@link URL}
     */
    @Override
    public URL getUrl() {
        return getFirst(url);
    }

    /**
     * URL of the item.
     *
     * @param url URL value to set.
     */
    @Override
    public void addUrl(URL url) {
        this.url = add(this.url, url);
    }

    private List<Text> alternateName;

    /**
     * An alias for the item.
     *
     * @return {@link Text}
     */
    @Override
    public List<Text> getAlternateNameList() {
        return alternateName;
    }

    /**
     * An alias for the item.
     *
     * @return {@link Text}
     */
    @Override
    public Text getAlternateName() {
        return getFirst(alternateName);
    }

    /**
     * An alias for the item.
     *
     * @param alternateName Text value to set.
     */
    @Override
    public void addAlternateName(Text alternateName) {
        this.alternateName = add(this.alternateName, alternateName);
    }

    private List<URL> sameAs;

    /**
     * URL of a reference Web page that unambiguously indicates the item's identity. E.g. the URL of the item's Wikipedia page, Wikidata entry, or official website.
     *
     * @return {@link URL}
     */
    @Override
    public List<URL> getSameAsList() {
        return sameAs;
    }

    /**
     * URL of a reference Web page that unambiguously indicates the item's identity. E.g. the URL of the item's Wikipedia page, Wikidata entry, or official website.
     *
     * @return {@link URL}
     */
    @Override
    public URL getSameAs() {
        return getFirst(sameAs);
    }

    /**
     * URL of a reference Web page that unambiguously indicates the item's identity. E.g. the URL of the item's Wikipedia page, Wikidata entry, or official website.
     *
     * @param sameAs URL value to set.
     */
    @Override
    public void addSameAs(URL sameAs) {
        this.sameAs = add(this.sameAs, sameAs);
    }

    private List<Text> description;

    /**
     * A description of the item.
     *
     * @return {@link Text}
     */
    @Override
    public List<Text> getDescriptionList() {
        return description;
    }

    /**
     * A description of the item.
     *
     * @return {@link Text}
     */
    @Override
    public Text getDescription() {
        return getFirst(description);
    }

    /**
     * A description of the item.
     *
     * @param description Text value to set.
     */
    @Override
    public void addDescription(Text description) {
        this.description = add(this.description, description);
    }

    private List<Text> disambiguatingDescription;

    /**
     * A sub property of description. A short description of the item used to disambiguate from other, similar items. Information from other properties (in particular, name) may be necessary for the description to be useful for disambiguation.
     *
     * @return {@link Text}
     */
    @Override
    public List<Text> getDisambiguatingDescriptionList() {
        return disambiguatingDescription;
    }

    /**
     * A sub property of description. A short description of the item used to disambiguate from other, similar items. Information from other properties (in particular, name) may be necessary for the description to be useful for disambiguation.
     *
     * @return {@link Text}
     */
    @Override
    public Text getDisambiguatingDescription() {
        return getFirst(disambiguatingDescription);
    }

    /**
     * A sub property of description. A short description of the item used to disambiguate from other, similar items. Information from other properties (in particular, name) may be necessary for the description to be useful for disambiguation.
     *
     * @param disambiguatingDescription Text value to set.
     */
    @Override
    public void addDisambiguatingDescription(Text disambiguatingDescription) {
        this.disambiguatingDescription = add(this.disambiguatingDescription, disambiguatingDescription);
    }

    @JsonLdFieldTypes({ PropertyValue.class, URL.class, Text.class })
    private List<Object> identifier;

    /**
     * The identifier property represents any kind of identifier for any kind of [[Thing]], such as ISBNs, GTIN codes, UUIDs etc. Schema.org provides dedicated properties for representing many of these, either as textual strings or as URL (URI) links. See [background notes](/docs/datamodel.html#identifierBg) for more details.
     *         
     *
     * @return {@link PropertyValue} or {@link URL} or {@link Text}
     */
    @Override
    public <T> List<T> getIdentifierList() {
        return (List<T>) identifier;
    }

    /**
     * The identifier property represents any kind of identifier for any kind of [[Thing]], such as ISBNs, GTIN codes, UUIDs etc. Schema.org provides dedicated properties for representing many of these, either as textual strings or as URL (URI) links. See [background notes](/docs/datamodel.html#identifierBg) for more details.
     *         
     *
     * @return {@link PropertyValue} or {@link URL} or {@link Text}
     */
    @Override
    public <T> T getIdentifier() {
        return (T) getFirst(identifier);
    }

    /**
     * The identifier property represents any kind of identifier for any kind of [[Thing]], such as ISBNs, GTIN codes, UUIDs etc. Schema.org provides dedicated properties for representing many of these, either as textual strings or as URL (URI) links. See [background notes](/docs/datamodel.html#identifierBg) for more details.
     *         
     *
     * @param identifier PropertyValue value to set.
     */
    @Override
    public void addIdentifier(PropertyValue identifier) {
        this.identifier = add(this.identifier, identifier);
    }
    /**
     * The identifier property represents any kind of identifier for any kind of [[Thing]], such as ISBNs, GTIN codes, UUIDs etc. Schema.org provides dedicated properties for representing many of these, either as textual strings or as URL (URI) links. See [background notes](/docs/datamodel.html#identifierBg) for more details.
     *         
     *
     * @param identifier URL value to set.
     */
    @Override
    public void addIdentifier(URL identifier) {
        this.identifier = add(this.identifier, identifier);
    }
    /**
     * The identifier property represents any kind of identifier for any kind of [[Thing]], such as ISBNs, GTIN codes, UUIDs etc. Schema.org provides dedicated properties for representing many of these, either as textual strings or as URL (URI) links. See [background notes](/docs/datamodel.html#identifierBg) for more details.
     *         
     *
     * @param identifier Text value to set.
     */
    @Override
    public void addIdentifier(Text identifier) {
        this.identifier = add(this.identifier, identifier);
    }

    @JsonLdFieldTypes({ URL.class, ImageObject.class })
    private List<Object> image;

    /**
     * An image of the item. This can be a [[URL]] or a fully described [[ImageObject]].
     *
     * @return {@link URL} or {@link ImageObject}
     */
    @Override
    public <T> List<T> getImageList() {
        return (List<T>) image;
    }

    /**
     * An image of the item. This can be a [[URL]] or a fully described [[ImageObject]].
     *
     * @return {@link URL} or {@link ImageObject}
     */
    @Override
    public <T> T getImage() {
        return (T) getFirst(image);
    }

    /**
     * An image of the item. This can be a [[URL]] or a fully described [[ImageObject]].
     *
     * @param image URL value to set.
     */
    @Override
    public void addImage(URL image) {
        this.image = add(this.image, image);
    }
    /**
     * An image of the item. This can be a [[URL]] or a fully described [[ImageObject]].
     *
     * @param image ImageObject value to set.
     */
    @Override
    public void addImage(ImageObject image) {
        this.image = add(this.image, image);
    }

    private List<Text> name;

    /**
     * The name of the item.
     *
     * @return {@link Text}
     */
    @Override
    public List<Text> getNameList() {
        return name;
    }

    /**
     * The name of the item.
     *
     * @return {@link Text}
     */
    @Override
    public Text getName() {
        return getFirst(name);
    }

    /**
     * The name of the item.
     *
     * @param name Text value to set.
     */
    @Override
    public void addName(Text name) {
        this.name = add(this.name, name);
    }

    private List<URL> additionalType;

    /**
     * An additional type for the item, typically used for adding more specific types from external vocabularies in microdata syntax. This is a relationship between something and a class that the thing is in. In RDFa syntax, it is better to use the native RDFa syntax - the 'typeof' attribute - for multiple types. Schema.org tools may have only weaker understanding of extra types, in particular those defined externally.
     *
     * @return {@link URL}
     */
    @Override
    public List<URL> getAdditionalTypeList() {
        return additionalType;
    }

    /**
     * An additional type for the item, typically used for adding more specific types from external vocabularies in microdata syntax. This is a relationship between something and a class that the thing is in. In RDFa syntax, it is better to use the native RDFa syntax - the 'typeof' attribute - for multiple types. Schema.org tools may have only weaker understanding of extra types, in particular those defined externally.
     *
     * @return {@link URL}
     */
    @Override
    public URL getAdditionalType() {
        return getFirst(additionalType);
    }

    /**
     * An additional type for the item, typically used for adding more specific types from external vocabularies in microdata syntax. This is a relationship between something and a class that the thing is in. In RDFa syntax, it is better to use the native RDFa syntax - the 'typeof' attribute - for multiple types. Schema.org tools may have only weaker understanding of extra types, in particular those defined externally.
     *
     * @param additionalType URL value to set.
     */
    @Override
    public void addAdditionalType(URL additionalType) {
        this.additionalType = add(this.additionalType, additionalType);
    }
}
