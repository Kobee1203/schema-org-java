/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model;

import java.util.List;
import spec_custom.model.Duration;
import spec_custom.model.Person;

/**
 * An audiobook.
 *
 * @see <a href="https://bib.schema.org">https://bib.schema.org</a>
 * @see <a href="https://schema.org/Audiobook">https://schema.org/Audiobook</a>
 */
public interface Audiobook extends AudioObject, Book {

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
     * A person who reads (performs) the audiobook.
     *
     * @return {@link Person}
     * @see <a href="https://bib.schema.org">https://bib.schema.org</a>
     */
    List<Person> getReadByList();

    /**
     * A person who reads (performs) the audiobook.
     *
     * @return {@link Person}
     * @see <a href="https://bib.schema.org">https://bib.schema.org</a>
     */
    Person getReadBy();

    /**
     * A person who reads (performs) the audiobook.
     *
     * @param readBy Person value to set.
     * @see <a href="https://bib.schema.org">https://bib.schema.org</a>
     */
    void addReadBy(Person readBy);
}
