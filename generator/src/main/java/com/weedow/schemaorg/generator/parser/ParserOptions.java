package com.weedow.schemaorg.generator.parser;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public final class ParserOptions {

    private String schemaVersion;

    private String schemaResource;
}
