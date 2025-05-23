/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.datatype.Text;

/**
 * An EducationalAudience.
 *
 * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_LRMIClass">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_LRMIClass</a>
 * @see <a href="https://schema.org/EducationalAudience">https://schema.org/EducationalAudience</a>
 */
public interface EducationalAudience extends Audience {

    /**
     * An educationalRole of an EducationalAudience.
     *
     * @return {@link Text}
     */
    List<Text> getEducationalRoleList();

    /**
     * An educationalRole of an EducationalAudience.
     *
     * @return {@link Text}
     */
    Text getEducationalRole();

    /**
     * An educationalRole of an EducationalAudience.
     *
     * @param educationalRole Text value to set.
     */
    void addEducationalRole(Text educationalRole);
}
