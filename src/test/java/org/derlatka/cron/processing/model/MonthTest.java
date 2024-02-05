package org.derlatka.cron.processing.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.derlatka.cron.processing.model.CronConstants.MONTH;
import static org.derlatka.cron.processing.model.TimeCalculatingValueTest.correctCalculationsStream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MonthTest {

    @ParameterizedTest
    @MethodSource("correctCalculations")
    void minuteCalculationsAreValid(String input,
                                    int[] expected) {
        int[] result = new Month(input).executionDetailsArray();
        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> correctCalculations() {
        return correctCalculationsStream(MONTH, allSupportedValues(), allSupportedAfterIncrementationFromMinTo5());
    }

    private static int[] allSupportedValues() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    }

    private static int[] allSupportedAfterIncrementationFromMinTo5() {
        return new int[]{1, 6, 11};
    }

}