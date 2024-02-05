package org.derlatka.cron.processing.verification;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InputVerifierTest {

    @ParameterizedTest
    @ValueSource(strings = {"test", "1 1"})
    void inputWithInvalidSizeIsRejected(String input) {
        assertTrue(InputVerifier.invalidInputSize(new String[]{input}));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void inputWithEmptyInputRejected(String[] input) {
        assertTrue(InputVerifier.invalidInputSize(input));
    }

}