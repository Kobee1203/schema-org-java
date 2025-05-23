/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.datatype.DateTime;
import spec_custom.model.datatype.Time;
import spec_custom.model.Action;
import spec_custom.model.WebSite;
import spec_custom.model.SoftwareApplication;
import spec_custom.model.datatype.Integer;
import spec_custom.model.Place;
import spec_custom.model.datatype.Text;
import spec_custom.model.VirtualLocation;
import spec_custom.model.PostalAddress;

/**
 * A summary of how users have interacted with this CreativeWork. In most cases, authors will use a subtype to specify the specific type of interaction.
 *
 * @see <a href="https://schema.org/InteractionCounter">https://schema.org/InteractionCounter</a>
 */
public interface InteractionCounter extends StructuredValue {

    /**
     * The endTime of something. For a reserved event or service (e.g. FoodEstablishmentReservation), the time that it is expected to end. For actions that span a period of time, when the action was performed. E.g. John wrote a book from January to *December*. For media, including audio and video, it's the time offset of the end of a clip within a larger file.<br/><br/>Note that Event uses startDate/endDate instead of startTime/endTime, even when describing dates with times. This situation may be clarified in future revisions.
     *
     * @return {@link DateTime} or {@link Time}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2493">https://github.com/schemaorg/schemaorg/issues/2493</a>
     */
    <T> List<T> getEndTimeList();

    /**
     * The endTime of something. For a reserved event or service (e.g. FoodEstablishmentReservation), the time that it is expected to end. For actions that span a period of time, when the action was performed. E.g. John wrote a book from January to *December*. For media, including audio and video, it's the time offset of the end of a clip within a larger file.<br/><br/>Note that Event uses startDate/endDate instead of startTime/endTime, even when describing dates with times. This situation may be clarified in future revisions.
     *
     * @return {@link DateTime} or {@link Time}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2493">https://github.com/schemaorg/schemaorg/issues/2493</a>
     */
    <T> T getEndTime();

    /**
     * The endTime of something. For a reserved event or service (e.g. FoodEstablishmentReservation), the time that it is expected to end. For actions that span a period of time, when the action was performed. E.g. John wrote a book from January to *December*. For media, including audio and video, it's the time offset of the end of a clip within a larger file.<br/><br/>Note that Event uses startDate/endDate instead of startTime/endTime, even when describing dates with times. This situation may be clarified in future revisions.
     *
     * @param endTime DateTime value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2493">https://github.com/schemaorg/schemaorg/issues/2493</a>
     */
    void addEndTime(DateTime endTime);
    /**
     * The endTime of something. For a reserved event or service (e.g. FoodEstablishmentReservation), the time that it is expected to end. For actions that span a period of time, when the action was performed. E.g. John wrote a book from January to *December*. For media, including audio and video, it's the time offset of the end of a clip within a larger file.<br/><br/>Note that Event uses startDate/endDate instead of startTime/endTime, even when describing dates with times. This situation may be clarified in future revisions.
     *
     * @param endTime Time value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2493">https://github.com/schemaorg/schemaorg/issues/2493</a>
     */
    void addEndTime(Time endTime);

    /**
     * The startTime of something. For a reserved event or service (e.g. FoodEstablishmentReservation), the time that it is expected to start. For actions that span a period of time, when the action was performed. E.g. John wrote a book from *January* to December. For media, including audio and video, it's the time offset of the start of a clip within a larger file.<br/><br/>Note that Event uses startDate/endDate instead of startTime/endTime, even when describing dates with times. This situation may be clarified in future revisions.
     *
     * @return {@link Time} or {@link DateTime}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2493">https://github.com/schemaorg/schemaorg/issues/2493</a>
     */
    <T> List<T> getStartTimeList();

    /**
     * The startTime of something. For a reserved event or service (e.g. FoodEstablishmentReservation), the time that it is expected to start. For actions that span a period of time, when the action was performed. E.g. John wrote a book from *January* to December. For media, including audio and video, it's the time offset of the start of a clip within a larger file.<br/><br/>Note that Event uses startDate/endDate instead of startTime/endTime, even when describing dates with times. This situation may be clarified in future revisions.
     *
     * @return {@link Time} or {@link DateTime}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2493">https://github.com/schemaorg/schemaorg/issues/2493</a>
     */
    <T> T getStartTime();

    /**
     * The startTime of something. For a reserved event or service (e.g. FoodEstablishmentReservation), the time that it is expected to start. For actions that span a period of time, when the action was performed. E.g. John wrote a book from *January* to December. For media, including audio and video, it's the time offset of the start of a clip within a larger file.<br/><br/>Note that Event uses startDate/endDate instead of startTime/endTime, even when describing dates with times. This situation may be clarified in future revisions.
     *
     * @param startTime Time value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2493">https://github.com/schemaorg/schemaorg/issues/2493</a>
     */
    void addStartTime(Time startTime);
    /**
     * The startTime of something. For a reserved event or service (e.g. FoodEstablishmentReservation), the time that it is expected to start. For actions that span a period of time, when the action was performed. E.g. John wrote a book from *January* to December. For media, including audio and video, it's the time offset of the start of a clip within a larger file.<br/><br/>Note that Event uses startDate/endDate instead of startTime/endTime, even when describing dates with times. This situation may be clarified in future revisions.
     *
     * @param startTime DateTime value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2493">https://github.com/schemaorg/schemaorg/issues/2493</a>
     */
    void addStartTime(DateTime startTime);

    /**
     * The Action representing the type of interaction. For up votes, +1s, etc. use [[LikeAction]]. For down votes use [[DislikeAction]]. Otherwise, use the most specific Action.
     *
     * @return {@link Action}
     */
    List<Action> getInteractionTypeList();

    /**
     * The Action representing the type of interaction. For up votes, +1s, etc. use [[LikeAction]]. For down votes use [[DislikeAction]]. Otherwise, use the most specific Action.
     *
     * @return {@link Action}
     */
    Action getInteractionType();

    /**
     * The Action representing the type of interaction. For up votes, +1s, etc. use [[LikeAction]]. For down votes use [[DislikeAction]]. Otherwise, use the most specific Action.
     *
     * @param interactionType Action value to set.
     */
    void addInteractionType(Action interactionType);

    /**
     * The WebSite or SoftwareApplication where the interactions took place.
     *
     * @return {@link WebSite} or {@link SoftwareApplication}
     */
    <T> List<T> getInteractionServiceList();

    /**
     * The WebSite or SoftwareApplication where the interactions took place.
     *
     * @return {@link WebSite} or {@link SoftwareApplication}
     */
    <T> T getInteractionService();

    /**
     * The WebSite or SoftwareApplication where the interactions took place.
     *
     * @param interactionService WebSite value to set.
     */
    void addInteractionService(WebSite interactionService);
    /**
     * The WebSite or SoftwareApplication where the interactions took place.
     *
     * @param interactionService SoftwareApplication value to set.
     */
    void addInteractionService(SoftwareApplication interactionService);

    /**
     * The number of interactions for the CreativeWork using the WebSite or SoftwareApplication.
     *
     * @return {@link Integer}
     */
    List<Integer> getUserInteractionCountList();

    /**
     * The number of interactions for the CreativeWork using the WebSite or SoftwareApplication.
     *
     * @return {@link Integer}
     */
    Integer getUserInteractionCount();

    /**
     * The number of interactions for the CreativeWork using the WebSite or SoftwareApplication.
     *
     * @param userInteractionCount Integer value to set.
     */
    void addUserInteractionCount(Integer userInteractionCount);

    /**
     * The location of, for example, where an event is happening, where an organization is located, or where an action takes place.
     *
     * @return {@link Place} or {@link Text} or {@link VirtualLocation} or {@link PostalAddress}
     */
    <T> List<T> getLocationList();

    /**
     * The location of, for example, where an event is happening, where an organization is located, or where an action takes place.
     *
     * @return {@link Place} or {@link Text} or {@link VirtualLocation} or {@link PostalAddress}
     */
    <T> T getLocation();

    /**
     * The location of, for example, where an event is happening, where an organization is located, or where an action takes place.
     *
     * @param location Place value to set.
     */
    void addLocation(Place location);
    /**
     * The location of, for example, where an event is happening, where an organization is located, or where an action takes place.
     *
     * @param location Text value to set.
     */
    void addLocation(Text location);
    /**
     * The location of, for example, where an event is happening, where an organization is located, or where an action takes place.
     *
     * @param location VirtualLocation value to set.
     */
    void addLocation(VirtualLocation location);
    /**
     * The location of, for example, where an event is happening, where an organization is located, or where an action takes place.
     *
     * @param location PostalAddress value to set.
     */
    void addLocation(PostalAddress location);
}
