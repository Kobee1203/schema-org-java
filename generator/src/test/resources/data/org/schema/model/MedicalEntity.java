/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.DrugLegalStatus;
import org.schema.model.datatype.Text;
import org.schema.model.MedicalEnumeration;
import org.schema.model.Grant;
import org.schema.model.MedicalStudy;
import org.schema.model.MedicalCode;
import org.schema.model.MedicalGuideline;
import org.schema.model.Organization;
import org.schema.model.MedicineSystem;
import org.schema.model.MedicalSpecialty;

/**
 * The most generic type of entity related to health and the practice of medicine.
 *
 * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
 * @see <a href="https://schema.org/MedicalEntity">https://schema.org/MedicalEntity</a>
 */
public interface MedicalEntity extends Thing {

    /**
     * The drug or supplement's legal status, including any controlled substance schedules that apply.
     *
     * @return {@link DrugLegalStatus} or {@link Text} or {@link MedicalEnumeration}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    <T> T getLegalStatus();

    /**
     * The drug or supplement's legal status, including any controlled substance schedules that apply.
     *
     * @param legalStatus DrugLegalStatus value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void setLegalStatus(DrugLegalStatus legalStatus);
    /**
     * The drug or supplement's legal status, including any controlled substance schedules that apply.
     *
     * @param legalStatus Text value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void setLegalStatus(Text legalStatus);
    /**
     * The drug or supplement's legal status, including any controlled substance schedules that apply.
     *
     * @param legalStatus MedicalEnumeration value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void setLegalStatus(MedicalEnumeration legalStatus);

    /**
     * A [[Grant]] that directly or indirectly provide funding or sponsorship for this item. See also [[ownershipFundingInfo]].
     *
     * @return {@link Grant}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/383">https://github.com/schemaorg/schemaorg/issues/383</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    Grant getFunding();

    /**
     * A [[Grant]] that directly or indirectly provide funding or sponsorship for this item. See also [[ownershipFundingInfo]].
     *
     * @param funding Grant value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/383">https://github.com/schemaorg/schemaorg/issues/383</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    void setFunding(Grant funding);

    /**
     * A medical study or trial related to this entity.
     *
     * @return {@link MedicalStudy}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    MedicalStudy getStudy();

    /**
     * A medical study or trial related to this entity.
     *
     * @param study MedicalStudy value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void setStudy(MedicalStudy study);

    /**
     * A medical code for the entity, taken from a controlled vocabulary or ontology such as ICD-9, DiseasesDB, MeSH, SNOMED-CT, RxNorm, etc.
     *
     * @return {@link MedicalCode}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    MedicalCode getCode();

    /**
     * A medical code for the entity, taken from a controlled vocabulary or ontology such as ICD-9, DiseasesDB, MeSH, SNOMED-CT, RxNorm, etc.
     *
     * @param code MedicalCode value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void setCode(MedicalCode code);

    /**
     * A medical guideline related to this entity.
     *
     * @return {@link MedicalGuideline}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    MedicalGuideline getGuideline();

    /**
     * A medical guideline related to this entity.
     *
     * @param guideline MedicalGuideline value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void setGuideline(MedicalGuideline guideline);

    /**
     * If applicable, the organization that officially recognizes this entity as part of its endorsed system of medicine.
     *
     * @return {@link Organization}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    Organization getRecognizingAuthority();

    /**
     * If applicable, the organization that officially recognizes this entity as part of its endorsed system of medicine.
     *
     * @param recognizingAuthority Organization value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void setRecognizingAuthority(Organization recognizingAuthority);

    /**
     * The system of medicine that includes this MedicalEntity, for example 'evidence-based', 'homeopathic', 'chiropractic', etc.
     *
     * @return {@link MedicineSystem}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    MedicineSystem getMedicineSystem();

    /**
     * The system of medicine that includes this MedicalEntity, for example 'evidence-based', 'homeopathic', 'chiropractic', etc.
     *
     * @param medicineSystem MedicineSystem value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void setMedicineSystem(MedicineSystem medicineSystem);

    /**
     * If applicable, a medical specialty in which this entity is relevant.
     *
     * @return {@link MedicalSpecialty}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    MedicalSpecialty getRelevantSpecialty();

    /**
     * If applicable, a medical specialty in which this entity is relevant.
     *
     * @param relevantSpecialty MedicalSpecialty value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void setRelevantSpecialty(MedicalSpecialty relevantSpecialty);
}
