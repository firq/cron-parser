package org.derlatka.cron.processing.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CronConstants {

    MINUTE(0, 59, "minute"),
    HOUR(0, 23, "hour"),
    DAY_OF_MONTH(1, 31, "day of month"),
    MONTH(1, 12, "month"),
    DAY_OF_WEEK(1, 7, "day of week");

    public static final String WILDCARD = "*";
    public static final String RANGE = "-";
    public static final String INCREMENTATION = "/";
    public static final String VALUES_SEPARATOR = ",";

    private final int min;

    private final int max;

    private final String name;

}
