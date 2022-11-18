package com.weedow.schemaorg.commons.model;

/**
 * Interface of JSON-LD data types.
 *
 * @see <a href="https://schema.org/DataType">https://schema.org/DataType</a>
 */
public interface JsonLdDataType<T> {

    T getValue();
}