package com.weedow.schemaorg.generator.model.jsonld;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.ScanOption;
import org.junit.jupiter.api.Test;

class JsonLDModelVerifierTest {

    @Test
    void equalsContract() {
        EqualsVerifier
                .simple()
                .forPackage(getClass().getPackageName(), ScanOption.recursive())
                .verify();
    }

    @Test
    void toStringContract() {
        ToStringVerifier
                .forPackage(getClass().getPackageName(), true)
                .verify();
    }

}