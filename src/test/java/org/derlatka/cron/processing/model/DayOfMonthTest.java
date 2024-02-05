package org.derlatka.cron.processing.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.derlatka.cron.processing.model.CronConstants.DAY_OF_MONTH;
import static org.derlatka.cron.processing.model.TimeCalculatingValueTest.correctCalculationsStream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DayOfMonthTest {

    @ParameterizedTest
    @MethodSource("correctCalculations")
    void minuteCalculationsAreValid(String input,
                                    int[] expected) {
        int[] result = new DayOfMonth(input).executionDetailsArray();
        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> correctCalculations() {
        return correctCalculationsStream(DAY_OF_MONTH, allSupportedValues(), allSupportedAfterIncrementationFromMinTo5());
    }

    private static int[] allSupportedValues() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
                26, 27, 28, 29, 30, 31};
    }

    private static int[] allSupportedAfterIncrementationFromMinTo5() {
        return new int[]{1, 6, 11, 16, 21, 26, 31};
    }

}