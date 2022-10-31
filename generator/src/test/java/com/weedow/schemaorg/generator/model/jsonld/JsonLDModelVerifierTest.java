package com.weedow.schemaorg.generator.model.jsonld;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class JsonLDModelVerifierTest {

    @Test
    void equalsContract() {
        EqualsVerifier
                .simple()
                .forPackage(getClass().getPackageName(), true)
                .verify();
    }

    @Test
    void toStringContract() {
        ToStringVerifier
                .forPackage(getClass().getPackageName(), true)
                .verify();
    }

}