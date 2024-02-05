package org.derlatka.cron.processing.model;

public class DayOfWeek extends TimeCalculatingValue {

    public DayOfWeek(String input) {
        super(CronConstants.DAY_OF_WEEK, input);
    }

}
