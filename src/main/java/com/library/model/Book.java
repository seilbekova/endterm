// src/main/java/com/library/model/Book.java
package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Book extends BaseEntity {
    private String title;
    private Author author;
    private String isbn;
    private double price;
    private int publicationYear;

    public Book() {}

    public Book(int id, String title, Author author, String isbn,
                double price, int publicationYear) {
        super(id);
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.publicationYear = publicationYear;
    }

    @Override
    public String getDetails() {
        return String.format("Book: %s (ID: %d, Author: %s, ISBN: %s, Price: $%.2f, Year: %d)",
                title, id, author.getName(), isbn, price, publicationYear);
    }

    @Override
    public void printBasicInfo() {
        System.out.printf("Book ID: %d | Title: %s | Author: %s | ISBN: %s | Price: $%.2f | Year: %d%n",
                id, title, author.getName(), isbn, price, publicationYear);
    }

    @Override
    public void validate() {
        super.validate();
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }
        if (author == null) {
            throw new IllegalArgumentException("Book must have an author");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (publicationYear < 0 || publicationYear > 2100) {
            throw new IllegalArgumentException("Invalid publication year");
        }
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}










