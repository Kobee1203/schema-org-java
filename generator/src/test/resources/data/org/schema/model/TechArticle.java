/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.datatype.Text;

/**
 * A technical article - Example: How-to (task) topics, step-by-step, procedural troubleshooting, specifications, etc.
 *
 * @see <a href="https://schema.org/TechArticle">https://schema.org/TechArticle</a>
 */
public interface TechArticle extends Article {

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
    void setDependencies(Text dependencies);

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
    void setProficiencyLevel(Text proficiencyLevel);
}
