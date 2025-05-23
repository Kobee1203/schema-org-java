/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.datatype.Text;
import spec_custom.model.datatype.Number;
import spec_custom.model.QuantitativeValue;

/**
 * An item used as either a tool or supply when performing the instructions for how to achieve a result.
 *
 * @see <a href="https://schema.org/HowToItem">https://schema.org/HowToItem</a>
 */
public interface HowToItem extends ListItem {

    /**
     * The required quantity of the item(s).
     *
     * @return {@link Text} or {@link Number} or {@link QuantitativeValue}
     */
    <T> List<T> getRequiredQuantityList();

    /**
     * The required quantity of the item(s).
     *
     * @return {@link Text} or {@link Number} or {@link QuantitativeValue}
     */
    <T> T getRequiredQuantity();

    /**
     * The required quantity of the item(s).
     *
     * @param requiredQuantity Text value to set.
     */
    void addRequiredQuantity(Text requiredQuantity);
    /**
     * The required quantity of the item(s).
     *
     * @param requiredQuantity Number value to set.
     */
    void addRequiredQuantity(Number requiredQuantity);
    /**
     * The required quantity of the item(s).
     *
     * @param requiredQuantity QuantitativeValue value to set.
     */
    void addRequiredQuantity(QuantitativeValue requiredQuantity);
}
