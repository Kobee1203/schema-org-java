/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.MusicComposition;
import spec.model.MusicGroup;
import spec.model.Person;
import spec.model.MusicPlaylist;
import spec.model.Duration;
import spec.model.datatype.Text;
import spec.model.MusicAlbum;

/**
 * A music recording (track), usually a single song.
 *
 * @see <a href="https://schema.org/MusicRecording">https://schema.org/MusicRecording</a>
 */
public interface MusicRecording extends CreativeWork {

    /**
     * The composition this track is a recording of.
     *
     * @return {@link MusicComposition}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    List<MusicComposition> getRecordingOfList();

    /**
     * The composition this track is a recording of.
     *
     * @return {@link MusicComposition}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    MusicComposition getRecordingOf();

    /**
     * The composition this track is a recording of.
     *
     * @param recordingOf MusicComposition value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addRecordingOf(MusicComposition recordingOf);

    /**
     * The artist that performed this album or recording.
     *
     * @return {@link MusicGroup} or {@link Person}
     */
    <T> List<T> getByArtistList();

    /**
     * The artist that performed this album or recording.
     *
     * @return {@link MusicGroup} or {@link Person}
     */
    <T> T getByArtist();

    /**
     * The artist that performed this album or recording.
     *
     * @param byArtist MusicGroup value to set.
     */
    void addByArtist(MusicGroup byArtist);
    /**
     * The artist that performed this album or recording.
     *
     * @param byArtist Person value to set.
     */
    void addByArtist(Person byArtist);

    /**
     * The playlist to which this recording belongs.
     *
     * @return {@link MusicPlaylist}
     */
    List<MusicPlaylist> getInPlaylistList();

    /**
     * The playlist to which this recording belongs.
     *
     * @return {@link MusicPlaylist}
     */
    MusicPlaylist getInPlaylist();

    /**
     * The playlist to which this recording belongs.
     *
     * @param inPlaylist MusicPlaylist value to set.
     */
    void addInPlaylist(MusicPlaylist inPlaylist);

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
     * The International Standard Recording Code for the recording.
     *
     * @return {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    List<Text> getIsrcCodeList();

    /**
     * The International Standard Recording Code for the recording.
     *
     * @return {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    Text getIsrcCode();

    /**
     * The International Standard Recording Code for the recording.
     *
     * @param isrcCode Text value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#MBZ</a>
     */
    void addIsrcCode(Text isrcCode);

    /**
     * The album to which this recording belongs.
     *
     * @return {@link MusicAlbum}
     */
    List<MusicAlbum> getInAlbumList();

    /**
     * The album to which this recording belongs.
     *
     * @return {@link MusicAlbum}
     */
    MusicAlbum getInAlbum();

    /**
     * The album to which this recording belongs.
     *
     * @param inAlbum MusicAlbum value to set.
     */
    void addInAlbum(MusicAlbum inAlbum);
}
