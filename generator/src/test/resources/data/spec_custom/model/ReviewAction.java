/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.Review;

/**
 * The act of producing a balanced opinion about the object for an audience. An agent reviews an object with participants resulting in a review.
 *
 * @see <a href="https://schema.org/ReviewAction">https://schema.org/ReviewAction</a>
 */
public interface ReviewAction extends AssessAction {

    /**
     * A sub property of result. The review that resulted in the performing of the action.
     *
     * @return {@link Review}
     */
    List<Review> getResultReviewList();

    /**
     * A sub property of result. The review that resulted in the performing of the action.
     *
     * @return {@link Review}
     */
    Review getResultReview();

    /**
     * A sub property of result. The review that resulted in the performing of the action.
     *
     * @param resultReview Review value to set.
     */
    void addResultReview(Review resultReview);
}
