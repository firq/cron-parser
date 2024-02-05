package org.derlatka.cron.processing.verification;

import org.derlatka.cron.processing.model.CronConstants;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.derlatka.cron.processing.model.CronConstants.*;

enum TimeCronValuesVerifier {

    ;
    private static final Pattern ANY_POSITIVE_NUMBER_AT_MOST_2_DIGITS = Pattern.compile("^[0-9]{1,2}$");

    static boolean verifyInput(String input,
                               CronConstants subjectOfVerification) {

        if (WILDCARD.equals(input)) {
            return true;
        }
        String[] valuesForVerification;
        List<Predicate<String[]>> parsingSpecificChecks = Collections.emptyList();
        if (input.contains(RANGE)) {
            valuesForVerification = input.split(RANGE);
            parsingSpecificChecks = List.of(
                    exactly2ArgumentsProvided(subjectOfVerification.name()),
                    correctOrderOfArguments(subjectOfVerification.name()));
        } else if (input.contains(INCREMENTATION)) {
            valuesForVerification = input.split(INCREMENTATION);
            parsingSpecificChecks = List.of(exactly2ArgumentsProvided(subjectOfVerification.name()));
        } else if (input.contains(VALUES_SEPARATOR)) {
            valuesForVerification = input.split(VALUES_SEPARATOR);
        } else {
            valuesForVerification = new String[]{input};
        }
        return wildcardOrWithinRange(subjectOfVerification, parsingSpecificChecks, valuesForVerification);
    }

    private static Predicate<String[]> correctOrderOfArguments(String subjectOfVerifications) {
        return params -> printErrorForFalse(() -> anyWildcard(params) || Integer.parseInt(params[0]) <= Integer.parseInt(params[1]),
                "Invalid order of arguments for " + subjectOfVerifications);
    }

    private static boolean anyWildcard(String[] params) {
        return toListWithoutWildcards(params).size() != params.length;
    }

    private static Predicate<String[]> exactly2ArgumentsProvided(String subjectOfVerifications) {
        return params -> printErrorForFalse(() -> params.length == 2,
                "Unexpected number of arguments for " + subjectOfVerifications);
    }

    private static boolean printErrorForFalse(Supplier<Boolean> condition, String errorMsg) {
        Boolean result = condition.get();
        if (!result) {
            System.err.println(errorMsg);
        }
        return result;
    }

    private static boolean wildcardOrWithinRange(CronConstants subjectOfVerification,
                                                 List<Predicate<String[]>> additionalChecks,
                                                 String... toBeVerified) {
        List<String> notWildcardInput = toListWithoutWildcards(toBeVerified);
        List<String> invalidInput = toListContainingInvalidInput(notWildcardInput);
        if (!invalidInput.isEmpty()) {
            System.err.println("Invalid input provided for " + subjectOfVerification
                    + ". \nUnexpected values: " + invalidInput);
            return false;
        }
        int min = subjectOfVerification.getMin();
        int max = subjectOfVerification.getMax();
        boolean allInputWithinRange = notWildcardInput.stream()
                .mapToInt(Integer::parseInt)
                .allMatch(valueToBeChecked -> valueToBeChecked >= min && valueToBeChecked <= max);
        if (!allInputWithinRange) {
            System.err.println("Input out of range provided for " + subjectOfVerification
                    + ". \nProvide values in between " + min + " and " + max);
        }
        return allInputWithinRange && additionalChecks.stream().allMatch(e -> e.test(toBeVerified));
    }

    private static List<String> toListContainingInvalidInput(List<String> input) {
        return input.stream()
                .map(String::trim)
                .filter(stringValue -> !ANY_POSITIVE_NUMBER_AT_MOST_2_DIGITS.matcher(stringValue).matches())
                .toList();
    }

    private static List<String> toListWithoutWildcards(String[] toBeVerified) {
        return Stream.of(toBeVerified)
                .filter(stringValue -> !WILDCARD.equals(stringValue))
                .toList();
    }


}
