package com.library.patterns.Factory;

import com.library.model.Author;
import com.library.model.Book;
import com.library.model.EBook;
import com.library.model.PrintedBook;

public class BookFactory {

    public enum BookType {
        EBOOK,
        PRINTED_BOOK,
        AUDIO_BOOK
    }

    private BookFactory() {
        // Private constructor to prevent instantiation
    }

    public static Book createBook(BookType type, int id, String title,
                                  Author author, String isbn, double price, int year) {
        switch (type) {
            case EBOOK:
                return new EBook(id, title, author, isbn, price, year);
            case PRINTED_BOOK:
                return new PrintedBook(id, title, author, isbn, price, year);
            case AUDIO_BOOK:
                // Creating a regular Book for audio book
                Book audioBook = new Book(id, title, author, isbn, price, year);
                return audioBook;
            default:
                throw new IllegalArgumentException("Unknown book type: " + type);
        }
    }

    public static Book createBook(BookType type, String title, Author author,
                                  String isbn, double price, int year) {
        return createBook(type, 0, title, author, isbn, price, year);
    }

    public static Book createEBookWithDetails(int id, String title, Author author,
                                              String isbn, double price, int year,
                                              String format, double fileSize) {
        return new EBook(id, title, author, isbn, price, year, format, fileSize);
    }

    public static Book createPrintedBookWithDetails(int id, String title, Author author,
                                                    String isbn, double price, int year,
                                                    int pageCount, String coverType) {
        return new PrintedBook(id, title, author, isbn, price, year, pageCount, coverType);
    }

    public static Book createBookFromString(String bookString) {
        try {
            String[] parts = bookString.split(",");
            BookType type = BookType.valueOf(parts[0].toUpperCase());
            String title = parts[1];
            String isbn = parts[2];
            double price = Double.parseDouble(parts[3]);
            int year = Integer.parseInt(parts[4]);

            // For demo, create a dummy author
            Author dummyAuthor = new Author(0, "Unknown Author", "Unknown");

            return createBook(type, title, dummyAuthor, isbn, price, year);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid book string format", e);
        }
    }
}








