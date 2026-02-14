package com.library.patterns.Builder;

import com.library.model.Author;
import com.library.model.Book;

public class BookBuilder {
    private int id;
    private String title;
    private Author author;
    private String isbn;
    private double price;
    private int publicationYear;
    private String description;
    private int pageCount;
    private String publisher;
    private String genre;
    private String language;

    public BookBuilder() {
        // Default values
        this.language = "English";
        this.genre = "General";
        this.pageCount = 300;
    }

    public BookBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public BookBuilder setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public BookBuilder setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
        return this;
    }

    public BookBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public BookBuilder setPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public BookBuilder setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public BookBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public BookBuilder setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Book build() {
        if (title == null) {
            throw new IllegalStateException("Title is required");
        }
        if (author == null) {
            throw new IllegalStateException("Author is required");
        }
        if (isbn == null) {
            throw new IllegalStateException("ISBN is required");
        }

        Book book = new Book(id, title, author, isbn, price, publicationYear);
        System.out.println("Built book: " + title + " with ISBN: " + isbn);
        System.out.println("Additional info - Genre: " + genre + ", Language: " + language +
                ", Pages: " + pageCount);

        return book;
    }

    // Static factory method
    public static BookBuilder builder() {
        return new BookBuilder();
    }
}








