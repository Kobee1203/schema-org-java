package com.weedow.schemaorg.generator.core.stream;

import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;
import com.weedow.schemaorg.generator.model.Type;

import java.util.Map;
import java.util.stream.Stream;

public class StreamServiceImpl implements StreamService {

    @Override
    public Stream<Type> stream(Map<String, Type> schemaDefinitions) {
        Stream<Type> stream;
        if (SchemaModelGeneratorConstants.isVerbose()) {
            stream = schemaDefinitions.values().stream();
        } else {
            stream = schemaDefinitions.values().parallelStream();
        }
        return stream;
    }
}
