package org.derlatka.cron.processing.model;

public record Command(String command) implements NamedCronEntity {

    static final String COMMAND_NAME = "command";

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public String getExecutionData() {
        return command;
    }
}
