package service.impl;

import java.time.LocalDateTime;

import service.Logger;

public class ConsoleLogger implements Logger {
    @Override
    public void logInfo(String message) {
        logMessage(message, "INFO");
    }

    @Override
    public void logError(String message) {
        logMessage(message, "ERROR");
    }

    private void logMessage(String message, String type) {
        String entry = "[" + LocalDateTime.now() + "] " + type + ": " + message;
        System.out.println(entry);
    }
}
