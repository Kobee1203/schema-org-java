package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;
import com.weedow.schemaorg.generator.parser.ParserOptions;

import java.util.Map;

public interface ModelHandler {

    boolean supports(GraphItem graphItem, ParserOptions options);

    void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem, ParserOptions options);

}
