Library Management REST API - Endterm Project
A. Project Overview
This project is a Spring Boot RESTful API for a Library Management System that implements Design Patterns, Component Principles, and SOLID principles. The system allows managing authors and books through a complete CRUD API with database persistence.

 Design Patterns Section
 Singleton Pattern
Purpose: Ensure a class has only one instance and provide a global point of access to it.

Implementation in Project:

AppConfig.java - Application configuration singleton

DatabaseConfig.java - Database configuration singleton

LoggerService.java - Logging service singleton
Factory Pattern
Purpose: Define an interface for creating objects, but let subclasses decide which class to instantiate.

Implementation in Project:

BookFactory.java - Creates different types of books (EBook, PrintedBook, AudioBook)
Builder Pattern
Purpose: Separate the construction of a complex object from its representation, allowing the same construction process to create different representations.

Implementation in Project:

BookBuilder.java - Builds complex Book objects with fluent interface

AuthorBuilder.java - Builds complex Author objects
. Component Principles Section
 REP (Reuse/Release Equivalence Principle)
"The granule of reuse is the granule of release."

Implementation:

Reusable packages with clear responsibilities:

repository/ - Database access layer (can be reused in other projects)

service/ - Business logic layer (can be swapped)

patterns/ - Design pattern implementations (reusable across projects)

utils/ - Utility classes (independent reuse)


SOLID & OOP Summary
1. Single Responsibility Principle (SRP)
Each class has one clear purpose:

AuthorController handles HTTP requests for authors

AuthorService contains business logic for authors

AuthorRepository handles database operations for authors

Author represents the author domain model

2. Open/Closed Principle (OCP)
Classes are open for extension, closed for modification:

Book class can be extended to EBook and PrintedBook without modifying base class

BookFactory can create new book types without changing existing code

Repository interfaces allow different implementations

3. Liskov Substitution Principle (LSP)
Subclasses can replace parent classes:

EBook and PrintedBook can be used wherever Book is expected

All methods from BaseEntity are properly implemented in subclasses

Polymorphism demonstrated in pattern demos

4. Interface Segregation Principle (ISP)
Small, specific interfaces:

Each repository has specific methods for its entity

Services have focused interfaces

DTOs are minimal and focused

5. Dependency Inversion Principle (DIP)
Depend on abstractions, not concretions:

Controllers depend on Service interfaces

Services depend on Repository interfaces

Spring dependency injection throughout

 OOP Features Demonstrated:
Inheritance: BaseEntity → Author/Book → EBook/PrintedBook

Polymorphism: Different book types treated uniformly

Encapsulation: Private fields with public getters/setters

Abstraction: Abstract BaseEntity class

Composition: Book has an Author

F. Database Schema
 Entity Relationship Diagram
sql
-- Authors Table
CREATE TABLE authors (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    nationality NVARCHAR(50) NOT NULL,
    created_at DATETIME DEFAULT GETDATE()
);

-- Books Table
CREATE TABLE books (
    id INT PRIMARY KEY IDENTITY(1,1),
    title NVARCHAR(200) NOT NULL,
    author_id INT NOT NULL,
    isbn NVARCHAR(20) UNIQUE NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    publication_year INT NOT NULL,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);

-- Indexes for Performance
CREATE INDEX idx_books_author_id ON books(author_id);
CREATE INDEX idx_books_isbn ON books(isbn);
CREATE INDEX idx_authors_name ON authors(name);
 Relationships
One-to-Many: One Author → Many Books

Cascade Delete: When author is deleted, all their books are deleted

Referential Integrity: Foreign key constraints ensure data consistency

Sample Data
sql
-- Insert sample authors
INSERT INTO authors (name, nationality) VALUES
('J.K. Rowling', 'British'),
('George Orwell', 'English'),
('J.R.R. Tolkien', 'English');

-- Insert sample books  
INSERT INTO books (title, author_id, isbn, price, publication_year) VALUES
('Harry Potter and the Philosopher''s Stone', 1, '9780747532743', 19.99, 1997),
('1984', 2, '9780451524935', 14.99, 1949),
('The Hobbit', 3, '9780547928227', 22.50, 1937);


Package Structure
text
com.library/
├── controller/           # REST Controllers
│   ├── AuthorController.java
│   ├── BookController.java
│   └── PatternDemoController.java
├── service/             # Business Logic Layer
│   ├── AuthorService.java
│   └── BookService.java
├── repository/          # Data Access Layer
│   ├── AuthorRepository.java
│   └── BookRepository.java
├── model/               # Domain Models
│   ├── BaseEntity.java
│   ├── Author.java
│   ├── Book.java
│   ├── EBook.java
│   └── PrintedBook.java
├── dto/                 # Data Transfer Objects
│   ├── AuthorDTO.java
│   ├── BookDTO.java
│   ├── AuthorRequest.java
│   └── BookRequest.java
├── patterns/            # Design Patterns
│   ├── singleton/
│   │   ├── AppConfig.java
│   │   ├── DatabaseConfig.java
│   │   └── LoggerService.java
│   ├── factory/
│   │   └── BookFactory.java
│   └── builder/
│       ├── BookBuilder.java
│       └── AuthorBuilder.java
├── exception/           # Exception Handling
│   ├── GlobalExceptionHandler.java
│   ├── ErrorResponse.java
│   └── ValidationErrorResponse.java
├── utils/               # Utility Classes
│   └── ResponseUtil.java
└── EndtermApplication.java  # Main Application Class
