package com.weedow.schemaorg.generator.template;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.weedow.schemaorg.generator.logging.Logger;
import com.weedow.schemaorg.generator.logging.LoggerFactory;
import com.weedow.schemaorg.generator.template.helper.CharSequenceHelpers;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Default implementation of {@link TemplateService} using Handlebars template engine.
 * <p>
 * This service is configured with various Handlebars helpers for string manipulation,
 * conditional logic, and character sequence operations.
 */
public class TemplateServiceImpl implements TemplateService {

    /** Default constructor */
    public TemplateServiceImpl() {
        // empty
    }

    private static final Logger LOG = LoggerFactory.getLogger(TemplateServiceImpl.class);

    private final Handlebars handlebars = handlebars();

    /**
     * Creates and configures a Handlebars instance with registered helpers.
     * <p>
     * The Handlebars instance is configured with:
     * <ul>
     *   <li>Pretty printing enabled</li>
     *   <li>String manipulation helpers</li>
     *   <li>Conditional logic helpers</li>
     *   <li>CharSequence helpers</li>
     * </ul>
     *
     * @return a configured Handlebars instance
     */
    private static Handlebars handlebars() {
        final Handlebars handlebars = new Handlebars();
        handlebars.setPrettyPrint(true);
        handlebars.registerHelpers(StringHelpers.class);
        handlebars.registerHelpers(ConditionalHelpers.class);
        handlebars.registerHelpers(CharSequenceHelpers.class);
        return handlebars;
    }

    @Override
    public void apply(String templateLocation, Path outputFile, Object context) throws IOException {
        LOG.verbose("Generating '{}'", outputFile);
        LOG.verbose("... from template '{}'", templateLocation);
        long start = System.currentTimeMillis();
        try (final Writer writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            final Template template = handlebars.compile(templateLocation);
            template.apply(context, writer);
        }
        long end = System.currentTimeMillis();
        LOG.verbose("Generated: {} ms", (end - start));
    }
}
