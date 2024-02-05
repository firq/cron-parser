package org.derlatka.cron.processing.model;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.derlatka.cron.processing.model.CronConstants.*;

@RequiredArgsConstructor
abstract class TimeCalculatingValue implements NamedCronEntity {

    private final CronConstants cronConstant;
    private final String input;

    int getMin() {
        return cronConstant.getMin();
    }

    int getMax() {
        return cronConstant.getMax();
    }

    public String getName() {
        return cronConstant.getName();
    }

    public String getExecutionData() {
        String executionsDetailArrayStr = Arrays.toString(executionDetailsArray());
        return executionsDetailArrayStr
                .substring(1, executionsDetailArrayStr.length() - 1)
                .replaceAll(VALUES_SEPARATOR, "");
    }

    int[] executionDetailsArray() {
        int[] scheduledValues;

        if (WILDCARD.equals(input)) {
            scheduledValues = allSupportedValues();
        } else if (input.contains(RANGE)) {
            scheduledValues = valuesInRange();
        } else if (input.contains(INCREMENTATION)) {
            scheduledValues = incrementedValues();
        } else {
            scheduledValues = arrayOfValues();
        }

        return scheduledValues;
    }

    private int[] arrayOfValues() {
        return Arrays.stream(input.split(VALUES_SEPARATOR))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
    }

    private int[] incrementedValues() {
        String[] inputSplit = input.split(INCREMENTATION);
        int start = valueOrMin(inputSplit[0]);
        int incrementBy = valueOrMax(inputSplit[1]);
        return toArrayBelowOrEqMax(IntStream.iterate(start, i -> i + incrementBy));
    }

    private int[] valuesInRange() {
        String[] inputSplit = input.split(RANGE);
        int start = valueOrMin(inputSplit[0]);
        int end = valueOrMax(inputSplit[1]);
        return toArrayBelowOrEqMax(IntStream.iterate(start, i -> i + 1), end);
    }

    private int[] allSupportedValues() {
        return toArrayBelowOrEqMax(IntStream.iterate(getMin(), i -> i + 1));
    }

    private int[] toArrayBelowOrEqMax(IntStream intStream) {
        return toArrayBelowOrEqMax(intStream, getMax());
    }

    private int[] toArrayBelowOrEqMax(IntStream intStream, int max) {
        return intStream.limit(max + 1)
                .filter(i -> i <= max)
                .toArray();
    }

    private int valueOrMin(String valueOrWildcard) {
        return parseOrProvided(valueOrWildcard, getMin());
    }

    private int valueOrMax(String valueOrWildcard) {
        return parseOrProvided(valueOrWildcard, getMax());
    }

    private static int parseOrProvided(String valueOrWildcard, int provided) {
        return valueOrWildcard.equals(WILDCARD)
                ? provided
                : Integer.parseInt(valueOrWildcard.trim());
    }

}
