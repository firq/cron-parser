package org.derlatka.cron.processing.model;

public class Hour extends TimeCalculatingValue {

    public Hour(String input) {
        super(CronConstants.HOUR, input);
    }

}
