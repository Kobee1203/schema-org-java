package com.weedow.schemaorg.commons.model;

import java.util.ArrayList;
import java.util.List;
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

    /** Default Constructor */
    public JsonLdNodeImpl() {
        // empty
    }

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

    /**
     * Adds an item to a list, creating the list if it doesn't exist.
     *
     * @param <T> the type of elements in the list
     * @param list the list to add to, may be null
     * @param item the item to add
     * @return the list containing the item
     */
    protected static <T> List<T> add(List<T> list, T item) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(item);
        return list;
    }

    /**
     * Gets the first element from a list.
     *
     * @param <T> the type of elements in the list
     * @param list the list to get the first element from
     * @return the first element or null if the list is null or empty
     */
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