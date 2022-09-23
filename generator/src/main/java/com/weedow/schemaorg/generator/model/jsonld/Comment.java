package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {

    @JsonProperty("@language")
    private String language;

    @JsonProperty("@value")
    private String value;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Label{" +
                "language='" + language + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
