/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.Person;

/**
 * The act of providing an object under an agreement that it will be returned at a later date. Reciprocal of BorrowAction.<br/><br/>Related actions:<br/><br/>* [[BorrowAction]]: Reciprocal of LendAction.
 *
 * @see <a href="https://schema.org/LendAction">https://schema.org/LendAction</a>
 */
public interface LendAction extends TransferAction {

    /**
     * A sub property of participant. The person that borrows the object being lent.
     *
     * @return {@link Person}
     */
    List<Person> getBorrowerList();

    /**
     * A sub property of participant. The person that borrows the object being lent.
     *
     * @return {@link Person}
     */
    Person getBorrower();

    /**
     * A sub property of participant. The person that borrows the object being lent.
     *
     * @param borrower Person value to set.
     */
    void addBorrower(Person borrower);
}
