/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.Person;
import spec_custom.model.datatype.Integer;
import spec_custom.model.datatype.Text;
import spec_custom.model.Episode;
import spec_custom.model.CreativeWorkSeason;
import spec_custom.model.datatype.Number;
import spec_custom.model.HyperTocEntry;
import spec_custom.model.CreativeWorkSeries;
import spec_custom.model.MusicGroup;

/**
 * A short TV or radio program or a segment/part of a program.
 *
 * @see <a href="https://schema.org/Clip">https://schema.org/Clip</a>
 */
public interface Clip extends CreativeWork {

    /**
     * An actor, e.g. in TV, radio, movie, video games etc. Actors can be associated with individual items or with a series, episode, clip.
     *
     * @return {@link Person}
     */
    List<Person> getActorsList();

    /**
     * An actor, e.g. in TV, radio, movie, video games etc. Actors can be associated with individual items or with a series, episode, clip.
     *
     * @return {@link Person}
     */
    Person getActors();

    /**
     * An actor, e.g. in TV, radio, movie, video games etc. Actors can be associated with individual items or with a series, episode, clip.
     *
     * @param actors Person value to set.
     */
    void addActors(Person actors);

    /**
     * An actor, e.g. in TV, radio, movie, video games etc., or in an event. Actors can be associated with individual items or with a series, episode, clip.
     *
     * @return {@link Person}
     */
    List<Person> getActorList();

    /**
     * An actor, e.g. in TV, radio, movie, video games etc., or in an event. Actors can be associated with individual items or with a series, episode, clip.
     *
     * @return {@link Person}
     */
    Person getActor();

    /**
     * An actor, e.g. in TV, radio, movie, video games etc., or in an event. Actors can be associated with individual items or with a series, episode, clip.
     *
     * @param actor Person value to set.
     */
    void addActor(Person actor);

    /**
     * Position of the clip within an ordered group of clips.
     *
     * @return {@link Integer} or {@link Text}
     */
    <T> List<T> getClipNumberList();

    /**
     * Position of the clip within an ordered group of clips.
     *
     * @return {@link Integer} or {@link Text}
     */
    <T> T getClipNumber();

    /**
     * Position of the clip within an ordered group of clips.
     *
     * @param clipNumber Integer value to set.
     */
    void addClipNumber(Integer clipNumber);
    /**
     * Position of the clip within an ordered group of clips.
     *
     * @param clipNumber Text value to set.
     */
    void addClipNumber(Text clipNumber);

    /**
     * The episode to which this clip belongs.
     *
     * @return {@link Episode}
     */
    List<Episode> getPartOfEpisodeList();

    /**
     * The episode to which this clip belongs.
     *
     * @return {@link Episode}
     */
    Episode getPartOfEpisode();

    /**
     * The episode to which this clip belongs.
     *
     * @param partOfEpisode Episode value to set.
     */
    void addPartOfEpisode(Episode partOfEpisode);

    /**
     * The season to which this episode belongs.
     *
     * @return {@link CreativeWorkSeason}
     */
    List<CreativeWorkSeason> getPartOfSeasonList();

    /**
     * The season to which this episode belongs.
     *
     * @return {@link CreativeWorkSeason}
     */
    CreativeWorkSeason getPartOfSeason();

    /**
     * The season to which this episode belongs.
     *
     * @param partOfSeason CreativeWorkSeason value to set.
     */
    void addPartOfSeason(CreativeWorkSeason partOfSeason);

    /**
     * The start time of the clip expressed as the number of seconds from the beginning of the work.
     *
     * @return {@link Number} or {@link HyperTocEntry}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2021">https://github.com/schemaorg/schemaorg/issues/2021</a>
     */
    <T> List<T> getStartOffsetList();

    /**
     * The start time of the clip expressed as the number of seconds from the beginning of the work.
     *
     * @return {@link Number} or {@link HyperTocEntry}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2021">https://github.com/schemaorg/schemaorg/issues/2021</a>
     */
    <T> T getStartOffset();

    /**
     * The start time of the clip expressed as the number of seconds from the beginning of the work.
     *
     * @param startOffset Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2021">https://github.com/schemaorg/schemaorg/issues/2021</a>
     */
    void addStartOffset(Number startOffset);
    /**
     * The start time of the clip expressed as the number of seconds from the beginning of the work.
     *
     * @param startOffset HyperTocEntry value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2021">https://github.com/schemaorg/schemaorg/issues/2021</a>
     */
    void addStartOffset(HyperTocEntry startOffset);

    /**
     * The series to which this episode or season belongs.
     *
     * @return {@link CreativeWorkSeries}
     */
    List<CreativeWorkSeries> getPartOfSeriesList();

    /**
     * The series to which this episode or season belongs.
     *
     * @return {@link CreativeWorkSeries}
     */
    CreativeWorkSeries getPartOfSeries();

    /**
     * The series to which this episode or season belongs.
     *
     * @param partOfSeries CreativeWorkSeries value to set.
     */
    void addPartOfSeries(CreativeWorkSeries partOfSeries);

    /**
     * The end time of the clip expressed as the number of seconds from the beginning of the work.
     *
     * @return {@link Number} or {@link HyperTocEntry}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2021">https://github.com/schemaorg/schemaorg/issues/2021</a>
     */
    <T> List<T> getEndOffsetList();

    /**
     * The end time of the clip expressed as the number of seconds from the beginning of the work.
     *
     * @return {@link Number} or {@link HyperTocEntry}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2021">https://github.com/schemaorg/schemaorg/issues/2021</a>
     */
    <T> T getEndOffset();

    /**
     * The end time of the clip expressed as the number of seconds from the beginning of the work.
     *
     * @param endOffset Number value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2021">https://github.com/schemaorg/schemaorg/issues/2021</a>
     */
    void addEndOffset(Number endOffset);
    /**
     * The end time of the clip expressed as the number of seconds from the beginning of the work.
     *
     * @param endOffset HyperTocEntry value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2021">https://github.com/schemaorg/schemaorg/issues/2021</a>
     */
    void addEndOffset(HyperTocEntry endOffset);

    /**
     * A director of e.g. TV, radio, movie, video gaming etc. content, or of an event. Directors can be associated with individual items or with a series, episode, clip.
     *
     * @return {@link Person}
     */
    List<Person> getDirectorList();

    /**
     * A director of e.g. TV, radio, movie, video gaming etc. content, or of an event. Directors can be associated with individual items or with a series, episode, clip.
     *
     * @return {@link Person}
     */
    Person getDirector();

    /**
     * A director of e.g. TV, radio, movie, video gaming etc. content, or of an event. Directors can be associated with individual items or with a series, episode, clip.
     *
     * @param director Person value to set.
     */
    void addDirector(Person director);

    /**
     * A director of e.g. TV, radio, movie, video games etc. content. Directors can be associated with individual items or with a series, episode, clip.
     *
     * @return {@link Person}
     */
    List<Person> getDirectorsList();

    /**
     * A director of e.g. TV, radio, movie, video games etc. content. Directors can be associated with individual items or with a series, episode, clip.
     *
     * @return {@link Person}
     */
    Person getDirectors();

    /**
     * A director of e.g. TV, radio, movie, video games etc. content. Directors can be associated with individual items or with a series, episode, clip.
     *
     * @param directors Person value to set.
     */
    void addDirectors(Person directors);

    /**
     * The composer of the soundtrack.
     *
     * @return {@link MusicGroup} or {@link Person}
     */
    <T> List<T> getMusicByList();

    /**
     * The composer of the soundtrack.
     *
     * @return {@link MusicGroup} or {@link Person}
     */
    <T> T getMusicBy();

    /**
     * The composer of the soundtrack.
     *
     * @param musicBy MusicGroup value to set.
     */
    void addMusicBy(MusicGroup musicBy);
    /**
     * The composer of the soundtrack.
     *
     * @param musicBy Person value to set.
     */
    void addMusicBy(Person musicBy);
}
