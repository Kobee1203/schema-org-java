/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model.datatype;

import com.weedow.schemaorg.commons.model.JsonLdTypeName;

/**
 * A date value in [ISO 8601 date format](http://en.wikipedia.org/wiki/ISO_8601).
 *
 * @see <a href="https://schema.org/Date">https://schema.org/Date</a>
 */
@JsonLdTypeName("Date")
public class Date extends DataType<java.time.LocalDate> {

    Date(java.time.LocalDate value) {
        super(value);
    }

    public static Date of(java.time.LocalDate value) {
        return new Date(value);
    }

}
