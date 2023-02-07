package com.weedow.schemaorg.commons.model;

import java.util.*;

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

    protected static <T> List<T> add(List<T> list, T item) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(item);
        return list;
    }

    protected static <T> T getFirst(List<T> list) {
        return list != null && !list.isEmpty() ? list.get(0) : null;
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