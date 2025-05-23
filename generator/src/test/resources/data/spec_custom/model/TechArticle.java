/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.datatype.Text;

/**
 * A technical article - Example: How-to (task) topics, step-by-step, procedural troubleshooting, specifications, etc.
 *
 * @see <a href="https://schema.org/TechArticle">https://schema.org/TechArticle</a>
 */
public interface TechArticle extends Article {

    /**
     * Proficiency needed for this content; expected values: 'Beginner', 'Expert'.
     *
     * @return {@link Text}
     */
    List<Text> getProficiencyLevelList();

    /**
     * Proficiency needed for this content; expected values: 'Beginner', 'Expert'.
     *
     * @return {@link Text}
     */
    Text getProficiencyLevel();

    /**
     * Proficiency needed for this content; expected values: 'Beginner', 'Expert'.
     *
     * @param proficiencyLevel Text value to set.
     */
    void addProficiencyLevel(Text proficiencyLevel);

    /**
     * Prerequisites needed to fulfill steps in article.
     *
     * @return {@link Text}
     */
    List<Text> getDependenciesList();

    /**
     * Prerequisites needed to fulfill steps in article.
     *
     * @return {@link Text}
     */
    Text getDependencies();

    /**
     * Prerequisites needed to fulfill steps in article.
     *
     * @param dependencies Text value to set.
     */
    void addDependencies(Text dependencies);
}
