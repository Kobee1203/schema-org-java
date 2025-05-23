/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.CDCPMDRecord;
import spec_custom.model.Dataset;
import spec_custom.model.MedicalSpecialty;
import spec_custom.model.MedicalTherapy;
import spec_custom.model.MedicalTest;
import spec_custom.model.MedicalProcedure;

/**
 * A hospital.
 *
 * @see <a href="https://schema.org/Hospital">https://schema.org/Hospital</a>
 */
public interface Hospital extends EmergencyService, MedicalOrganization, CivicStructure {

    /**
     * Indicates data describing a hospital, e.g. a CDC [[CDCPMDRecord]] or as some kind of [[Dataset]].
     *
     * @return {@link CDCPMDRecord} or {@link Dataset}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    <T> List<T> getHealthcareReportingDataList();

    /**
     * Indicates data describing a hospital, e.g. a CDC [[CDCPMDRecord]] or as some kind of [[Dataset]].
     *
     * @return {@link CDCPMDRecord} or {@link Dataset}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    <T> T getHealthcareReportingData();

    /**
     * Indicates data describing a hospital, e.g. a CDC [[CDCPMDRecord]] or as some kind of [[Dataset]].
     *
     * @param healthcareReportingData CDCPMDRecord value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    void addHealthcareReportingData(CDCPMDRecord healthcareReportingData);
    /**
     * Indicates data describing a hospital, e.g. a CDC [[CDCPMDRecord]] or as some kind of [[Dataset]].
     *
     * @param healthcareReportingData Dataset value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2521">https://github.com/schemaorg/schemaorg/issues/2521</a>
     */
    void addHealthcareReportingData(Dataset healthcareReportingData);

    /**
     * A medical specialty of the provider.
     *
     * @return {@link MedicalSpecialty}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    List<MedicalSpecialty> getMedicalSpecialtyList();

    /**
     * A medical specialty of the provider.
     *
     * @return {@link MedicalSpecialty}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    MedicalSpecialty getMedicalSpecialty();

    /**
     * A medical specialty of the provider.
     *
     * @param medicalSpecialty MedicalSpecialty value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void addMedicalSpecialty(MedicalSpecialty medicalSpecialty);

    /**
     * A medical service available from this provider.
     *
     * @return {@link MedicalTherapy} or {@link MedicalTest} or {@link MedicalProcedure}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    <T> List<T> getAvailableServiceList();

    /**
     * A medical service available from this provider.
     *
     * @return {@link MedicalTherapy} or {@link MedicalTest} or {@link MedicalProcedure}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    <T> T getAvailableService();

    /**
     * A medical service available from this provider.
     *
     * @param availableService MedicalTherapy value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void addAvailableService(MedicalTherapy availableService);
    /**
     * A medical service available from this provider.
     *
     * @param availableService MedicalTest value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void addAvailableService(MedicalTest availableService);
    /**
     * A medical service available from this provider.
     *
     * @param availableService MedicalProcedure value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void addAvailableService(MedicalProcedure availableService);
}
