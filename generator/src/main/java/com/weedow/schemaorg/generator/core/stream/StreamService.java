package com.weedow.schemaorg.generator.core.stream;

import com.weedow.schemaorg.generator.model.Type;

import java.util.Map;
import java.util.stream.Stream;

public interface StreamService {

    Stream<Type> stream(Map<String, Type> schemaDefinitions);
}
