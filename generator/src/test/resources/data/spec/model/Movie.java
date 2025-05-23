/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.Person;
import spec.model.datatype.URL;
import spec.model.datatype.Text;
import spec.model.Language;
import spec.model.VideoObject;
import spec.model.Duration;
import spec.model.Organization;
import spec.model.Country;
import spec.model.MusicGroup;

/**
 * A movie.
 *
 * @see <a href="https://schema.org/Movie">https://schema.org/Movie</a>
 */
public interface Movie extends CreativeWork {

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
     * An [EIDR](https://eidr.org/) (Entertainment Identifier Registry) [[identifier]] representing at the most general/abstract level, a work of film or television.
     * 
     * For example, the motion picture known as "Ghostbusters" has a titleEIDR of  "10.5240/7EC7-228A-510A-053E-CBB8-J". This title (or work) may have several variants, which EIDR calls "edits". See [[editEIDR]].
     * 
     * Since schema.org types like [[Movie]] and [[TVEpisode]] can be used for both works and their multiple expressions, it is possible to use [[titleEIDR]] alone (for a general description), or alongside [[editEIDR]] for a more edit-specific description.
     *
     * @return {@link URL} or {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2469">https://github.com/schemaorg/schemaorg/issues/2469</a>
     */
    <T> List<T> getTitleEIDRList();

    /**
     * An [EIDR](https://eidr.org/) (Entertainment Identifier Registry) [[identifier]] representing at the most general/abstract level, a work of film or television.
     * 
     * For example, the motion picture known as "Ghostbusters" has a titleEIDR of  "10.5240/7EC7-228A-510A-053E-CBB8-J". This title (or work) may have several variants, which EIDR calls "edits". See [[editEIDR]].
     * 
     * Since schema.org types like [[Movie]] and [[TVEpisode]] can be used for both works and their multiple expressions, it is possible to use [[titleEIDR]] alone (for a general description), or alongside [[editEIDR]] for a more edit-specific description.
     *
     * @return {@link URL} or {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2469">https://github.com/schemaorg/schemaorg/issues/2469</a>
     */
    <T> T getTitleEIDR();

    /**
     * An [EIDR](https://eidr.org/) (Entertainment Identifier Registry) [[identifier]] representing at the most general/abstract level, a work of film or television.
     * 
     * For example, the motion picture known as "Ghostbusters" has a titleEIDR of  "10.5240/7EC7-228A-510A-053E-CBB8-J". This title (or work) may have several variants, which EIDR calls "edits". See [[editEIDR]].
     * 
     * Since schema.org types like [[Movie]] and [[TVEpisode]] can be used for both works and their multiple expressions, it is possible to use [[titleEIDR]] alone (for a general description), or alongside [[editEIDR]] for a more edit-specific description.
     *
     * @param titleEIDR URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2469">https://github.com/schemaorg/schemaorg/issues/2469</a>
     */
    void addTitleEIDR(URL titleEIDR);
    /**
     * An [EIDR](https://eidr.org/) (Entertainment Identifier Registry) [[identifier]] representing at the most general/abstract level, a work of film or television.
     * 
     * For example, the motion picture known as "Ghostbusters" has a titleEIDR of  "10.5240/7EC7-228A-510A-053E-CBB8-J". This title (or work) may have several variants, which EIDR calls "edits". See [[editEIDR]].
     * 
     * Since schema.org types like [[Movie]] and [[TVEpisode]] can be used for both works and their multiple expressions, it is possible to use [[titleEIDR]] alone (for a general description), or alongside [[editEIDR]] for a more edit-specific description.
     *
     * @param titleEIDR Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2469">https://github.com/schemaorg/schemaorg/issues/2469</a>
     */
    void addTitleEIDR(Text titleEIDR);

    /**
     * Languages in which subtitles/captions are available, in [IETF BCP 47 standard format](http://tools.ietf.org/html/bcp47).
     *
     * @return {@link Language} or {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2110">https://github.com/schemaorg/schemaorg/issues/2110</a>
     */
    <T> List<T> getSubtitleLanguageList();

    /**
     * Languages in which subtitles/captions are available, in [IETF BCP 47 standard format](http://tools.ietf.org/html/bcp47).
     *
     * @return {@link Language} or {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2110">https://github.com/schemaorg/schemaorg/issues/2110</a>
     */
    <T> T getSubtitleLanguage();

    /**
     * Languages in which subtitles/captions are available, in [IETF BCP 47 standard format](http://tools.ietf.org/html/bcp47).
     *
     * @param subtitleLanguage Language value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2110">https://github.com/schemaorg/schemaorg/issues/2110</a>
     */
    void addSubtitleLanguage(Language subtitleLanguage);
    /**
     * Languages in which subtitles/captions are available, in [IETF BCP 47 standard format](http://tools.ietf.org/html/bcp47).
     *
     * @param subtitleLanguage Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2110">https://github.com/schemaorg/schemaorg/issues/2110</a>
     */
    void addSubtitleLanguage(Text subtitleLanguage);

    /**
     * The trailer of a movie or TV/radio series, season, episode, etc.
     *
     * @return {@link VideoObject}
     */
    List<VideoObject> getTrailerList();

    /**
     * The trailer of a movie or TV/radio series, season, episode, etc.
     *
     * @return {@link VideoObject}
     */
    VideoObject getTrailer();

    /**
     * The trailer of a movie or TV/radio series, season, episode, etc.
     *
     * @param trailer VideoObject value to set.
     */
    void addTrailer(VideoObject trailer);

    /**
     * The duration of the item (movie, audio recording, event, etc.) in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601).
     *
     * @return {@link Duration}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1457">https://github.com/schemaorg/schemaorg/issues/1457</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1698">https://github.com/schemaorg/schemaorg/issues/1698</a>
     */
    List<Duration> getDurationList();

    /**
     * The duration of the item (movie, audio recording, event, etc.) in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601).
     *
     * @return {@link Duration}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1457">https://github.com/schemaorg/schemaorg/issues/1457</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1698">https://github.com/schemaorg/schemaorg/issues/1698</a>
     */
    Duration getDuration();

    /**
     * The duration of the item (movie, audio recording, event, etc.) in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601).
     *
     * @param duration Duration value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1457">https://github.com/schemaorg/schemaorg/issues/1457</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1698">https://github.com/schemaorg/schemaorg/issues/1698</a>
     */
    void addDuration(Duration duration);

    /**
     * The production company or studio responsible for the item, e.g. series, video game, episode etc.
     *
     * @return {@link Organization}
     */
    List<Organization> getProductionCompanyList();

    /**
     * The production company or studio responsible for the item, e.g. series, video game, episode etc.
     *
     * @return {@link Organization}
     */
    Organization getProductionCompany();

    /**
     * The production company or studio responsible for the item, e.g. series, video game, episode etc.
     *
     * @param productionCompany Organization value to set.
     */
    void addProductionCompany(Organization productionCompany);

    /**
     * The country of origin of something, including products as well as creative  works such as movie and TV content.
     * 
     * In the case of TV and movie, this would be the country of the principle offices of the production company or individual responsible for the movie. For other kinds of [[CreativeWork]] it is difficult to provide fully general guidance, and properties such as [[contentLocation]] and [[locationCreated]] may be more applicable.
     * 
     * In the case of products, the country of origin of the product. The exact interpretation of this may vary by context and product type, and cannot be fully enumerated here.
     *
     * @return {@link Country}
     */
    List<Country> getCountryOfOriginList();

    /**
     * The country of origin of something, including products as well as creative  works such as movie and TV content.
     * 
     * In the case of TV and movie, this would be the country of the principle offices of the production company or individual responsible for the movie. For other kinds of [[CreativeWork]] it is difficult to provide fully general guidance, and properties such as [[contentLocation]] and [[locationCreated]] may be more applicable.
     * 
     * In the case of products, the country of origin of the product. The exact interpretation of this may vary by context and product type, and cannot be fully enumerated here.
     *
     * @return {@link Country}
     */
    Country getCountryOfOrigin();

    /**
     * The country of origin of something, including products as well as creative  works such as movie and TV content.
     * 
     * In the case of TV and movie, this would be the country of the principle offices of the production company or individual responsible for the movie. For other kinds of [[CreativeWork]] it is difficult to provide fully general guidance, and properties such as [[contentLocation]] and [[locationCreated]] may be more applicable.
     * 
     * In the case of products, the country of origin of the product. The exact interpretation of this may vary by context and product type, and cannot be fully enumerated here.
     *
     * @param countryOfOrigin Country value to set.
     */
    void addCountryOfOrigin(Country countryOfOrigin);

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
