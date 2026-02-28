package com.weedow.schemaorg.generator.parser;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * Configuration options for parsing Schema.org definitions.
 */
@Data
@Accessors(chain = true)
public final class ParserOptions {

    /**
     * The version of the Schema.org vocabulary to use (e.g., "13.0", "14.0").
     *
     * @return The version of the Schema.org vocabulary to use
     * @param schemaVersion The version of the Schema.org vocabulary to use
     */
    private String schemaVersion;

    /**
     * The resource path or URL to the Schema.org definition file.
     * <p>
     * This can be a classpath resource (e.g., "classpath:schemaorg-all-https.jsonld")
     * or an external URL.
     *
     * @return The resource path or URL to the Schema.org definition file
     * @param schemaResource The resource path or URL to the Schema.org definition file
     */
    private String schemaResource;

    /**
     * Whether to use Java primitive types for Schema.org data types.
     * <p>
     * When {@code true}, Schema.org types like Boolean, Integer, etc. will be mapped
     * to Java primitives (boolean, int, etc.). When {@code false}, wrapper types will be used.
     *
     * @return Whether to use Java primitive types for Schema.org data types
     * @param usedJavaTypes Whether to use Java primitive types for Schema.org data types
     */
    private boolean usedJavaTypes = false;

    /**
     * Custom mappings from Schema.org data type names to Java type names.
     * <p>
     * Allows overriding the default type mappings. The key is the Schema.org data type name,
     * and the value is the fully qualified Java type name.
     *
     * @return Custom mappings from Schema.org data type names to Java type names
     * @param customDataTypes Custom mappings from Schema.org data type names to Java type names
     */
    private Map<String, String> customDataTypes;
}
