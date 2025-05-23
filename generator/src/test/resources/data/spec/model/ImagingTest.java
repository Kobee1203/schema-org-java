/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.MedicalImagingTechnique;

/**
 * Any medical imaging modality typically used for diagnostic purposes.
 *
 * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
 * @see <a href="https://schema.org/ImagingTest">https://schema.org/ImagingTest</a>
 */
public interface ImagingTest extends MedicalTest {

    /**
     * Imaging technique used.
     *
     * @return {@link MedicalImagingTechnique}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    List<MedicalImagingTechnique> getImagingTechniqueList();

    /**
     * Imaging technique used.
     *
     * @return {@link MedicalImagingTechnique}
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    MedicalImagingTechnique getImagingTechnique();

    /**
     * Imaging technique used.
     *
     * @param imagingTechnique MedicalImagingTechnique value to set.
     * @see <a href="https://health-lifesci.schema.org">https://health-lifesci.schema.org</a>
     */
    void addImagingTechnique(MedicalImagingTechnique imagingTechnique);
}
