package org.derlatka.cron.processing.verification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandInputVerifierTest {

    @Test
    void correctValueVerifiedProperly() {
        assertTrue(CommandInputVerifier.verify("/usr/bin/find"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void emptyValuesAreNotVerified(String commandInput) {
        assertFalse(CommandInputVerifier.verify(commandInput));
    }

}