/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.QuantitativeValue;
import org.schema.model.datatype.Number;
import org.schema.model.datatype.Text;

/**
 * An item used as either a tool or supply when performing the instructions for how to to achieve a result.
 *
 * @see <a href="https://schema.org/HowToItem">https://schema.org/HowToItem</a>
 */
public interface HowToItem extends ListItem {

    /**
     * The required quantity of the item(s).
     *
     * @return {@link QuantitativeValue} or {@link Number} or {@link Text}
     */
    <T> T getRequiredQuantity();

    /**
     * The required quantity of the item(s).
     *
     * @param requiredQuantity QuantitativeValue value to set.
     */
    void setRequiredQuantity(QuantitativeValue requiredQuantity);
    /**
     * The required quantity of the item(s).
     *
     * @param requiredQuantity Number value to set.
     */
    void setRequiredQuantity(Number requiredQuantity);
    /**
     * The required quantity of the item(s).
     *
     * @param requiredQuantity Text value to set.
     */
    void setRequiredQuantity(Text requiredQuantity);
}
