package org.derlatka.cron.processing.model;

public class Minute extends TimeCalculatingValue {

    public Minute(String input) {
        super(CronConstants.MINUTE, input);
    }

}
