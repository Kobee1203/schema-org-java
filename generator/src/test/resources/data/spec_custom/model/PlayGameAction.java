/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.datatype.Text;
import spec_custom.model.GameAvailabilityEnumeration;

/**
 * The act of playing a video game.
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/3058">https://github.com/schemaorg/schemaorg/issues/3058</a>
 * @see <a href="https://schema.org/PlayGameAction">https://schema.org/PlayGameAction</a>
 */
public interface PlayGameAction extends ConsumeAction {

    /**
     * Indicates the availability type of the game content associated with this action, such as whether it is a full version or a demo.
     *
     * @return {@link Text} or {@link GameAvailabilityEnumeration}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/3058">https://github.com/schemaorg/schemaorg/issues/3058</a>
     */
    <T> List<T> getGameAvailabilityTypeList();

    /**
     * Indicates the availability type of the game content associated with this action, such as whether it is a full version or a demo.
     *
     * @return {@link Text} or {@link GameAvailabilityEnumeration}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/3058">https://github.com/schemaorg/schemaorg/issues/3058</a>
     */
    <T> T getGameAvailabilityType();

    /**
     * Indicates the availability type of the game content associated with this action, such as whether it is a full version or a demo.
     *
     * @param gameAvailabilityType Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/3058">https://github.com/schemaorg/schemaorg/issues/3058</a>
     */
    void addGameAvailabilityType(Text gameAvailabilityType);
    /**
     * Indicates the availability type of the game content associated with this action, such as whether it is a full version or a demo.
     *
     * @param gameAvailabilityType GameAvailabilityEnumeration value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/3058">https://github.com/schemaorg/schemaorg/issues/3058</a>
     */
    void addGameAvailabilityType(GameAvailabilityEnumeration gameAvailabilityType);
}
