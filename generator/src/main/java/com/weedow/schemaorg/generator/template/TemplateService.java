package com.weedow.schemaorg.generator.template;

import java.io.File;
import java.io.IOException;

public interface TemplateService {

    void apply(String templateName, File outputFile, Object context) throws IOException;

}
