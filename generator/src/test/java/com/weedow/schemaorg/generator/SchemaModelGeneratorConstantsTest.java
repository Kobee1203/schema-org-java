package com.weedow.schemaorg.generator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SchemaModelGeneratorConstantsTest {

    @Test
    void setVerbose() {
        Assertions.assertThat(SchemaModelGeneratorConstants.isVerbose()).isFalse();

        SchemaModelGeneratorConstants.setVerbose(true);
        Assertions.assertThat(SchemaModelGeneratorConstants.isVerbose()).isTrue();

        SchemaModelGeneratorConstants.setVerbose(false);
        Assertions.assertThat(SchemaModelGeneratorConstants.isVerbose()).isFalse();
        Assertions.assertThat(System.getProperty("com.weedow.shemaorg.generator.verbose")).isNull();
    }
}