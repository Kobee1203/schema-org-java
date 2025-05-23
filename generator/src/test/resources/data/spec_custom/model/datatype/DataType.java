/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec_custom.model.datatype;


/**
 * The basic data types such as Integers, Strings, etc.
 *
 * @see <a href="https://schema.org/DataType">https://schema.org/DataType</a>
 */
public abstract class DataType<T> implements com.weedow.schemaorg.commons.model.JsonLdDataType<T> {

    private final T value;

    DataType(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(getClass().getSimpleName())
                .append("{")
                .append("value=").append(value)
                .append('}')
                .toString();
    }
}
