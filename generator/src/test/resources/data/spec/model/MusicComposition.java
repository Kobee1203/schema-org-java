/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.datatype.Text;
import spec.model.CreativeWork;
import spec.model.MusicComposition;
import spec.model.MusicRecording;
import spec.model.Organization;
import spec.model.Person;
import spec.model.Event;

/**
 * A musical composition.
 *
 * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
 * @see <a href="https://schema.org/MusicComposition">https://schema.org/MusicComposition</a>
 */
public interface MusicComposition extends CreativeWork {

    /**
     * The International Standard Musical Work Code for the composition.
     *
     * @return {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    List<Text> getIswcCodeList();

    /**
     * The International Standard Musical Work Code for the composition.
     *
     * @return {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    Text getIswcCode();

    /**
     * The International Standard Musical Work Code for the composition.
     *
     * @param iswcCode Text value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addIswcCode(Text iswcCode);

    /**
     * The words in the song.
     *
     * @return {@link CreativeWork}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    List<CreativeWork> getLyricsList();

    /**
     * The words in the song.
     *
     * @return {@link CreativeWork}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    CreativeWork getLyrics();

    /**
     * The words in the song.
     *
     * @param lyrics CreativeWork value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addLyrics(CreativeWork lyrics);

    /**
     * The key, mode, or scale this composition uses.
     *
     * @return {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    List<Text> getMusicalKeyList();

    /**
     * The key, mode, or scale this composition uses.
     *
     * @return {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    Text getMusicalKey();

    /**
     * The key, mode, or scale this composition uses.
     *
     * @param musicalKey Text value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addMusicalKey(Text musicalKey);

    /**
     * Smaller compositions included in this work (e.g. a movement in a symphony).
     *
     * @return {@link MusicComposition}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    List<MusicComposition> getIncludedCompositionList();

    /**
     * Smaller compositions included in this work (e.g. a movement in a symphony).
     *
     * @return {@link MusicComposition}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    MusicComposition getIncludedComposition();

    /**
     * Smaller compositions included in this work (e.g. a movement in a symphony).
     *
     * @param includedComposition MusicComposition value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addIncludedComposition(MusicComposition includedComposition);

    /**
     * An audio recording of the work.
     *
     * @return {@link MusicRecording}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    List<MusicRecording> getRecordedAsList();

    /**
     * An audio recording of the work.
     *
     * @return {@link MusicRecording}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    MusicRecording getRecordedAs();

    /**
     * An audio recording of the work.
     *
     * @param recordedAs MusicRecording value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addRecordedAs(MusicRecording recordedAs);

    /**
     * The person or organization who wrote a composition, or who is the composer of a work performed at some event.
     *
     * @return {@link Organization} or {@link Person}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    <T> List<T> getComposerList();

    /**
     * The person or organization who wrote a composition, or who is the composer of a work performed at some event.
     *
     * @return {@link Organization} or {@link Person}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    <T> T getComposer();

    /**
     * The person or organization who wrote a composition, or who is the composer of a work performed at some event.
     *
     * @param composer Organization value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addComposer(Organization composer);
    /**
     * The person or organization who wrote a composition, or who is the composer of a work performed at some event.
     *
     * @param composer Person value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addComposer(Person composer);

    /**
     * The type of composition (e.g. overture, sonata, symphony, etc.).
     *
     * @return {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    List<Text> getMusicCompositionFormList();

    /**
     * The type of composition (e.g. overture, sonata, symphony, etc.).
     *
     * @return {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    Text getMusicCompositionForm();

    /**
     * The type of composition (e.g. overture, sonata, symphony, etc.).
     *
     * @param musicCompositionForm Text value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addMusicCompositionForm(Text musicCompositionForm);

    /**
     * The date and place the work was first performed.
     *
     * @return {@link Event}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    List<Event> getFirstPerformanceList();

    /**
     * The date and place the work was first performed.
     *
     * @return {@link Event}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    Event getFirstPerformance();

    /**
     * The date and place the work was first performed.
     *
     * @param firstPerformance Event value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addFirstPerformance(Event firstPerformance);

    /**
     * The person who wrote the words.
     *
     * @return {@link Person}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    List<Person> getLyricistList();

    /**
     * The person who wrote the words.
     *
     * @return {@link Person}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    Person getLyricist();

    /**
     * The person who wrote the words.
     *
     * @param lyricist Person value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addLyricist(Person lyricist);

    /**
     * An arrangement derived from the composition.
     *
     * @return {@link MusicComposition}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    List<MusicComposition> getMusicArrangementList();

    /**
     * An arrangement derived from the composition.
     *
     * @return {@link MusicComposition}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    MusicComposition getMusicArrangement();

    /**
     * An arrangement derived from the composition.
     *
     * @param musicArrangement MusicComposition value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addMusicArrangement(MusicComposition musicArrangement);
}
