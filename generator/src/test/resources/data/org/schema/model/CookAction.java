/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.Recipe;
import org.schema.model.Place;
import org.schema.model.FoodEstablishment;
import org.schema.model.FoodEvent;

/**
 * The act of producing/preparing food.
 *
 * @see <a href="https://schema.org/CookAction">https://schema.org/CookAction</a>
 */
public interface CookAction extends CreateAction {

    /**
     * A sub property of instrument. The recipe/instructions used to perform the action.
     *
     * @return {@link Recipe}
     */
    Recipe getRecipe();

    /**
     * A sub property of instrument. The recipe/instructions used to perform the action.
     *
     * @param recipe Recipe value to set.
     */
    void setRecipe(Recipe recipe);

    /**
     * A sub property of location. The specific food establishment where the action occurred.
     *
     * @return {@link Place} or {@link FoodEstablishment}
     */
    <T> T getFoodEstablishment();

    /**
     * A sub property of location. The specific food establishment where the action occurred.
     *
     * @param foodEstablishment Place value to set.
     */
    void setFoodEstablishment(Place foodEstablishment);
    /**
     * A sub property of location. The specific food establishment where the action occurred.
     *
     * @param foodEstablishment FoodEstablishment value to set.
     */
    void setFoodEstablishment(FoodEstablishment foodEstablishment);

    /**
     * A sub property of location. The specific food event where the action occurred.
     *
     * @return {@link FoodEvent}
     */
    FoodEvent getFoodEvent();

    /**
     * A sub property of location. The specific food event where the action occurred.
     *
     * @param foodEvent FoodEvent value to set.
     */
    void setFoodEvent(FoodEvent foodEvent);
}
