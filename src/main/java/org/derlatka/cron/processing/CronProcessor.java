package org.derlatka.cron.processing;

import org.derlatka.cron.processing.model.*;
import org.derlatka.cron.processing.verification.InputVerifier;

public record CronProcessor(String[] args) {

    public void process() {

        ParsedInput input = InputVerifier.verifyParseInput(args);

        Minute minute = new Minute(input.getMinuteInput());
        Hour hour = new Hour(input.getHourInput());
        DayOfMonth dayOfMonth = new DayOfMonth(input.getDayOfMonthInput());
        Month month = new Month(input.getMonthInput());
        DayOfWeek dayOfWeek = new DayOfWeek(input.getDayOfWeekInput());
        Command command = new Command(input.getCommandInput());

        OutputFormattedPrinter.printFormatted(minute, hour, dayOfMonth, month, dayOfWeek, command);
    }
}
