/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.datatype.Text;
import org.schema.model.MediaObject;

/**
 * An audio file.
 *
 * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews</a>
 * @see <a href="https://schema.org/AudioObject">https://schema.org/AudioObject</a>
 */
public interface AudioObject extends MediaObject {

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
    void setEmbeddedTextCaption(Text embeddedTextCaption);

    /**
     * The caption for this object. For downloadable machine formats (closed caption, subtitles etc.) use MediaObject and indicate the [[encodingFormat]].
     *
     * @return {@link Text} or {@link MediaObject}
     */
    <T> T getCaption();

    /**
     * The caption for this object. For downloadable machine formats (closed caption, subtitles etc.) use MediaObject and indicate the [[encodingFormat]].
     *
     * @param caption Text value to set.
     */
    void setCaption(Text caption);
    /**
     * The caption for this object. For downloadable machine formats (closed caption, subtitles etc.) use MediaObject and indicate the [[encodingFormat]].
     *
     * @param caption MediaObject value to set.
     */
    void setCaption(MediaObject caption);

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
    void setTranscript(Text transcript);
}
