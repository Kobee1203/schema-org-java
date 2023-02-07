package com.weedow.schemaorg.generator.model.field;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class FieldVerifierTest {

    @Test
    void equalsContract() {
        EqualsVerifier
                .forPackage(getClass().getPackageName())
                .except(FieldUtils.class)
                .verify();
    }

    @Test
    void toStringContract() {
        ToStringVerifier
                .forPackage(getClass().getPackageName(), false)
                // Ignore fields that are not present in toString() method, or their toString representation is modified before being added (eg.Supplier fields)
                .withIgnoredFields(
                        // Accessor
                        "fieldTypeLinks", "returnFieldType", "returnFieldTypeAsList", "cast", "castAsList",
                        // Field
                        "fieldType", "fieldTypeAsList",
                        // Mutator
                        "paramType", "paramValue"
                )
                .verify();
    }
}
