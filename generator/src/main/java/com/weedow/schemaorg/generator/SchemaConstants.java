package com.weedow.schemaorg.generator;

/**
 * Constants for Schema.org and RDF vocabulary identifiers.
 * <p>
 * These constants represent common RDF/Schema.org type identifiers used
 * throughout the code generation process.
 */
public final class SchemaConstants {

    private SchemaConstants() {
    }

    /** RDF Schema Class type identifier. */
    public static final String RDFS_CLASS = "rdfs:Class";

    /** Prefix for Schema.org vocabulary terms. */
    public static final String SCHEMA_PREFIX = "schema:";

    /** Schema.org DataType identifier. */
    public static final String SCHEMA_DATA_TYPE = SCHEMA_PREFIX + "DataType";

    /** Schema.org Enumeration identifier. */
    public static final String SCHEMA_ENUMERATION = SCHEMA_PREFIX + "Enumeration";

}
