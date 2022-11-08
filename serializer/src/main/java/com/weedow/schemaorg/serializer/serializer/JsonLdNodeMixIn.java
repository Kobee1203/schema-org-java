package com.weedow.schemaorg.serializer.serializer;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface JsonLdNodeMixIn {

    @JsonProperty(JsonLdConstants.TYPE)
    String getType();

    @JsonProperty(JsonLdConstants.CONTEXT)
    String getContext();

    @JsonProperty(JsonLdConstants.CONTEXT)
    void setContext(final String value);

    @JsonProperty(JsonLdConstants.ID)
    String getId();

    @JsonProperty(JsonLdConstants.ID)
    void setId(final String value);

}
