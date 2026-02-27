package com.weedow.schemaorg.generator.core.handler;

import com.weedow.schemaorg.generator.core.Context;

import java.nio.file.Path;

/**
 * Handler called when a template is successfully applied and a file is generated.
 * <p>
 * This handler can be used to perform post-generation actions such as logging,
 * validation, or further processing of generated files.
 */
public interface SuccessHandler {

    /**
     * Called when a template has been successfully applied to generate an output file.
     *
     * @param templateName the name/location of the template that was applied
     * @param outputFile the path to the generated output file
     * @param context the context containing the type and package information used for generation
     */
    void onSuccess(String templateName, Path outputFile, Context context);
}
