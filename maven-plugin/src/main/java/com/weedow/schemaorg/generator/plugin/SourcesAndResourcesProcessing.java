package com.weedow.schemaorg.generator.plugin;

/**
 * Enum to specify the behavior of the plugin with the generated java types and generated resources.
 */
public enum SourcesAndResourcesProcessing {
    /** The output directory with the generated java types and generated resources are not added to the project. */
    NOTHING,
    /** Add the output directory to the project as a <b>source root</b>, so that the generated java are compiled and included in the project artifact. */
    SOURCES_AND_RESOURCES,
    /** Add the output directory to the project as a <b>test source root</b>. */
    TEST_SOURCES_AND_RESOURCES
}
