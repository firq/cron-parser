package org.derlatka.cron.processing.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.derlatka.cron.processing.model.CronConstants.MINUTE;
import static org.derlatka.cron.processing.model.TimeCalculatingValueTest.correctCalculationsStream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MinuteTest{

    @ParameterizedTest
    @MethodSource("correctCalculations")
    void minuteCalculationsAreValid(String input,
                                    int[] expected) {
        int[] result = new Minute(input).executionDetailsArray();
        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> correctCalculations() {
        return correctCalculationsStream(MINUTE, allSupportedValues(), allSupportedAfterIncrementationFromMinTo5());
    }

    private static int[] allSupportedValues() {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
                26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                51, 52, 53, 54, 55, 56, 57, 58, 59};
    }

    private static int[] allSupportedAfterIncrementationFromMinTo5() {
        return new int[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
    }

}