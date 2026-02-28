package com.weedow.schemaorg.serializer.deserialization;

import com.fasterxml.jackson.databind.Module;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

/**
 * Configuration options for JSON-LD deserialization.
 */
@Value
@Builder
public class JsonLdDeserializerOptions {

    /**
     * Jackson modules to add to the object mapper.
     *
     * @return List of Modules
     * */
    @Singular
    List<Module> modules;
}
