package com.library.controller;

import com.library.model.Author;
import com.library.model.Book;
import com.library.patterns.Factory.BookFactory;
import com.library.patterns.Builder.BookBuilder;
import com.library.patterns.Singleton.AppConfig;
import com.library.patterns.Singleton.DatabaseConfig;
import com.library.patterns.Singleton.LoggerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/patterns")
public class PatternDemoController {

    @GetMapping("/singleton")
    public ResponseEntity<Map<String, Object>> demonstrateSingleton() {
        Map<String, Object> response = new HashMap<>();

        AppConfig appConfig = AppConfig.getInstance();
        DatabaseConfig dbConfig = DatabaseConfig.getInstance();
        LoggerService logger = LoggerService.getInstance();
        logger.logInfo("Singleton pattern demonstration accessed");

        response.put("appConfig", Map.of(
                "appName", appConfig.getAppName(),
                "appVersion", appConfig.getAppVersion(),
                "environment", appConfig.getEnvironment()
        ));

        response.put("databaseConfig", Map.of(
                "url", dbConfig.getUrl(),
                "username", dbConfig.getUsername(),
                "driverClassName", dbConfig.getDriverClassName()
        ));

        response.put("message", "Singleton patterns demonstrated successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/factory")
    public ResponseEntity<Map<String, Object>> demonstrateFactory() {
        Map<String, Object> response = new HashMap<>();

        Author sampleAuthor = new Author(999, "Factory Pattern Author", "International");

        Book ebook = BookFactory.createBook(
                BookFactory.BookType.EBOOK,
                0, "Digital Revolution", sampleAuthor, "978-1234567890", 19.99, 2023
        );

        Book printedBook = BookFactory.createBook(
                BookFactory.BookType.PRINTED_BOOK,
                0, "Paper Legacy", sampleAuthor, "978-0987654321", 29.99, 2023
        );

        response.put("ebook", Map.of(
                "type", ebook.getClass().getSimpleName(),
                "details", ebook.getDetails()
        ));

        response.put("printedBook", Map.of(
                "type", printedBook.getClass().getSimpleName(),
                "details", printedBook.getDetails()
        ));

        response.put("message", "Factory pattern demonstrated successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/builder")
    public ResponseEntity<Map<String, Object>> demonstrateBuilder() {
        Map<String, Object> response = new HashMap<>();

        Author sampleAuthor = new Author(999, "Builder Pattern Author", "International");

        BookBuilder bookBuilder = BookBuilder.builder()
                .setTitle("The Builder Pattern Guide")
                .setAuthor(sampleAuthor)
                .setIsbn("978-5555555555")
                .setPrice(39.99)
                .setPublicationYear(2023)
                .setDescription("A comprehensive guide to the Builder design pattern")
                .setPageCount(450)
                .setPublisher("Design Patterns Publishing")
                .setGenre("Technical")
                .setLanguage("English");

        Book builtBook = bookBuilder.build();

        response.put("builtBook", Map.of(
                "title", builtBook.getTitle(),
                "isbn", builtBook.getIsbn(),
                "price", builtBook.getPrice(),
                "year", builtBook.getPublicationYear(),
                "author", builtBook.getAuthor().getName()
        ));

        response.put("message", "Builder pattern demonstrated successfully");
        response.put("builderUsage", "BookBuilder.builder().setTitle(...).setAuthor(...).build()");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> demonstrateAllPatterns() {
        Map<String, Object> response = new HashMap<>();

        LoggerService logger = LoggerService.getInstance();
        logger.logInfo("Demonstrating all design patterns");

        AppConfig appConfig = AppConfig.getInstance();

        Author author = new Author(99, "Pattern Master", "Global");
        Book factoryBook = BookFactory.createBook(
                BookFactory.BookType.EBOOK,
                0, "Patterns in Practice", author, "978-1111111111", 24.99, 2023
        );

        BookBuilder builder = BookBuilder.builder()
                .setTitle("Mastering Design Patterns")
                .setAuthor(author)
                .setIsbn("978-9999999999")
                .setPrice(49.99)
                .setPublicationYear(2023);

        Book builderBook = builder.build();

        response.put("singleton", Map.of(
                "instance", "AppConfig, DatabaseConfig, LoggerService",
                "config", appConfig.getAppName() + " v" + appConfig.getAppVersion()
        ));

        response.put("factory", Map.of(
                "createdType", factoryBook.getClass().getSimpleName(),
                "bookDetails", factoryBook.getDetails()
        ));

        response.put("builder", Map.of(
                "bookTitle", builderBook.getTitle(),
                "bookPrice", builderBook.getPrice(),
                "bookAuthor", builderBook.getAuthor().getName()
        ));

        response.put("summary", "All three design patterns (Singleton, Factory, Builder) have been successfully demonstrated");

        return ResponseEntity.ok(response);
    }
}








