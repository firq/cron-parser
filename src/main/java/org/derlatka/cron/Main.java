package org.derlatka.cron;

import org.derlatka.cron.processing.CronProcessor;

public class Main {
    public static void main(String[] args) {
        new CronProcessor(args).process();
    }

}