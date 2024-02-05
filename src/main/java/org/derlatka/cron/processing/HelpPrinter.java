package org.derlatka.cron.processing;

public enum HelpPrinter {

    ;

    public static void printHelp() {
        System.out.println("""
                CRON parser
                Application - prints execution times of provided command
                General usage:
                Provide command to application enclosed in " (double quites) in the following pattern:
                "1 2 3 4 5 /command"
                1 being minutes
                2 hours
                3 day of month
                4 day of week
                /command command to be executed
                Supported expression semantic:
                * (all) specifies that event should happen for every time unit in category.
                Example "1 1 1 * 1 /command" will print all seven days of week.
                – (range) determines the value range (inclusive)
                Example "1 1 1 2-4 1 /command" will print all 3 values for day: 2, 3 and 4.
                / (increments) specifies the incremental values
                Example "0/20 1 1 1 1 /command" will print incremented values by 20 for minutes, starting at 0: 0, 20, 40
                , (values) specifies multiple values.
                """);
    }

}
