package com.weedow.schemaorg.serializer;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jackson MixIn for JsonLdNode that maps Java properties to JSON-LD keywords.
 */
public interface JsonLdNodeMixIn {

    /**
     * Maps the type property to @type.
     *
     * @return Type
     */
    @JsonProperty(JsonLdConstants.TYPE)
    String getType();

    /**
     * Maps the context property to @context.
     *
     * @return Context
     */
    @JsonProperty(JsonLdConstants.CONTEXT)
    String getContext();

    /**
     * Maps the context property to @context.
     *
     * @param value context value to set
     */
    @JsonProperty(JsonLdConstants.CONTEXT)
    void setContext(final String value);

    /**
     * Maps the id property to @id.
     *
     * @return id
     */
    @JsonProperty(JsonLdConstants.ID)
    String getId();

    /**
     * Maps the id property to @id.
     *
     * @param value id value to set
     */
    @JsonProperty(JsonLdConstants.ID)
    void setId(final String value);

}
