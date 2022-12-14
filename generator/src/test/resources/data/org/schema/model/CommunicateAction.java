/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.Person;
import org.schema.model.Audience;
import org.schema.model.ContactPoint;
import org.schema.model.Organization;
import org.schema.model.datatype.Text;
import org.schema.model.Language;
import org.schema.model.Thing;

/**
 * The act of conveying information to another person via a communication medium (instrument) such as speech, email, or telephone conversation.
 *
 * @see <a href="https://schema.org/CommunicateAction">https://schema.org/CommunicateAction</a>
 */
public interface CommunicateAction extends InteractAction {

    /**
     * A sub property of participant. The participant who is at the receiving end of the action.
     *
     * @return {@link Person} or {@link Audience} or {@link ContactPoint} or {@link Organization}
     */
    <T> T getRecipient();

    /**
     * A sub property of participant. The participant who is at the receiving end of the action.
     *
     * @param recipient Person value to set.
     */
    void setRecipient(Person recipient);
    /**
     * A sub property of participant. The participant who is at the receiving end of the action.
     *
     * @param recipient Audience value to set.
     */
    void setRecipient(Audience recipient);
    /**
     * A sub property of participant. The participant who is at the receiving end of the action.
     *
     * @param recipient ContactPoint value to set.
     */
    void setRecipient(ContactPoint recipient);
    /**
     * A sub property of participant. The participant who is at the receiving end of the action.
     *
     * @param recipient Organization value to set.
     */
    void setRecipient(Organization recipient);

    /**
     * The language of the content or performance or used in an action. Please use one of the language codes from the [IETF BCP 47 standard](http://tools.ietf.org/html/bcp47). See also [[availableLanguage]].
     *
     * @return {@link Text} or {@link Language}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2382">https://github.com/schemaorg/schemaorg/issues/2382</a>
     */
    <T> T getInLanguage();

    /**
     * The language of the content or performance or used in an action. Please use one of the language codes from the [IETF BCP 47 standard](http://tools.ietf.org/html/bcp47). See also [[availableLanguage]].
     *
     * @param inLanguage Text value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2382">https://github.com/schemaorg/schemaorg/issues/2382</a>
     */
    void setInLanguage(Text inLanguage);
    /**
     * The language of the content or performance or used in an action. Please use one of the language codes from the [IETF BCP 47 standard](http://tools.ietf.org/html/bcp47). See also [[availableLanguage]].
     *
     * @param inLanguage Language value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2382">https://github.com/schemaorg/schemaorg/issues/2382</a>
     */
    void setInLanguage(Language inLanguage);

    /**
     * A sub property of instrument. The language used on this action.
     *
     * @return {@link Language}
     */
    Language getLanguage();

    /**
     * A sub property of instrument. The language used on this action.
     *
     * @param language Language value to set.
     */
    void setLanguage(Language language);

    /**
     * The subject matter of the content.
     *
     * @return {@link Thing}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1670">https://github.com/schemaorg/schemaorg/issues/1670</a>
     */
    Thing getAbout();

    /**
     * The subject matter of the content.
     *
     * @param about Thing value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1670">https://github.com/schemaorg/schemaorg/issues/1670</a>
     */
    void setAbout(Thing about);
}
