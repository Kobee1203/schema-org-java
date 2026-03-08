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

    /**
     * Returns the type name.
     * If the type name does not contain ":", the method adds the prefix SCHEMA_PREFIX.
     * Otherwise, the method returns the given type name.
     *
     * @param typeName type name
     * @return updated type name
     */
    public static String typeName(String typeName) {
        return typeName.contains(":") ? typeName : SCHEMA_PREFIX + typeName;
    }
}
