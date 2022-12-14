/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.Thing;

/**
 * The act of editing a recipient by replacing an old object with a new object.
 *
 * @see <a href="https://schema.org/ReplaceAction">https://schema.org/ReplaceAction</a>
 */
public interface ReplaceAction extends UpdateAction {

    /**
     * A sub property of object. The object that is being replaced.
     *
     * @return {@link Thing}
     */
    Thing getReplacee();

    /**
     * A sub property of object. The object that is being replaced.
     *
     * @param replacee Thing value to set.
     */
    void setReplacee(Thing replacee);

    /**
     * A sub property of object. The object that replaces.
     *
     * @return {@link Thing}
     */
    Thing getReplacer();

    /**
     * A sub property of object. The object that replaces.
     *
     * @param replacer Thing value to set.
     */
    void setReplacer(Thing replacer);
}
