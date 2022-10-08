package com.weedow.schemaorg.generator.core.handler;

import com.weedow.schemaorg.generator.core.Context;

import java.nio.file.Path;

public interface ErrorHandler {

    void onError(String templateName, Path outputFile, Context context, Exception e);
}
