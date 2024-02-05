package org.derlatka.cron.processing;

import org.derlatka.cron.processing.model.NamedCronEntity;

import java.util.stream.Stream;

enum OutputFormattedPrinter {

    ;

    static final int DEFAULT_SIZE = 10;
    static final int ADDITIONAL_SPACING_SIZE = 3;

    static void printFormatted(NamedCronEntity... entities) {
        int longestName = calculateLongestNameWithExtraSpacing(entities);
        Stream.of(entities)
                .map(data -> nameWithCalculatedSpacing(data.getName(),
                        longestName) + data.getExecutionData())
                .forEach(System.out::println);
    }

    private static int calculateLongestNameWithExtraSpacing(NamedCronEntity[] entities) {
        return Stream.of(entities)
                .map(NamedCronEntity::getName)
                .mapToInt(String::length)
                .max()
                .orElse(DEFAULT_SIZE) + ADDITIONAL_SPACING_SIZE;
    }

    private static String nameWithCalculatedSpacing(String name, int longestName) {
        return String.format("%-" + longestName + "s", name);
    }

}
