package com.weedow.schemaorg.generator.parser;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class ParserOptionsVerifierTest {

    @Test
    void equalsContract() {
        EqualsVerifier
                .simple()
                .forClass(ParserOptions.class)
                .verify();
    }

    @Test
    void toStringContract() {
        ToStringVerifier.forClass(ParserOptions.class).verify();
    }
}