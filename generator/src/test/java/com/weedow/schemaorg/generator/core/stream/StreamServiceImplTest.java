package com.weedow.schemaorg.generator.core.stream;

import com.weedow.schemaorg.generator.SchemaModelGeneratorConstants;
import com.weedow.schemaorg.generator.model.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.stream.Stream;

class StreamServiceImplTest {

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void stream(boolean verbose) {
        boolean backupVerbose = SchemaModelGeneratorConstants.isVerbose();

        SchemaModelGeneratorConstants.setVerbose(verbose);
        try {
            StreamServiceImpl streamService = new StreamServiceImpl();

            Stream<Type> stream = streamService.stream(Collections.emptyMap());

            Assertions.assertThat(stream.isParallel()).isEqualTo(!verbose);
        } finally {
            SchemaModelGeneratorConstants.setVerbose(backupVerbose);
        }
    }
}