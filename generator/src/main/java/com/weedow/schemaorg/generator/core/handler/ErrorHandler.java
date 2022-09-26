package com.weedow.schemaorg.generator.core.handler;

import com.weedow.schemaorg.generator.core.Context;

import java.io.File;

public interface ErrorHandler {

    void onError(String templateName, File outputFile, Context context, Exception e);
}
