package com.weedow.schemaorg.serializer.deserialization;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class JsonLdDeserializerOptionsVerifierTest {

    @Test
    void equalsContract() {
        EqualsVerifier
                .simple()
                .forClass(JsonLdDeserializerOptions.class)
                .verify();
    }

    @Test
    void toStringContract() {
        ToStringVerifier.forClass(JsonLdDeserializerOptions.class).verify();
    }
}