package com.weedow.schemaorg.commons.model;


/**
 * Interface of JSON-LD node.
 * <p>
 * JSON-LD is a lightweight syntax to serialize Linked Data in JSON.
 *
 * @see <a href="https://json-ld.org/spec/latest/json-ld/#basic-concepts">Basic Concepts</a>)
 */
public interface JsonLdNode {

    /**
     * Returns the Schema.org type.
     *
     * @return the Schema.org type
     */
    String getType();

    /**
     * Returns the JSON-LD {@literal @context}.
     *
     * @return the JSON-LD {@literal @context}
     */
    String getContext();

    /**
     * Sets the JSON-LD {@literal @context} in current JSON-LD node.
     *
     * @param value of the JSON-LD {@literal @context}
     */
    void setContext(final String value);

    /**
     * Returns the value of {@literal @id}.
     * Null value means either {@literal @id} value is {@code null} or current JSON-LD node doesn't contain {@literal @id} value.
     *
     * @return the value of {@literal @id}
     */
    String getId();

    /**
     * Set value to {@literal @id} in current JSON-LD node.
     *
     * @param value of {@literal @id}
     */
    void setId(final String value);
}