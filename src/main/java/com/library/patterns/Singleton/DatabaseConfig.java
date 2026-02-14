package com.library.patterns.Singleton;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {

    private static DatabaseConfig instance;

    @Value("${spring.datasource.url:jdbc:postgresql://localhost:5432/library_db}")
    private String url;

    @Value("${spring.datasource.username:postgres}")
    private String username;

    @Value("${spring.datasource.password:password}")
    private String password;

    @Value("${spring.datasource.driver-class-name:org.postgresql.Driver}")
    private String driverClassName;

    private DatabaseConfig() {
        System.out.println("DatabaseConfig Singleton instance created");
    }

    public static synchronized DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    // Getters
    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getConnectionInfo() {
        return String.format("URL: %s, User: %s, Driver: %s",
                url, username, driverClassName);
    }
}








