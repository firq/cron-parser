package org.derlatka.cron.processing.model;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.derlatka.cron.processing.model.CronConstants.*;

class TimeCalculatingValueTest {

    static Stream<Arguments> correctCalculationsStream(CronConstants type,
                                                       int[] allSupportedValues,
                                                       int[] allSupportedAfterIncrementationFromMinTo5) {
        int min = type.getMin();
        int max = type.getMax();
        int valueInBetween = max / 2;
        return Stream.of(
                Arguments.of(WILDCARD, allSupportedValues),
                Arguments.of(String.valueOf(min), new int[]{min}),
                Arguments.of(String.valueOf(max), new int[]{max}),
                Arguments.of(String.valueOf(valueInBetween), new int[]{valueInBetween}),
                Arguments.of(min + VALUES_SEPARATOR + max + VALUES_SEPARATOR + valueInBetween, new int[]{min, valueInBetween, max}),
                Arguments.of(min + RANGE + (min + 2), new int[]{min, min + 1, min + 2}),
                Arguments.of(min + INCREMENTATION + 5, allSupportedAfterIncrementationFromMinTo5)
        );
    }

}