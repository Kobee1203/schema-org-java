package com.weedow.schemaorg.serializer.serialization;

import com.fasterxml.jackson.databind.Module;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

/**
 * Configuration options for JSON-LD serialization.
 */
@Value
@Builder
public class JsonLdSerializerOptions {

    /**
     * Whether to format the output with indentation.
     *
     * @return {@code true} or {@code false}
     */
    boolean prettyPrint;

    /**
     * Jackson modules to add to the object mapper.
     *
     * @return List of Modules
     * */
    @Singular
    List<Module> modules;
}
