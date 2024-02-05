package org.derlatka.cron.processing.verification;

enum CommandInputVerifier {

    ;

    static boolean verify(String commandInput) {
        return commandInput != null && !commandInput.isBlank();
    }
}
