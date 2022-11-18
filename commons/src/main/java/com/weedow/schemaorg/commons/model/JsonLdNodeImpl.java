package com.weedow.schemaorg.commons.model;

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
}