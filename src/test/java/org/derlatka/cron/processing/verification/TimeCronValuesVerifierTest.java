package org.derlatka.cron.processing.verification;

import org.derlatka.cron.processing.model.CronConstants;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.String.valueOf;
import static org.derlatka.cron.processing.model.CronConstants.*;
import static org.derlatka.cron.processing.verification.TimeCronValuesVerifier.verifyInput;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeCronValuesVerifierTest {

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void wildcardIsAcceptedAsValidInput(CronConstants cronType) {
        assertTrue(verifyInput(WILDCARD, cronType));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void singleMinValueIsAcceptedAsValidInput(CronConstants cronType) {
        assertTrue(verifyInput(valueOf(cronType.getMin()), cronType));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void singleMaxValueIsAcceptedAsValidInput(CronConstants cronType) {
        assertTrue(verifyInput(valueOf(cronType.getMax()), cronType));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void arrayOfValuesWithinRangeIsAcceptedAsValidInput(CronConstants cronType) {
        assertTrue(verifyInput(cronType.getMin() + VALUES_SEPARATOR + cronType.getMax(), cronType));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void rangeOfValuesWithinRangeIsAcceptedAsValidInput(CronConstants cronType) {
        assertTrue(verifyInput(cronType.getMin() + RANGE + cronType.getMax(), cronType));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void incrementationOfValuesWithinRangeIsAcceptedAsValidInput(CronConstants cronType) {
        assertTrue(verifyInput(cronType.getMin() + INCREMENTATION + 1, cronType));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void rangeExpressionAcceptsWildcardAsValidInput(CronConstants cronType) {
        assertTrue(verifyInput(WILDCARD + RANGE + WILDCARD, cronType));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void incrementationExpressionAcceptsWildcardAsValidInput(CronConstants cronType) {
        assertTrue(verifyInput(WILDCARD + INCREMENTATION + WILDCARD, cronType));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void multipleExpressionsNotSupported(CronConstants cronType) {
        assertFalse(verifyInput(cronType.getMin() + INCREMENTATION + 1 + VALUES_SEPARATOR + cronType.getMax(), cronType));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void over2ValuesNotSupportedForRangeCheck(CronConstants cronType) {
        assertFalse(verifyInput(cronType.getMin() + RANGE + 1 + RANGE + cronType.getMax(), cronType));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void over2ValuesNotSupportedForIncrementationCheck(CronConstants cronType) {
        assertFalse(verifyInput(cronType.getMin() + INCREMENTATION + 1 + INCREMENTATION + cronType.getMax(), cronType));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void invalidOrderOfArgumentsForRangeCheckNotSupported(CronConstants cronType) {
        assertFalse(verifyInput(cronType.getMax() + RANGE + cronType.getMin(), cronType));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "test", "one"})
    void notDigitValuesNotSupported(String value) {
        assertFalse(verifyInput(value, MINUTE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "300", "-90000"})
    void negativeAndOver3DigitValuesNotSupported(String value) {
        assertFalse(verifyInput(value, MINUTE));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void valuesUnderMinValueAreNotAllowed(CronConstants cronType) {
        assertFalse(verifyInput(valueOf(cronType.getMin() - 1), cronType));
    }

    @ParameterizedTest
    @EnumSource(CronConstants.class)
    void valuesOverMaxValueAreNotAllowed(CronConstants cronType) {
        assertFalse(verifyInput(valueOf(cronType.getMax() + 1), cronType));
    }

}