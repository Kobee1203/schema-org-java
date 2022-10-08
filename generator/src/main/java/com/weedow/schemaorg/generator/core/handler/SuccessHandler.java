package com.weedow.schemaorg.generator.core.handler;

import com.weedow.schemaorg.generator.core.Context;

import java.nio.file.Path;

public interface SuccessHandler {

    void onSuccess(String templateName, Path outputFile, Context context);
}
