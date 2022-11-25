package com.weedow.schemaorg.generator.core;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class GeneratorOptionsVerifierTest {

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(GeneratorOptions.class).verify();
    }

    @Test
    void toStringContract() {
        ToStringVerifier.forClass(GeneratorOptions.class).verify();
    }
}