package org.derlatka.cron.processing.model;

public class Month extends TimeCalculatingValue {

    public Month(String input) {
        super(CronConstants.MONTH, input);
    }

}
