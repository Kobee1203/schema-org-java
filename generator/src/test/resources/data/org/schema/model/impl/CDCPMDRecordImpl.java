/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model.impl;

import org.schema.model.datatype.Number;
import org.schema.model.datatype.Text;
import org.schema.model.datatype.DateTime;
import org.schema.model.datatype.Date;
import org.schema.model.CreativeWork;
import org.schema.model.datatype.URL;
import org.schema.model.Action;
import org.schema.model.ImageObject;
import org.schema.model.Event;
import org.schema.model.PropertyValue;
import org.schema.model.Thing;
import com.weedow.schemaorg.commons.model.JsonLdTypeName;
import org.schema.model.Intangible;
import org.schema.model.StructuredValue;
import org.schema.model.CDCPMDRecord;

/**
 * A CDCPMDRecord is a data structure representing a record in a CDC tabular data format
 *       used for hospital data reporting. See [documentation](/docs/cdc-covid.html) for details, and the linked CDC materials for authoritative
 *       definitions used as the source here.
 *       
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
 * @see <a href="https://schema.org/CDCPMDRecord">https://schema.org/CDCPMDRecord</a>
 */
@JsonLdTypeName("CDCPMDRecord")
public class CDCPMDRecordImpl extends com.weedow.schemaorg.commons.model.JsonLdNodeImpl implements CDCPMDRecord {

    private Number cvdNumBedsOcc;

    /**
     * numbedsocc - HOSPITAL INPATIENT BED OCCUPANCY: Total number of staffed inpatient beds that are occupied.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumBedsOcc() {
        return cvdNumBedsOcc;
    }

    /**
     * numbedsocc - HOSPITAL INPATIENT BED OCCUPANCY: Total number of staffed inpatient beds that are occupied.
     *
     * @param cvdNumBedsOcc Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumBedsOcc(Number cvdNumBedsOcc) {
        this.cvdNumBedsOcc = cvdNumBedsOcc;
    }

    private Number cvdNumICUBedsOcc;

    /**
     * numicubedsocc - ICU BED OCCUPANCY: Total number of staffed inpatient ICU beds that are occupied.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumICUBedsOcc() {
        return cvdNumICUBedsOcc;
    }

    /**
     * numicubedsocc - ICU BED OCCUPANCY: Total number of staffed inpatient ICU beds that are occupied.
     *
     * @param cvdNumICUBedsOcc Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumICUBedsOcc(Number cvdNumICUBedsOcc) {
        this.cvdNumICUBedsOcc = cvdNumICUBedsOcc;
    }

    private Number cvdNumVent;

    /**
     * numvent - MECHANICAL VENTILATORS: Total number of ventilators available.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumVent() {
        return cvdNumVent;
    }

    /**
     * numvent - MECHANICAL VENTILATORS: Total number of ventilators available.
     *
     * @param cvdNumVent Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumVent(Number cvdNumVent) {
        this.cvdNumVent = cvdNumVent;
    }

    private Number cvdNumC19Died;

    /**
     * numc19died - DEATHS: Patients with suspected or confirmed COVID-19 who died in the hospital, ED, or any overflow location.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumC19Died() {
        return cvdNumC19Died;
    }

    /**
     * numc19died - DEATHS: Patients with suspected or confirmed COVID-19 who died in the hospital, ED, or any overflow location.
     *
     * @param cvdNumC19Died Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumC19Died(Number cvdNumC19Died) {
        this.cvdNumC19Died = cvdNumC19Died;
    }

    private Number cvdNumTotBeds;

    /**
     * numtotbeds - ALL HOSPITAL BEDS: Total number of all Inpatient and outpatient beds, including all staffed,ICU, licensed, and overflow (surge) beds used for inpatients or outpatients.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumTotBeds() {
        return cvdNumTotBeds;
    }

    /**
     * numtotbeds - ALL HOSPITAL BEDS: Total number of all Inpatient and outpatient beds, including all staffed,ICU, licensed, and overflow (surge) beds used for inpatients or outpatients.
     *
     * @param cvdNumTotBeds Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumTotBeds(Number cvdNumTotBeds) {
        this.cvdNumTotBeds = cvdNumTotBeds;
    }

    private Text cvdFacilityCounty;

    /**
     * Name of the County of the NHSN facility that this data record applies to. Use [[cvdFacilityId]] to identify the facility. To provide other details, [[healthcareReportingData]] can be used on a [[Hospital]] entry.
     *
     * @return {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Text getCvdFacilityCounty() {
        return cvdFacilityCounty;
    }

    /**
     * Name of the County of the NHSN facility that this data record applies to. Use [[cvdFacilityId]] to identify the facility. To provide other details, [[healthcareReportingData]] can be used on a [[Hospital]] entry.
     *
     * @param cvdFacilityCounty Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdFacilityCounty(Text cvdFacilityCounty) {
        this.cvdFacilityCounty = cvdFacilityCounty;
    }

    private Number cvdNumICUBeds;

    /**
     * numicubeds - ICU BEDS: Total number of staffed inpatient intensive care unit (ICU) beds.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumICUBeds() {
        return cvdNumICUBeds;
    }

    /**
     * numicubeds - ICU BEDS: Total number of staffed inpatient intensive care unit (ICU) beds.
     *
     * @param cvdNumICUBeds Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumICUBeds(Number cvdNumICUBeds) {
        this.cvdNumICUBeds = cvdNumICUBeds;
    }

    private Object cvdCollectionDate;

    /**
     * collectiondate - Date for which patient counts are reported.
     *
     * @return {@link DateTime} or {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public <T> T getCvdCollectionDate() {
        return (T) cvdCollectionDate;
    }

    /**
     * collectiondate - Date for which patient counts are reported.
     *
     * @param cvdCollectionDate DateTime value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdCollectionDate(DateTime cvdCollectionDate) {
        this.cvdCollectionDate = cvdCollectionDate;
    }
    /**
     * collectiondate - Date for which patient counts are reported.
     *
     * @param cvdCollectionDate Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdCollectionDate(Text cvdCollectionDate) {
        this.cvdCollectionDate = cvdCollectionDate;
    }

    private Number cvdNumC19HOPats;

    /**
     * numc19hopats - HOSPITAL ONSET: Patients hospitalized in an NHSN inpatient care location with onset of suspected or confirmed COVID-19 14 or more days after hospitalization.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumC19HOPats() {
        return cvdNumC19HOPats;
    }

    /**
     * numc19hopats - HOSPITAL ONSET: Patients hospitalized in an NHSN inpatient care location with onset of suspected or confirmed COVID-19 14 or more days after hospitalization.
     *
     * @param cvdNumC19HOPats Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumC19HOPats(Number cvdNumC19HOPats) {
        this.cvdNumC19HOPats = cvdNumC19HOPats;
    }

    private Number cvdNumC19OFMechVentPats;

    /**
     * numc19ofmechventpats - ED/OVERFLOW and VENTILATED: Patients with suspected or confirmed COVID-19 who are in the ED or any overflow location awaiting an inpatient bed and on a mechanical ventilator.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumC19OFMechVentPats() {
        return cvdNumC19OFMechVentPats;
    }

    /**
     * numc19ofmechventpats - ED/OVERFLOW and VENTILATED: Patients with suspected or confirmed COVID-19 who are in the ED or any overflow location awaiting an inpatient bed and on a mechanical ventilator.
     *
     * @param cvdNumC19OFMechVentPats Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumC19OFMechVentPats(Number cvdNumC19OFMechVentPats) {
        this.cvdNumC19OFMechVentPats = cvdNumC19OFMechVentPats;
    }

    private Text cvdFacilityId;

    /**
     * Identifier of the NHSN facility that this data record applies to. Use [[cvdFacilityCounty]] to indicate the county. To provide other details, [[healthcareReportingData]] can be used on a [[Hospital]] entry.
     *
     * @return {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Text getCvdFacilityId() {
        return cvdFacilityId;
    }

    /**
     * Identifier of the NHSN facility that this data record applies to. Use [[cvdFacilityCounty]] to indicate the county. To provide other details, [[healthcareReportingData]] can be used on a [[Hospital]] entry.
     *
     * @param cvdFacilityId Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdFacilityId(Text cvdFacilityId) {
        this.cvdFacilityId = cvdFacilityId;
    }

    private Number cvdNumVentUse;

    /**
     * numventuse - MECHANICAL VENTILATORS IN USE: Total number of ventilators in use.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumVentUse() {
        return cvdNumVentUse;
    }

    /**
     * numventuse - MECHANICAL VENTILATORS IN USE: Total number of ventilators in use.
     *
     * @param cvdNumVentUse Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumVentUse(Number cvdNumVentUse) {
        this.cvdNumVentUse = cvdNumVentUse;
    }

    private Number cvdNumBeds;

    /**
     * numbeds - HOSPITAL INPATIENT BEDS: Inpatient beds, including all staffed, licensed, and overflow (surge) beds used for inpatients.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumBeds() {
        return cvdNumBeds;
    }

    /**
     * numbeds - HOSPITAL INPATIENT BEDS: Inpatient beds, including all staffed, licensed, and overflow (surge) beds used for inpatients.
     *
     * @param cvdNumBeds Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumBeds(Number cvdNumBeds) {
        this.cvdNumBeds = cvdNumBeds;
    }

    private Number cvdNumC19MechVentPats;

    /**
     * numc19mechventpats - HOSPITALIZED and VENTILATED: Patients hospitalized in an NHSN inpatient care location who have suspected or confirmed COVID-19 and are on a mechanical ventilator.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumC19MechVentPats() {
        return cvdNumC19MechVentPats;
    }

    /**
     * numc19mechventpats - HOSPITALIZED and VENTILATED: Patients hospitalized in an NHSN inpatient care location who have suspected or confirmed COVID-19 and are on a mechanical ventilator.
     *
     * @param cvdNumC19MechVentPats Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumC19MechVentPats(Number cvdNumC19MechVentPats) {
        this.cvdNumC19MechVentPats = cvdNumC19MechVentPats;
    }

    private Object datePosted;

    /**
     * Publication date of an online listing.
     *
     * @return {@link Date} or {@link DateTime}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2490">https://github.com/schemaorg/schemaorg/issues/2490</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public <T> T getDatePosted() {
        return (T) datePosted;
    }

    /**
     * Publication date of an online listing.
     *
     * @param datePosted Date value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2490">https://github.com/schemaorg/schemaorg/issues/2490</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }
    /**
     * Publication date of an online listing.
     *
     * @param datePosted DateTime value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2490">https://github.com/schemaorg/schemaorg/issues/2490</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setDatePosted(DateTime datePosted) {
        this.datePosted = datePosted;
    }

    private Number cvdNumC19OverflowPats;

    /**
     * numc19overflowpats - ED/OVERFLOW: Patients with suspected or confirmed COVID-19 who are in the ED or any overflow location awaiting an inpatient bed.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumC19OverflowPats() {
        return cvdNumC19OverflowPats;
    }

    /**
     * numc19overflowpats - ED/OVERFLOW: Patients with suspected or confirmed COVID-19 who are in the ED or any overflow location awaiting an inpatient bed.
     *
     * @param cvdNumC19OverflowPats Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumC19OverflowPats(Number cvdNumC19OverflowPats) {
        this.cvdNumC19OverflowPats = cvdNumC19OverflowPats;
    }

    private Number cvdNumC19HospPats;

    /**
     * numc19hosppats - HOSPITALIZED: Patients currently hospitalized in an inpatient care location who have suspected or confirmed COVID-19.
     *
     * @return {@link Number}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public Number getCvdNumC19HospPats() {
        return cvdNumC19HospPats;
    }

    /**
     * numc19hosppats - HOSPITALIZED: Patients currently hospitalized in an inpatient care location who have suspected or confirmed COVID-19.
     *
     * @param cvdNumC19HospPats Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    @Override
    public void setCvdNumC19HospPats(Number cvdNumC19HospPats) {
        this.cvdNumC19HospPats = cvdNumC19HospPats;
    }

    private Object mainEntityOfPage;

    /**
     * Indicates a page (or other CreativeWork) for which this thing is the main entity being described. See [background notes](/docs/datamodel.html#mainEntityBackground) for details.
     *
     * @return {@link CreativeWork} or {@link URL}
     */
    @Override
    public <T> T getMainEntityOfPage() {
        return (T) mainEntityOfPage;
    }

    /**
     * Indicates a page (or other CreativeWork) for which this thing is the main entity being described. See [background notes](/docs/datamodel.html#mainEntityBackground) for details.
     *
     * @param mainEntityOfPage CreativeWork value to set.
     */
    @Override
    public void setMainEntityOfPage(CreativeWork mainEntityOfPage) {
        this.mainEntityOfPage = mainEntityOfPage;
    }
    /**
     * Indicates a page (or other CreativeWork) for which this thing is the main entity being described. See [background notes](/docs/datamodel.html#mainEntityBackground) for details.
     *
     * @param mainEntityOfPage URL value to set.
     */
    @Override
    public void setMainEntityOfPage(URL mainEntityOfPage) {
        this.mainEntityOfPage = mainEntityOfPage;
    }

    private Text alternateName;

    /**
     * An alias for the item.
     *
     * @return {@link Text}
     */
    @Override
    public Text getAlternateName() {
        return alternateName;
    }

    /**
     * An alias for the item.
     *
     * @param alternateName Text value to set.
     */
    @Override
    public void setAlternateName(Text alternateName) {
        this.alternateName = alternateName;
    }

    private Text name;

    /**
     * The name of the item.
     *
     * @return {@link Text}
     */
    @Override
    public Text getName() {
        return name;
    }

    /**
     * The name of the item.
     *
     * @param name Text value to set.
     */
    @Override
    public void setName(Text name) {
        this.name = name;
    }

    private Action potentialAction;

    /**
     * Indicates a potential Action, which describes an idealized action in which this thing would play an 'object' role.
     *
     * @return {@link Action}
     */
    @Override
    public Action getPotentialAction() {
        return potentialAction;
    }

    /**
     * Indicates a potential Action, which describes an idealized action in which this thing would play an 'object' role.
     *
     * @param potentialAction Action value to set.
     */
    @Override
    public void setPotentialAction(Action potentialAction) {
        this.potentialAction = potentialAction;
    }

    private Object image;

    /**
     * An image of the item. This can be a [[URL]] or a fully described [[ImageObject]].
     *
     * @return {@link URL} or {@link ImageObject}
     */
    @Override
    public <T> T getImage() {
        return (T) image;
    }

    /**
     * An image of the item. This can be a [[URL]] or a fully described [[ImageObject]].
     *
     * @param image URL value to set.
     */
    @Override
    public void setImage(URL image) {
        this.image = image;
    }
    /**
     * An image of the item. This can be a [[URL]] or a fully described [[ImageObject]].
     *
     * @param image ImageObject value to set.
     */
    @Override
    public void setImage(ImageObject image) {
        this.image = image;
    }

    private URL url;

    /**
     * URL of the item.
     *
     * @return {@link URL}
     */
    @Override
    public URL getUrl() {
        return url;
    }

    /**
     * URL of the item.
     *
     * @param url URL value to set.
     */
    @Override
    public void setUrl(URL url) {
        this.url = url;
    }

    private Text description;

    /**
     * A description of the item.
     *
     * @return {@link Text}
     */
    @Override
    public Text getDescription() {
        return description;
    }

    /**
     * A description of the item.
     *
     * @param description Text value to set.
     */
    @Override
    public void setDescription(Text description) {
        this.description = description;
    }

    private Object subjectOf;

    /**
     * A CreativeWork or Event about this Thing.
     *
     * @return {@link Event} or {@link CreativeWork}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1670">https://github.com/schemaorg/schemaorg/issues/1670</a>
     */
    @Override
    public <T> T getSubjectOf() {
        return (T) subjectOf;
    }

    /**
     * A CreativeWork or Event about this Thing.
     *
     * @param subjectOf Event value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1670">https://github.com/schemaorg/schemaorg/issues/1670</a>
     */
    @Override
    public void setSubjectOf(Event subjectOf) {
        this.subjectOf = subjectOf;
    }
    /**
     * A CreativeWork or Event about this Thing.
     *
     * @param subjectOf CreativeWork value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1670">https://github.com/schemaorg/schemaorg/issues/1670</a>
     */
    @Override
    public void setSubjectOf(CreativeWork subjectOf) {
        this.subjectOf = subjectOf;
    }

    private URL additionalType;

    /**
     * An additional type for the item, typically used for adding more specific types from external vocabularies in microdata syntax. This is a relationship between something and a class that the thing is in. In RDFa syntax, it is better to use the native RDFa syntax - the 'typeof' attribute - for multiple types. Schema.org tools may have only weaker understanding of extra types, in particular those defined externally.
     *
     * @return {@link URL}
     */
    @Override
    public URL getAdditionalType() {
        return additionalType;
    }

    /**
     * An additional type for the item, typically used for adding more specific types from external vocabularies in microdata syntax. This is a relationship between something and a class that the thing is in. In RDFa syntax, it is better to use the native RDFa syntax - the 'typeof' attribute - for multiple types. Schema.org tools may have only weaker understanding of extra types, in particular those defined externally.
     *
     * @param additionalType URL value to set.
     */
    @Override
    public void setAdditionalType(URL additionalType) {
        this.additionalType = additionalType;
    }

    private Text disambiguatingDescription;

    /**
     * A sub property of description. A short description of the item used to disambiguate from other, similar items. Information from other properties (in particular, name) may be necessary for the description to be useful for disambiguation.
     *
     * @return {@link Text}
     */
    @Override
    public Text getDisambiguatingDescription() {
        return disambiguatingDescription;
    }

    /**
     * A sub property of description. A short description of the item used to disambiguate from other, similar items. Information from other properties (in particular, name) may be necessary for the description to be useful for disambiguation.
     *
     * @param disambiguatingDescription Text value to set.
     */
    @Override
    public void setDisambiguatingDescription(Text disambiguatingDescription) {
        this.disambiguatingDescription = disambiguatingDescription;
    }

    private URL sameAs;

    /**
     * URL of a reference Web page that unambiguously indicates the item's identity. E.g. the URL of the item's Wikipedia page, Wikidata entry, or official website.
     *
     * @return {@link URL}
     */
    @Override
    public URL getSameAs() {
        return sameAs;
    }

    /**
     * URL of a reference Web page that unambiguously indicates the item's identity. E.g. the URL of the item's Wikipedia page, Wikidata entry, or official website.
     *
     * @param sameAs URL value to set.
     */
    @Override
    public void setSameAs(URL sameAs) {
        this.sameAs = sameAs;
    }

    private Object identifier;

    /**
     * The identifier property represents any kind of identifier for any kind of [[Thing]], such as ISBNs, GTIN codes, UUIDs etc. Schema.org provides dedicated properties for representing many of these, either as textual strings or as URL (URI) links. See [background notes](/docs/datamodel.html#identifierBg) for more details.
     *         
     *
     * @return {@link URL} or {@link Text} or {@link PropertyValue}
     */
    @Override
    public <T> T getIdentifier() {
        return (T) identifier;
    }

    /**
     * The identifier property represents any kind of identifier for any kind of [[Thing]], such as ISBNs, GTIN codes, UUIDs etc. Schema.org provides dedicated properties for representing many of these, either as textual strings or as URL (URI) links. See [background notes](/docs/datamodel.html#identifierBg) for more details.
     *         
     *
     * @param identifier URL value to set.
     */
    @Override
    public void setIdentifier(URL identifier) {
        this.identifier = identifier;
    }
    /**
     * The identifier property represents any kind of identifier for any kind of [[Thing]], such as ISBNs, GTIN codes, UUIDs etc. Schema.org provides dedicated properties for representing many of these, either as textual strings or as URL (URI) links. See [background notes](/docs/datamodel.html#identifierBg) for more details.
     *         
     *
     * @param identifier Text value to set.
     */
    @Override
    public void setIdentifier(Text identifier) {
        this.identifier = identifier;
    }
    /**
     * The identifier property represents any kind of identifier for any kind of [[Thing]], such as ISBNs, GTIN codes, UUIDs etc. Schema.org provides dedicated properties for representing many of these, either as textual strings or as URL (URI) links. See [background notes](/docs/datamodel.html#identifierBg) for more details.
     *         
     *
     * @param identifier PropertyValue value to set.
     */
    @Override
    public void setIdentifier(PropertyValue identifier) {
        this.identifier = identifier;
    }
}
