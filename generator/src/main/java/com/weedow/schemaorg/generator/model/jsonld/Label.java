package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Label {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return Objects.equals(language, label.language) && Objects.equals(value, label.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, value);
    }

    @Override
    public String toString() {
        return "Label{" +
                "language='" + language + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
