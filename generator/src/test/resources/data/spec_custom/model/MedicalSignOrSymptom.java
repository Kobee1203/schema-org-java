/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.MedicalTherapy;

/**
 * Any feature associated or not with a medical condition. In medicine a symptom is generally subjective while a sign is objective.
 *
 * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
 * @see <a href="https://schema.org/MedicalSignOrSymptom">https://schema.org/MedicalSignOrSymptom</a>
 */
public interface MedicalSignOrSymptom extends MedicalCondition {

    /**
     * A possible treatment to address this condition, sign or symptom.
     *
     * @return {@link MedicalTherapy}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    List<MedicalTherapy> getPossibleTreatmentList();

    /**
     * A possible treatment to address this condition, sign or symptom.
     *
     * @return {@link MedicalTherapy}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    MedicalTherapy getPossibleTreatment();

    /**
     * A possible treatment to address this condition, sign or symptom.
     *
     * @param possibleTreatment MedicalTherapy value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void addPossibleTreatment(MedicalTherapy possibleTreatment);
}
