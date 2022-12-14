/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model.impl;

import org.schema.model.datatype.Number;
import org.schema.model.datatype.Boolean;
import org.schema.model.MonetaryAmount;
import org.schema.model.Enumeration;
import org.schema.model.Class;
import org.schema.model.Property;
import org.schema.model.CreativeWork;
import org.schema.model.datatype.URL;
import org.schema.model.datatype.Text;
import org.schema.model.Action;
import org.schema.model.ImageObject;
import org.schema.model.Event;
import org.schema.model.PropertyValue;
import org.schema.model.Thing;
import com.weedow.schemaorg.commons.model.JsonLdTypeName;
import org.schema.model.Intangible;
import org.schema.model.PaymentMethod;
import org.schema.model.QuantitativeValue;
import org.schema.model.Review;
import org.schema.model.Audience;
import org.schema.model.PhysicalActivityCategory;
import org.schema.model.CategoryCode;
import org.schema.model.OpeningHoursSpecification;
import org.schema.model.Offer;
import org.schema.model.Demand;
import org.schema.model.Organization;
import org.schema.model.Person;
import org.schema.model.AggregateRating;
import org.schema.model.Service;
import org.schema.model.Product;
import org.schema.model.ServiceChannel;
import org.schema.model.Brand;
import org.schema.model.GeoShape;
import org.schema.model.AdministrativeArea;
import org.schema.model.Place;
import org.schema.model.GovernmentBenefitsType;
import org.schema.model.OfferCatalog;
import org.schema.model.FinancialProduct;
import org.schema.model.PaymentCard;
import com.weedow.schemaorg.commons.model.JsonLdSubTypes;

/**
 * A payment method using a credit, debit, store or other card to associate the payment with an account.
 *
 * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#FIBO">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#FIBO</a>
 * @see <a href="https://schema.org/PaymentCard">https://schema.org/PaymentCard</a>
 */
@JsonLdTypeName("PaymentCard")
@JsonLdSubTypes({ "CreditCardEnum" })
public enum PaymentCardEnum implements PaymentCard {
    ;

    private final String enumValue;

    PaymentCardEnum(String enumValue) {
        this.enumValue = enumValue;
    }

    public static PaymentCardEnum from(String value) {
        for (PaymentCardEnum item : values()) {
            if (item.enumValue.equals(value)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return enumValue;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public  java.lang.String getContext() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public  java.lang.String getId() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public  java.lang.String getType() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public  void setContext(java.lang.String arg0) {
        throw new java.lang.IllegalAccessError("Method 'setContext' not allowed for enum PaymentCardEnum");
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public  void setId(java.lang.String arg0) {
        throw new java.lang.IllegalAccessError("Method 'setId' not allowed for enum PaymentCardEnum");
    }


    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getCashBack() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setCashBack(Number cashBack) {
        throw new java.lang.IllegalAccessError("Unable to set property 'cashBack': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setCashBack(Boolean cashBack) {
        throw new java.lang.IllegalAccessError("Unable to set property 'cashBack': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getMonthlyMinimumRepaymentAmount() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setMonthlyMinimumRepaymentAmount(MonetaryAmount monthlyMinimumRepaymentAmount) {
        throw new java.lang.IllegalAccessError("Unable to set property 'monthlyMinimumRepaymentAmount': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setMonthlyMinimumRepaymentAmount(Number monthlyMinimumRepaymentAmount) {
        throw new java.lang.IllegalAccessError("Unable to set property 'monthlyMinimumRepaymentAmount': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public MonetaryAmount getFloorLimit() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setFloorLimit(MonetaryAmount floorLimit) {
        throw new java.lang.IllegalAccessError("Unable to set property 'floorLimit': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Boolean getContactlessPayment() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setContactlessPayment(Boolean contactlessPayment) {
        throw new java.lang.IllegalAccessError("Unable to set property 'contactlessPayment': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getSupersededBy() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setSupersededBy(Enumeration supersededBy) {
        throw new java.lang.IllegalAccessError("Unable to set property 'supersededBy': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setSupersededBy(Class supersededBy) {
        throw new java.lang.IllegalAccessError("Unable to set property 'supersededBy': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setSupersededBy(Property supersededBy) {
        throw new java.lang.IllegalAccessError("Unable to set property 'supersededBy': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getMainEntityOfPage() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setMainEntityOfPage(CreativeWork mainEntityOfPage) {
        throw new java.lang.IllegalAccessError("Unable to set property 'mainEntityOfPage': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setMainEntityOfPage(URL mainEntityOfPage) {
        throw new java.lang.IllegalAccessError("Unable to set property 'mainEntityOfPage': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Text getAlternateName() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setAlternateName(Text alternateName) {
        throw new java.lang.IllegalAccessError("Unable to set property 'alternateName': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Text getName() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setName(Text name) {
        throw new java.lang.IllegalAccessError("Unable to set property 'name': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Action getPotentialAction() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setPotentialAction(Action potentialAction) {
        throw new java.lang.IllegalAccessError("Unable to set property 'potentialAction': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getImage() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setImage(URL image) {
        throw new java.lang.IllegalAccessError("Unable to set property 'image': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setImage(ImageObject image) {
        throw new java.lang.IllegalAccessError("Unable to set property 'image': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public URL getUrl() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setUrl(URL url) {
        throw new java.lang.IllegalAccessError("Unable to set property 'url': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Text getDescription() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setDescription(Text description) {
        throw new java.lang.IllegalAccessError("Unable to set property 'description': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getSubjectOf() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setSubjectOf(Event subjectOf) {
        throw new java.lang.IllegalAccessError("Unable to set property 'subjectOf': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setSubjectOf(CreativeWork subjectOf) {
        throw new java.lang.IllegalAccessError("Unable to set property 'subjectOf': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public URL getAdditionalType() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setAdditionalType(URL additionalType) {
        throw new java.lang.IllegalAccessError("Unable to set property 'additionalType': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Text getDisambiguatingDescription() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setDisambiguatingDescription(Text disambiguatingDescription) {
        throw new java.lang.IllegalAccessError("Unable to set property 'disambiguatingDescription': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public URL getSameAs() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setSameAs(URL sameAs) {
        throw new java.lang.IllegalAccessError("Unable to set property 'sameAs': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getIdentifier() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setIdentifier(URL identifier) {
        throw new java.lang.IllegalAccessError("Unable to set property 'identifier': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setIdentifier(Text identifier) {
        throw new java.lang.IllegalAccessError("Unable to set property 'identifier': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setIdentifier(PropertyValue identifier) {
        throw new java.lang.IllegalAccessError("Unable to set property 'identifier': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getAnnualPercentageRate() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setAnnualPercentageRate(Number annualPercentageRate) {
        throw new java.lang.IllegalAccessError("Unable to set property 'annualPercentageRate': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setAnnualPercentageRate(QuantitativeValue annualPercentageRate) {
        throw new java.lang.IllegalAccessError("Unable to set property 'annualPercentageRate': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getFeesAndCommissionsSpecification() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setFeesAndCommissionsSpecification(URL feesAndCommissionsSpecification) {
        throw new java.lang.IllegalAccessError("Unable to set property 'feesAndCommissionsSpecification': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setFeesAndCommissionsSpecification(Text feesAndCommissionsSpecification) {
        throw new java.lang.IllegalAccessError("Unable to set property 'feesAndCommissionsSpecification': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getInterestRate() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setInterestRate(Number interestRate) {
        throw new java.lang.IllegalAccessError("Unable to set property 'interestRate': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setInterestRate(QuantitativeValue interestRate) {
        throw new java.lang.IllegalAccessError("Unable to set property 'interestRate': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Review getReview() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setReview(Review review) {
        throw new java.lang.IllegalAccessError("Unable to set property 'review': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Text getAward() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setAward(Text award) {
        throw new java.lang.IllegalAccessError("Unable to set property 'award': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Audience getServiceAudience() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setServiceAudience(Audience serviceAudience) {
        throw new java.lang.IllegalAccessError("Unable to set property 'serviceAudience': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getCategory() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setCategory(URL category) {
        throw new java.lang.IllegalAccessError("Unable to set property 'category': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setCategory(Text category) {
        throw new java.lang.IllegalAccessError("Unable to set property 'category': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setCategory(PhysicalActivityCategory category) {
        throw new java.lang.IllegalAccessError("Unable to set property 'category': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setCategory(Thing category) {
        throw new java.lang.IllegalAccessError("Unable to set property 'category': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setCategory(CategoryCode category) {
        throw new java.lang.IllegalAccessError("Unable to set property 'category': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public OpeningHoursSpecification getHoursAvailable() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setHoursAvailable(OpeningHoursSpecification hoursAvailable) {
        throw new java.lang.IllegalAccessError("Unable to set property 'hoursAvailable': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Audience getAudience() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setAudience(Audience audience) {
        throw new java.lang.IllegalAccessError("Unable to set property 'audience': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getOffers() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setOffers(Offer offers) {
        throw new java.lang.IllegalAccessError("Unable to set property 'offers': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setOffers(Demand offers) {
        throw new java.lang.IllegalAccessError("Unable to set property 'offers': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Thing getServiceOutput() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setServiceOutput(Thing serviceOutput) {
        throw new java.lang.IllegalAccessError("Unable to set property 'serviceOutput': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getProvider() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setProvider(Organization provider) {
        throw new java.lang.IllegalAccessError("Unable to set property 'provider': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setProvider(Person provider) {
        throw new java.lang.IllegalAccessError("Unable to set property 'provider': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getTermsOfService() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setTermsOfService(URL termsOfService) {
        throw new java.lang.IllegalAccessError("Unable to set property 'termsOfService': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setTermsOfService(Text termsOfService) {
        throw new java.lang.IllegalAccessError("Unable to set property 'termsOfService': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Text getProviderMobility() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setProviderMobility(Text providerMobility) {
        throw new java.lang.IllegalAccessError("Unable to set property 'providerMobility': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getBroker() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setBroker(Person broker) {
        throw new java.lang.IllegalAccessError("Unable to set property 'broker': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setBroker(Organization broker) {
        throw new java.lang.IllegalAccessError("Unable to set property 'broker': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public AggregateRating getAggregateRating() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setAggregateRating(AggregateRating aggregateRating) {
        throw new java.lang.IllegalAccessError("Unable to set property 'aggregateRating': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getIsSimilarTo() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setIsSimilarTo(Service isSimilarTo) {
        throw new java.lang.IllegalAccessError("Unable to set property 'isSimilarTo': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setIsSimilarTo(Product isSimilarTo) {
        throw new java.lang.IllegalAccessError("Unable to set property 'isSimilarTo': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public ServiceChannel getAvailableChannel() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setAvailableChannel(ServiceChannel availableChannel) {
        throw new java.lang.IllegalAccessError("Unable to set property 'availableChannel': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Text getSlogan() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setSlogan(Text slogan) {
        throw new java.lang.IllegalAccessError("Unable to set property 'slogan': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getBrand() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setBrand(Organization brand) {
        throw new java.lang.IllegalAccessError("Unable to set property 'brand': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setBrand(Brand brand) {
        throw new java.lang.IllegalAccessError("Unable to set property 'brand': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getLogo() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setLogo(URL logo) {
        throw new java.lang.IllegalAccessError("Unable to set property 'logo': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setLogo(ImageObject logo) {
        throw new java.lang.IllegalAccessError("Unable to set property 'logo': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Thing getProduces() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setProduces(Thing produces) {
        throw new java.lang.IllegalAccessError("Unable to set property 'produces': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getServiceArea() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setServiceArea(GeoShape serviceArea) {
        throw new java.lang.IllegalAccessError("Unable to set property 'serviceArea': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setServiceArea(AdministrativeArea serviceArea) {
        throw new java.lang.IllegalAccessError("Unable to set property 'serviceArea': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setServiceArea(Place serviceArea) {
        throw new java.lang.IllegalAccessError("Unable to set property 'serviceArea': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getServiceType() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setServiceType(GovernmentBenefitsType serviceType) {
        throw new java.lang.IllegalAccessError("Unable to set property 'serviceType': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setServiceType(Text serviceType) {
        throw new java.lang.IllegalAccessError("Unable to set property 'serviceType': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getAreaServed() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setAreaServed(AdministrativeArea areaServed) {
        throw new java.lang.IllegalAccessError("Unable to set property 'areaServed': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setAreaServed(GeoShape areaServed) {
        throw new java.lang.IllegalAccessError("Unable to set property 'areaServed': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setAreaServed(Text areaServed) {
        throw new java.lang.IllegalAccessError("Unable to set property 'areaServed': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setAreaServed(Place areaServed) {
        throw new java.lang.IllegalAccessError("Unable to set property 'areaServed': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getIsRelatedTo() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setIsRelatedTo(Service isRelatedTo) {
        throw new java.lang.IllegalAccessError("Unable to set property 'isRelatedTo': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setIsRelatedTo(Product isRelatedTo) {
        throw new java.lang.IllegalAccessError("Unable to set property 'isRelatedTo': method not allowed for enum Enum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public OfferCatalog getHasOfferCatalog() {
        return null;
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void setHasOfferCatalog(OfferCatalog hasOfferCatalog) {
        throw new java.lang.IllegalAccessError("Unable to set property 'hasOfferCatalog': method not allowed for enum Enum");
    }
}
