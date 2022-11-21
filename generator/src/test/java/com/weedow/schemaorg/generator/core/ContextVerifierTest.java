package com.weedow.schemaorg.generator.core;

import com.jparams.verifier.tostring.ToStringVerifier;
import com.weedow.schemaorg.generator.model.Type;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class ContextVerifierTest {

    @Test
    void equalsContract() {
        EqualsVerifier
                .simple()
                .forClass(Context.class)
                .suppress(Warning.REFERENCE_EQUALITY)
                .withPrefabValues(Type.class, mock(Type.class), mock(Type.class))
                .verify();
    }

    @Test
    void toStringContract() {
        ToStringVerifier.forClass(Context.class).verify();
    }
}