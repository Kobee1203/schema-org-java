/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.datatype.Text;
import spec.model.QualitativeValue;
import spec.model.datatype.Number;
import spec.model.QuantitativeValue;

/**
 * The frequency in MHz and the modulation used for a particular BroadcastService.
 *
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/1004">https://github.com/schemaorg/schemaorg/issues/1004</a>
 * @see <a href="https://schema.org/BroadcastFrequencySpecification">https://schema.org/BroadcastFrequencySpecification</a>
 */
public interface BroadcastFrequencySpecification extends Intangible {

    /**
     * The modulation (e.g. FM, AM, etc) used by a particular broadcast service.
     *
     * @return {@link Text} or {@link QualitativeValue}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2111">https://github.com/schemaorg/schemaorg/issues/2111</a>
     */
    <T> List<T> getBroadcastSignalModulationList();

    /**
     * The modulation (e.g. FM, AM, etc) used by a particular broadcast service.
     *
     * @return {@link Text} or {@link QualitativeValue}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2111">https://github.com/schemaorg/schemaorg/issues/2111</a>
     */
    <T> T getBroadcastSignalModulation();

    /**
     * The modulation (e.g. FM, AM, etc) used by a particular broadcast service.
     *
     * @param broadcastSignalModulation Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2111">https://github.com/schemaorg/schemaorg/issues/2111</a>
     */
    void addBroadcastSignalModulation(Text broadcastSignalModulation);
    /**
     * The modulation (e.g. FM, AM, etc) used by a particular broadcast service.
     *
     * @param broadcastSignalModulation QualitativeValue value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2111">https://github.com/schemaorg/schemaorg/issues/2111</a>
     */
    void addBroadcastSignalModulation(QualitativeValue broadcastSignalModulation);

    /**
     * The subchannel used for the broadcast.
     *
     * @return {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2111">https://github.com/schemaorg/schemaorg/issues/2111</a>
     */
    List<Text> getBroadcastSubChannelList();

    /**
     * The subchannel used for the broadcast.
     *
     * @return {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2111">https://github.com/schemaorg/schemaorg/issues/2111</a>
     */
    Text getBroadcastSubChannel();

    /**
     * The subchannel used for the broadcast.
     *
     * @param broadcastSubChannel Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2111">https://github.com/schemaorg/schemaorg/issues/2111</a>
     */
    void addBroadcastSubChannel(Text broadcastSubChannel);

    /**
     * The frequency in MHz for a particular broadcast.
     *
     * @return {@link Number} or {@link QuantitativeValue}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1004">https://github.com/schemaorg/schemaorg/issues/1004</a>
     */
    <T> List<T> getBroadcastFrequencyValueList();

    /**
     * The frequency in MHz for a particular broadcast.
     *
     * @return {@link Number} or {@link QuantitativeValue}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1004">https://github.com/schemaorg/schemaorg/issues/1004</a>
     */
    <T> T getBroadcastFrequencyValue();

    /**
     * The frequency in MHz for a particular broadcast.
     *
     * @param broadcastFrequencyValue Number value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1004">https://github.com/schemaorg/schemaorg/issues/1004</a>
     */
    void addBroadcastFrequencyValue(Number broadcastFrequencyValue);
    /**
     * The frequency in MHz for a particular broadcast.
     *
     * @param broadcastFrequencyValue QuantitativeValue value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1004">https://github.com/schemaorg/schemaorg/issues/1004</a>
     */
    void addBroadcastFrequencyValue(QuantitativeValue broadcastFrequencyValue);
}
