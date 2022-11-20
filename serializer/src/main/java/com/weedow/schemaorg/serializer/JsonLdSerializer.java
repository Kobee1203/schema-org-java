package com.weedow.schemaorg.serializer;

import com.weedow.schemaorg.commons.model.JsonLdNode;

import java.util.List;

/**
 * Converts Schema.org objects to JSON-LD.
 * <p>
 * An example for serialization:
 * <pre>
 * {@code JsonLdSerializer serializer = new JsonLdSerializerImpl();
 *
 * Thing thing = new ThingImpl();
 * thing.setId("my_id");
 * thing.setName(Text.of("My Thing"));
 * thing.setDescription(Text.of("This is my thing."));
 * thing.setUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java")));
 *
 * String jsonldStr = serializer.serialize(thing); }
 * </pre>
 *
 * <p>The serialized JSON-LD string should look like:
 *
 * <pre>
 * {
 *   "@context": "https://schema.org",
 *   "@type": "Thing",
 *   "name": "name"
 * }</pre>
 * <p>
 * If there are more than one objects to be serialized, the overloaded method{@code serialize(List<? extends Thing> objects)} could also be called.
 * <p>
 * <b>Warning: serializing process will remove the outer brackets([]) in generated JSON-LD if the {@code objects} list only contains one object.</b>
 */
public interface JsonLdSerializer extends Serializer<JsonLdNode> {

    String APPLICATION_JSON_LD_TYPE = "application/ld+json";

    @Override
    default String getMediaType() {
        return APPLICATION_JSON_LD_TYPE;
    }

    /**
     * Serializes one schema.org object.
     *
     * @param object schema.org object to serialize
     * @return A String representing the given serialized schema.org object
     */
    String serialize(JsonLdNode object) throws JsonLdException;

    /**
     * Serializes a list of schema.org objects.
     *
     * @param objects schema.org object to serialize
     * @return A String representing the given serialized schema.org objects
     */
    String serialize(List<? extends JsonLdNode> objects) throws JsonLdException;
}
