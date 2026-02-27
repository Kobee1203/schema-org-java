package com.weedow.schemaorg.serializer;

/**
 * Constants for JSON-LD keywords and namespaces.
 */
public final class JsonLdConstants {

    private JsonLdConstants() {
    }

    /** JSON-LD @base keyword. */
    public static final String BASE = "@base";

    /** JSON-LD @context keyword. */
    public static final String CONTEXT = "@context";

    /** JSON-LD @id keyword. */
    public static final String ID = "@id";

    /** JSON-LD @reverse keyword. */
    public static final String REVERSE = "@reverse";

    /** JSON-LD @type keyword. */
    public static final String TYPE = "@type";

    /** Schema.org core namespace URI. */
    public static final String CORE_NAMESPACE = "https://schema.org";
}
