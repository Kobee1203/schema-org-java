package com.weedow.schemaorg.serializer.deserialization;

import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.serializer.JsonLdException;

/**
 * Converts Schema.org objects from a String.
 */
public interface JsonLdDeserializer extends Deserializer<JsonLdNode> {

    /**
     * Deserializes the JSON-LD string into schema.org objects.
     *
     * @param json JSON-LD String to deserialize
     * @return a JsonLdNode object resulting from the given deserialized String
     * @throws JsonLdException if the String cannot be deserialized
     */
    @Override
    <T extends JsonLdNode> T deserialize(String json) throws JsonLdException;

}
