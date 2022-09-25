package com.weedow.schemaorg.generator.parser;

public final class ParserOptions {

    private String schemaVersion = "latest";

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public ParserOptions setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
        return this;
    }
}
