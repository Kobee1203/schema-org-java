package com.weedow.schemaorg.generator.core;

/**
 * Main interface for generating Java classes from Schema.org type definitions.
 * <p>
 * This generator is responsible for creating Java interfaces and implementation classes
 * for Schema.org types, including:
 * <ul>
 *   <li>Model interfaces (for Schema.org classes)</li>
 *   <li>Model implementations (concrete classes)</li>
 *   <li>Data types (for Schema.org data types)</li>
 *   <li>Enumerations (for Schema.org enumeration types)</li>
 * </ul>
 */
public interface SchemaModelGenerator {

    /**
     * Generates all Java files for the configured Schema.org types.
     * <p>
     * The generation process includes:
     * <ul>
     *   <li>Creating output directories if they don't exist</li>
     *   <li>Copying common model classes (if configured)</li>
     *   <li>Generating property file with package configuration</li>
     *   <li>Applying templates to generate type interfaces and implementations</li>
     *   <li>Invoking success/error/complete handlers as appropriate</li>
     * </ul>
     */
    void generate();

}
