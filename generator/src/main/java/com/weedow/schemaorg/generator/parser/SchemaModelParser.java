package com.weedow.schemaorg.generator.parser;

import com.weedow.schemaorg.generator.model.Type;

import java.util.Map;

public interface SchemaModelParser {

    Map<String, Type> parse(ParserOptions options);

}
