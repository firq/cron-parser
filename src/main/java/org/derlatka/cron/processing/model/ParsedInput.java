package org.derlatka.cron.processing.model;

import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class ParsedInput {

    String[] separatedInput;
    String minuteInput;
    String hourInput;
    String dayOfMonthInput;
    String monthInput;
    String dayOfWeekInput;
    String commandInput;

    public ParsedInput(String[] args) {
        separatedInput = args[0].split(" ");
        minuteInput = separatedInput[0];
        hourInput = separatedInput[1];
        dayOfMonthInput = separatedInput[2];
        monthInput = separatedInput[3];
        dayOfWeekInput = separatedInput[4];
        commandInput = separatedInput[5];
    }

}
