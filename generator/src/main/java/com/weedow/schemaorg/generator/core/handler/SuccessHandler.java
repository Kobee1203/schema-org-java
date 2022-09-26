package com.weedow.schemaorg.generator.core.handler;

import com.weedow.schemaorg.generator.core.Context;

import java.io.File;

public interface SuccessHandler {

    void onSuccess(String templateName, File outputFile, Context context);
}
