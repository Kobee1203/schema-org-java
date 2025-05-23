/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.Organization;
import spec_custom.model.Person;

/**
 * The act of obtaining an object under an agreement to return it at a later date. Reciprocal of LendAction.<br/><br/>Related actions:<br/><br/>* [[LendAction]]: Reciprocal of BorrowAction.
 *
 * @see <a href="https://schema.org/BorrowAction">https://schema.org/BorrowAction</a>
 */
public interface BorrowAction extends TransferAction {

    /**
     * A sub property of participant. The person that lends the object being borrowed.
     *
     * @return {@link Organization} or {@link Person}
     */
    <T> List<T> getLenderList();

    /**
     * A sub property of participant. The person that lends the object being borrowed.
     *
     * @return {@link Organization} or {@link Person}
     */
    <T> T getLender();

    /**
     * A sub property of participant. The person that lends the object being borrowed.
     *
     * @param lender Organization value to set.
     */
    void addLender(Organization lender);
    /**
     * A sub property of participant. The person that lends the object being borrowed.
     *
     * @param lender Person value to set.
     */
    void addLender(Person lender);
}
