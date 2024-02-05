package org.derlatka.cron.processing.model;

public class DayOfMonth extends TimeCalculatingValue {

    public DayOfMonth(String input) {
        super(CronConstants.DAY_OF_MONTH, input);
    }

}
