package com.library.patterns.Singleton;

public class LoggerService {

    private static LoggerService instance;

    private LoggerService() {
        System.out.println("LoggerService Singleton instance created");
    }

    public static synchronized LoggerService getInstance() {
        if (instance == null) {
            instance = new LoggerService();
        }
        return instance;
    }

    public void logInfo(String message) {
        String timestamp = java.time.LocalDateTime.now().toString();
        System.out.println("[INFO][" + timestamp + "] " + message);
    }

    public void logError(String message) {
        String timestamp = java.time.LocalDateTime.now().toString();
        System.err.println("[ERROR][" + timestamp + "] " + message);
    }

    public void logDebug(String message) {
        String timestamp = java.time.LocalDateTime.now().toString();
        System.out.println("[DEBUG][" + timestamp + "] " + message);
    }

    public void logWarning(String message) {
        String timestamp = java.time.LocalDateTime.now().toString();
        System.out.println("[WARNING][" + timestamp + "] " + message);
    }
}






