package com.weedow.schemaorg.generator.core;

import com.weedow.schemaorg.generator.model.Type;
import lombok.Value;

import java.util.Set;

/**
 * Context information for code generation including type, package, and imports.
 */
@Value
public class Context {

    /** Type */
    Type type;
    /** Package */
    String usedPackage;
    /** Imports */
    Set<String> imports;
}
