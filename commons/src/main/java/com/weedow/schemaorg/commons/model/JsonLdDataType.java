package com.weedow.schemaorg.commons.model;

/**
 * Interface of JSON-LD data types.
 *
 * @param <T> the type of the value held by this data type
 * @see <a href="https://schema.org/DataType">https://schema.org/DataType</a>
 */
public interface JsonLdDataType<T> {

    /**
     * Gets the value of this data type.
     *
     * @return the value
     */
    T getValue();
}