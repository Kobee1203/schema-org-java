package com.weedow.schemaorg.generator.template;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Service for applying Handlebars templates to generate files.
 * <p>
 * This service is responsible for compiling templates and rendering them
 * with the provided context to produce output files.
 */
public interface TemplateService {

    /**
     * Applies a template to generate an output file with the given context.
     *
     * @param templateName the name/location of the template to compile
     * @param outputFile the path where the generated file will be written
     * @param context the context object containing data for template rendering
     * @throws IOException if an error occurs during template processing or file writing
     */
    void apply(String templateName, Path outputFile, Object context) throws IOException;

}
