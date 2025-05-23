/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.Language;
import spec_custom.model.datatype.Text;
import spec_custom.model.Movie;

/**
 * A screening of a movie or other video.
 *
 * @see <a href="https://schema.org/ScreeningEvent">https://schema.org/ScreeningEvent</a>
 */
public interface ScreeningEvent extends Event {

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
     * The type of screening or video broadcast used (e.g. IMAX, 3D, SD, HD, etc.).
     *
     * @return {@link Text}
     */
    List<Text> getVideoFormatList();

    /**
     * The type of screening or video broadcast used (e.g. IMAX, 3D, SD, HD, etc.).
     *
     * @return {@link Text}
     */
    Text getVideoFormat();

    /**
     * The type of screening or video broadcast used (e.g. IMAX, 3D, SD, HD, etc.).
     *
     * @param videoFormat Text value to set.
     */
    void addVideoFormat(Text videoFormat);

    /**
     * The movie presented during this event.
     *
     * @return {@link Movie}
     */
    List<Movie> getWorkPresentedList();

    /**
     * The movie presented during this event.
     *
     * @return {@link Movie}
     */
    Movie getWorkPresented();

    /**
     * The movie presented during this event.
     *
     * @param workPresented Movie value to set.
     */
    void addWorkPresented(Movie workPresented);
}
