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

public class TemplateServiceImpl implements TemplateService {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateServiceImpl.class);

    private final Handlebars handlebars = handlebars();

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
        LOG.info("Generating '{}'...", outputFile);
        LOG.verbose("... from template '{}'...", templateLocation);
        long start = System.currentTimeMillis();
        try (final Writer writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            final Template template = handlebars.compile(templateLocation);
            template.apply(context, writer);
        }
        long end = System.currentTimeMillis();
        LOG.verbose("Generated: {} ms", (end - start));
    }
}
