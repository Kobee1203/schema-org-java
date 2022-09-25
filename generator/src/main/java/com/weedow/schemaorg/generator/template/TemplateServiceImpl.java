package com.weedow.schemaorg.generator.template;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
 import com.weedow.schemaorg.generator.template.helper.CharSequenceHelpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    public void apply(String templateName, File outputFile, Object context) throws IOException {
        LOG.info("Generating '{}' from template '{}'", outputFile, templateName);
        long start = System.currentTimeMillis();
        try (final FileWriter writer = new FileWriter(outputFile)) {
            final Template tplJsonLdTypeName = handlebars.compile(templateName);
            tplJsonLdTypeName.apply(context, writer);
        }
        long end = System.currentTimeMillis();
        LOG.info("Generated: {} ms", (end - start));
    }
}
