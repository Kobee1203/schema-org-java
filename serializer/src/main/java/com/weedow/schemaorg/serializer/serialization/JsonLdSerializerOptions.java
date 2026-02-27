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

    /** Whether to format the output with indentation. */
    boolean prettyPrint;

    /** Extensions to be registered with ObjectMapper. */
    @Singular
    List<Module> modules;
}
