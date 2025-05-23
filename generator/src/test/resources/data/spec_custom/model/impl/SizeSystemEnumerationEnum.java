/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model.impl;

import spec_custom.model.Class;
import spec_custom.model.Property;
import spec_custom.model.Enumeration;
import spec_custom.model.Action;
import spec_custom.model.datatype.URL;
import spec_custom.model.CreativeWork;
import spec_custom.model.Event;
import spec_custom.model.datatype.Text;
import spec_custom.model.PropertyValue;
import spec_custom.model.ImageObject;
import spec_custom.model.Thing;
import com.weedow.schemaorg.commons.model.JsonLdTypeName;
import com.weedow.schemaorg.commons.model.JsonLdFieldTypes;
import java.util.List;
import spec_custom.model.Intangible;
import spec_custom.model.SizeSystemEnumeration;
import com.weedow.schemaorg.commons.model.JsonLdSubTypes;

/**
 * Enumerates common size systems for different categories of products, for example "EN-13402" or "UK" for wearables or "Imperial" for screws.
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/2811">https://github.com/schemaorg/schemaorg/issues/2811</a>
 * @see <a href="https://schema.org/SizeSystemEnumeration">https://schema.org/SizeSystemEnumeration</a>
 */
@JsonLdTypeName("SizeSystemEnumeration")
@JsonLdSubTypes({ "WearableSizeSystemEnumerationEnum" })
public enum SizeSystemEnumerationEnum implements SizeSystemEnumeration {
    SIZE_SYSTEM_METRIC("SizeSystemMetric"),
    SIZE_SYSTEM_IMPERIAL("SizeSystemImperial")
    ;

    private final String enumValue;

    SizeSystemEnumerationEnum(String enumValue) {
        this.enumValue = enumValue;
    }

    public static SizeSystemEnumerationEnum from(String value) {
        for (SizeSystemEnumerationEnum item : values()) {
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
        throw new java.lang.IllegalAccessError("Method 'setContext' not allowed for enum SizeSystemEnumerationEnum");
    }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public  void setId(java.lang.String arg0) {
        throw new java.lang.IllegalAccessError("Method 'setId' not allowed for enum SizeSystemEnumerationEnum");
    }


    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> List<T> getSupersededByList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getSupersededBy() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addSupersededBy(Class supersededBy) {
        throw new java.lang.IllegalAccessError("Unable to set property 'supersededBy': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addSupersededBy(Property supersededBy) {
        throw new java.lang.IllegalAccessError("Unable to set property 'supersededBy': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addSupersededBy(Enumeration supersededBy) {
        throw new java.lang.IllegalAccessError("Unable to set property 'supersededBy': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public List<Action> getPotentialActionList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Action getPotentialAction() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addPotentialAction(Action potentialAction) {
        throw new java.lang.IllegalAccessError("Unable to set property 'potentialAction': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> List<T> getMainEntityOfPageList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getMainEntityOfPage() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addMainEntityOfPage(URL mainEntityOfPage) {
        throw new java.lang.IllegalAccessError("Unable to set property 'mainEntityOfPage': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addMainEntityOfPage(CreativeWork mainEntityOfPage) {
        throw new java.lang.IllegalAccessError("Unable to set property 'mainEntityOfPage': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> List<T> getSubjectOfList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getSubjectOf() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addSubjectOf(Event subjectOf) {
        throw new java.lang.IllegalAccessError("Unable to set property 'subjectOf': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addSubjectOf(CreativeWork subjectOf) {
        throw new java.lang.IllegalAccessError("Unable to set property 'subjectOf': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public List<URL> getUrlList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public URL getUrl() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addUrl(URL url) {
        throw new java.lang.IllegalAccessError("Unable to set property 'url': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public List<Text> getAlternateNameList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Text getAlternateName() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addAlternateName(Text alternateName) {
        throw new java.lang.IllegalAccessError("Unable to set property 'alternateName': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public List<URL> getSameAsList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public URL getSameAs() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addSameAs(URL sameAs) {
        throw new java.lang.IllegalAccessError("Unable to set property 'sameAs': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public List<Text> getDescriptionList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Text getDescription() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addDescription(Text description) {
        throw new java.lang.IllegalAccessError("Unable to set property 'description': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public List<Text> getDisambiguatingDescriptionList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Text getDisambiguatingDescription() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addDisambiguatingDescription(Text disambiguatingDescription) {
        throw new java.lang.IllegalAccessError("Unable to set property 'disambiguatingDescription': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> List<T> getIdentifierList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getIdentifier() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addIdentifier(PropertyValue identifier) {
        throw new java.lang.IllegalAccessError("Unable to set property 'identifier': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addIdentifier(URL identifier) {
        throw new java.lang.IllegalAccessError("Unable to set property 'identifier': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addIdentifier(Text identifier) {
        throw new java.lang.IllegalAccessError("Unable to set property 'identifier': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> List<T> getImageList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public <T> T getImage() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addImage(URL image) {
        throw new java.lang.IllegalAccessError("Unable to set property 'image': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addImage(ImageObject image) {
        throw new java.lang.IllegalAccessError("Unable to set property 'image': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public List<Text> getNameList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public Text getName() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addName(Text name) {
        throw new java.lang.IllegalAccessError("Unable to set property 'name': method not allowed for enum SizeSystemEnumerationEnum");
    }
    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public List<URL> getAdditionalTypeList() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public URL getAdditionalType() { return null; }

    /** {@inheritDoc} <br/><b>DO NOT USE THIS METHOD</b> */
    @Override
    public void addAdditionalType(URL additionalType) {
        throw new java.lang.IllegalAccessError("Unable to set property 'additionalType': method not allowed for enum SizeSystemEnumerationEnum");
    }
}
