package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.generator.model.Type;
import lombok.Value;

import java.util.Set;

/**
 * Context information for code generation including type, package, and imports.
 */
@Value
public class Context {

    /**
     * Type.
     *
     * @return The type
     */
    Type type;
    /**
     * Package.
     *
     * @return The package
     */
    String usedPackage;
    /**
     * Imports.
     *
     * @return The imports
     */
    Set<String> imports;
}
