package com.weedow.schemaorg.commons.model;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Implementation of {@link JsonLdNode}.
 *
 * @see <a href="https://json-ld.org/spec/latest/json-ld/#basic-concepts">Basic Concepts</a>)
 */
public class JsonLdNodeImpl implements JsonLdNode {

    private String context;

    private String id;

    @Override
    public String getContext() {
        return context;
    }

    @Override
    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getType() {
        final Class<? extends JsonLdNodeImpl> objectClass = getClass();
        final JsonLdTypeName jsonLdTypeName = objectClass.getAnnotation(JsonLdTypeName.class);
        return jsonLdTypeName != null && !jsonLdTypeName.value().isEmpty() ? jsonLdTypeName.value() : objectClass.getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonLdNodeImpl that = (JsonLdNodeImpl) o;
        return Objects.equals(context, that.context) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context, id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JsonLdNodeImpl.class.getSimpleName() + "[", "]")
                .add("context='" + context + "'")
                .add("id='" + id + "'")
                .toString();
    }
}