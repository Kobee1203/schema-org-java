package com.weedow.schemaorg.generator.template;

import java.io.IOException;
import java.nio.file.Path;

public interface TemplateService {

    void apply(String templateName, Path outputFile, Object context) throws IOException;

}
