/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.Person;
import spec.model.MediaObject;
import spec.model.datatype.Text;
import spec.model.ImageObject;
import spec.model.MusicGroup;

/**
 * A video file.
 *
 * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews</a>
 * @see <a href="https://schema.org/VideoObject">https://schema.org/VideoObject</a>
 */
public interface VideoObject extends MediaObject {

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
     * The caption for this object. For downloadable machine formats (closed caption, subtitles etc.) use MediaObject and indicate the [[encodingFormat]].
     *
     * @return {@link MediaObject} or {@link Text}
     */
    <T> List<T> getCaptionList();

    /**
     * The caption for this object. For downloadable machine formats (closed caption, subtitles etc.) use MediaObject and indicate the [[encodingFormat]].
     *
     * @return {@link MediaObject} or {@link Text}
     */
    <T> T getCaption();

    /**
     * The caption for this object. For downloadable machine formats (closed caption, subtitles etc.) use MediaObject and indicate the [[encodingFormat]].
     *
     * @param caption MediaObject value to set.
     */
    void addCaption(MediaObject caption);
    /**
     * The caption for this object. For downloadable machine formats (closed caption, subtitles etc.) use MediaObject and indicate the [[encodingFormat]].
     *
     * @param caption Text value to set.
     */
    void addCaption(Text caption);

    /**
     * Thumbnail image for an image or video.
     *
     * @return {@link ImageObject}
     */
    List<ImageObject> getThumbnailList();

    /**
     * Thumbnail image for an image or video.
     *
     * @return {@link ImageObject}
     */
    ImageObject getThumbnail();

    /**
     * Thumbnail image for an image or video.
     *
     * @param thumbnail ImageObject value to set.
     */
    void addThumbnail(ImageObject thumbnail);

    /**
     * Represents textual captioning from a [[MediaObject]], e.g. text of a 'meme'.
     *
     * @return {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2450">https://github.com/schemaorg/schemaorg/issues/2450</a>
     */
    List<Text> getEmbeddedTextCaptionList();

    /**
     * Represents textual captioning from a [[MediaObject]], e.g. text of a 'meme'.
     *
     * @return {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2450">https://github.com/schemaorg/schemaorg/issues/2450</a>
     */
    Text getEmbeddedTextCaption();

    /**
     * Represents textual captioning from a [[MediaObject]], e.g. text of a 'meme'.
     *
     * @param embeddedTextCaption Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2450">https://github.com/schemaorg/schemaorg/issues/2450</a>
     */
    void addEmbeddedTextCaption(Text embeddedTextCaption);

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
     * The frame size of the video.
     *
     * @return {@link Text}
     */
    List<Text> getVideoFrameSizeList();

    /**
     * The frame size of the video.
     *
     * @return {@link Text}
     */
    Text getVideoFrameSize();

    /**
     * The frame size of the video.
     *
     * @param videoFrameSize Text value to set.
     */
    void addVideoFrameSize(Text videoFrameSize);

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
     * If this MediaObject is an AudioObject or VideoObject, the transcript of that object.
     *
     * @return {@link Text}
     */
    List<Text> getTranscriptList();

    /**
     * If this MediaObject is an AudioObject or VideoObject, the transcript of that object.
     *
     * @return {@link Text}
     */
    Text getTranscript();

    /**
     * If this MediaObject is an AudioObject or VideoObject, the transcript of that object.
     *
     * @param transcript Text value to set.
     */
    void addTranscript(Text transcript);

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

    /**
     * The quality of the video.
     *
     * @return {@link Text}
     */
    List<Text> getVideoQualityList();

    /**
     * The quality of the video.
     *
     * @return {@link Text}
     */
    Text getVideoQuality();

    /**
     * The quality of the video.
     *
     * @param videoQuality Text value to set.
     */
    void addVideoQuality(Text videoQuality);
}
