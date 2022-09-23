package com.weedow.schemaorg.generator.model.handler;

import com.weedow.schemaorg.generator.model.Type;
import com.weedow.schemaorg.generator.model.jsonld.GraphItem;

import java.util.Map;

public interface ModelHandler {

    boolean supports(GraphItem graphItem);

    void handle(Map<String, Type> schemaDefinitions, GraphItem graphItem);

}
