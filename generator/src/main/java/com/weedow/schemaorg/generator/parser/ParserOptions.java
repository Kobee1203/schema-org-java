package com.weedow.schemaorg.generator.parser;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public final class ParserOptions {

    private String schemaVersion;

    private String schemaResource;

    private boolean usedJavaTypes = false;

    private Map<String, String> customDataTypes;
}
