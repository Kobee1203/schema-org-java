/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.datatype.Text;
import spec_custom.model.MonetaryAmount;

/**
 * A supply consumed when performing the instructions for how to achieve a result.
 *
 * @see <a href="https://schema.org/HowToSupply">https://schema.org/HowToSupply</a>
 */
public interface HowToSupply extends HowToItem {

    /**
     * The estimated cost of the supply or supplies consumed when performing instructions.
     *
     * @return {@link Text} or {@link MonetaryAmount}
     */
    <T> List<T> getEstimatedCostList();

    /**
     * The estimated cost of the supply or supplies consumed when performing instructions.
     *
     * @return {@link Text} or {@link MonetaryAmount}
     */
    <T> T getEstimatedCost();

    /**
     * The estimated cost of the supply or supplies consumed when performing instructions.
     *
     * @param estimatedCost Text value to set.
     */
    void addEstimatedCost(Text estimatedCost);
    /**
     * The estimated cost of the supply or supplies consumed when performing instructions.
     *
     * @param estimatedCost MonetaryAmount value to set.
     */
    void addEstimatedCost(MonetaryAmount estimatedCost);
}
