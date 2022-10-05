package com.weedow.schemaorg.generator.model.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(language, comment.language) && Objects.equals(value, comment.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, value);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "language='" + language + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
