package com.library.patterns.Singleton;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private static AppConfig instance;

    @Value("${app.name:Library Management System}")
    private String appName;

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @Value("${app.environment:development}")
    private String environment;

    private AppConfig() {
        System.out.println("AppConfig Singleton instance created");
    }

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public void displayConfig() {
        System.out.println("=== Application Configuration ===");
        System.out.println("Application: " + appName);
        System.out.println("Version: " + appVersion);
        System.out.println("Environment: " + environment);
        System.out.println("=================================");
    }

    // Getters
    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getEnvironment() {
        return environment;
    }
}









