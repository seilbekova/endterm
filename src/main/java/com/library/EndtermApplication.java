package com.library;

import com.library.patterns.Singleton.LoggerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EndtermApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndtermApplication.class, args);

        LoggerService logger = LoggerService.getInstance();
        logger.logInfo("Library Management API started successfully");

        printStartupInfo();
    }

    private static void printStartupInfo() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        LIBRARY MANAGEMENT REST API");
        System.out.println("        Endterm Project - Spring Boot");
        System.out.println("=".repeat(60));

        System.out.println("\n📡 API ENDPOINTS:");
        System.out.println("  ┌─────────────────────────────────────────────────────");
        System.out.println("  │ AUTHORS:");
        System.out.println("  │   GET    /api/authors");
        System.out.println("  │   POST   /api/authors        {name, nationality}");
        System.out.println("  │   GET    /api/authors/{id}");
        System.out.println("  │   PUT    /api/authors/{id}   {name, nationality}");
        System.out.println("  │   DELETE /api/authors/{id}");
        System.out.println("  │");
        System.out.println("  │ BOOKS:");
        System.out.println("  │   GET    /api/books");
        System.out.println("  │   POST   /api/books          {title, authorId, isbn, price, year}");
        System.out.println("  │   GET    /api/books/{id}");
        System.out.println("  │   PUT    /api/books/{id}     {title, authorId, isbn, price, year}");
        System.out.println("  │   DELETE /api/books/{id}");
        System.out.println("  │   GET    /api/books/author/{authorId}");
        System.out.println("  │   GET    /api/books/search?keyword=...");
        System.out.println("  │");
        System.out.println("  │ PATTERNS DEMO:");
        System.out.println("  │   GET    /api/patterns/singleton");
        System.out.println("  │   GET    /api/patterns/factory");
        System.out.println("  │   GET    /api/patterns/builder");
        System.out.println("  │   GET    /api/patterns/all");
        System.out.println("  └─────────────────────────────────────────────────────");

        System.out.println("\n🎯 DESIGN PATTERNS IMPLEMENTED:");
        System.out.println("  • Singleton: AppConfig, DatabaseConfig, LoggerService");
        System.out.println("  • Factory: BookFactory for EBook/PrintedBook creation");
        System.out.println("  • Builder: BookBuilder with fluent API");

        System.out.println("\n🏗️  ARCHITECTURE:");
        System.out.println("  • RESTful API with Spring Boot");
        System.out.println("  • Layered Architecture: Controller → Service → Repository");
        System.out.println("  • SQL Server Database with JDBC");
        System.out.println("  • Global Exception Handling");

        System.out.println("\n✅ Application is running on: http://localhost:8080");
        System.out.println("=".repeat(60) + "\n");
    }
}





