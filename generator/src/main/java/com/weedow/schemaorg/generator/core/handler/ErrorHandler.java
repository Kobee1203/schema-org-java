package com.weedow.schemaorg.generator.core.handler;

import com.weedow.schemaorg.generator.core.Context;

import java.nio.file.Path;

/**
 * Handler interface for code generation errors.
 */
public interface ErrorHandler {

    /**
     * Called when an error occurs during code generation.
     *
     * @param templateName the name of the template being processed
     * @param outputFile the output file path
     * @param context the generation context
     * @param e the exception that occurred
     */
    void onError(String templateName, Path outputFile, Context context, Exception e);
}
