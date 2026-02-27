package com.weedow.schemaorg.generator.core.stream;

import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;
import com.weedow.schemaorg.generator.model.Type;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.stream.Stream;

/**
 * Default implementation of {@link StreamService}.
 * <p>
 * This implementation returns:
 * <ul>
 *   <li>A sequential stream when verbose mode is enabled (for better log ordering)</li>
 *   <li>A parallel stream when verbose mode is disabled (for better performance)</li>
 * </ul>
 */
@NoArgsConstructor
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
