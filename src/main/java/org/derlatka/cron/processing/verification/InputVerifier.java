package org.derlatka.cron.processing.verification;

import org.derlatka.cron.processing.HelpPrinter;
import org.derlatka.cron.processing.model.CronConstants;
import org.derlatka.cron.processing.model.ParsedInput;

import java.util.stream.Stream;

public enum InputVerifier {

    ;

    public static ParsedInput verifyParseInput(String[] args) {
        verifyGeneralInput(args);
        return verifyInputContent(args);
    }

    private static void verifyGeneralInput(String[] args) {
        if (invalidInputSize(args)) {
            System.err.println("Invalid size of input.");
            printHelpAndExit();
        }
    }

    static boolean invalidInputSize(String[] args) {
        return args == null || args.length != 1 || args[0].isBlank() || args[0].split(" ").length != 6;
    }

    private static ParsedInput verifyInputContent(String[] args) {
        ParsedInput input = new ParsedInput(args);
        boolean allVerificationsPassed = Stream.of(
                        TimeCronValuesVerifier.verifyInput(input.getMinuteInput(), CronConstants.MINUTE),
                        TimeCronValuesVerifier.verifyInput(input.getHourInput(), CronConstants.HOUR),
                        TimeCronValuesVerifier.verifyInput(input.getDayOfMonthInput(), CronConstants.DAY_OF_MONTH),
                        TimeCronValuesVerifier.verifyInput(input.getMonthInput(), CronConstants.MONTH),
                        TimeCronValuesVerifier.verifyInput(input.getDayOfWeekInput(), CronConstants.DAY_OF_WEEK),
                        CommandInputVerifier.verify(input.getCommandInput())
                )
                .allMatch(verificationPassed -> verificationPassed);

        if (!allVerificationsPassed) {
            printHelpAndExit();
        }
        return input;
    }

    private static void printHelpAndExit() {
        HelpPrinter.printHelp();
        System.exit(1);
    }

}
