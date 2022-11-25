package com.weedow.schemaorg.serializer;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class JsonLdSerializerOptionsVerifierTest {

    @Test
    void equalsContract() {
        EqualsVerifier
                .simple()
                .forClass(JsonLdSerializerOptions.class)
                .verify();
    }

    @Test
    void toStringContract() {
        ToStringVerifier.forClass(JsonLdSerializerOptions.class).verify();
    }
}