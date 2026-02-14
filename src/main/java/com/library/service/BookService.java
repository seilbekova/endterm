package com.library.service;

import com.library.cache.Cache;
import com.library.cache.InMemoryCache;
import com.library.model.Author;
import com.library.model.Book;
import com.library.patterns.Builder.BookBuilder;
import com.library.patterns.Factory.BookFactory;
import com.library.patterns.Singleton.LoggerService;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private final LoggerService logger = LoggerService.getInstance();

    // --- Кэш поля ---
    private final Cache cache = InMemoryCache.getInstance();
    private static final String ALL_BOOKS_KEY = "allBooks";

    // --- Методы ---

    public List<Book> getAllBooks() {
        // 1. Проверяем кэш
        var cached = cache.get(ALL_BOOKS_KEY);
        if (cached.isPresent()) {
            logger.logInfo("Returning cached list of books");
            return (List<Book>) cached.get();
        }

        // 2. Если нет в кэше – загружаем из БД
        logger.logInfo("Fetching all books from database");
        List<Book> books = bookRepository.findAll();
        cache.put(ALL_BOOKS_KEY, books);
        return books;
    }

    public Book getBookById(int id) {
        logger.logInfo("Fetching book with ID: " + id);
        return bookRepository.findById(id)
                .orElseThrow(() -> {
                    String error = "Book not found with ID: " + id;
                    logger.logError(error);
                    return new RuntimeException(error);
                });
    }

    public Book createBook(Book book) {
        logger.logInfo("Creating new book: " + book.getTitle());

        // Check if author exists
        int authorId = book.getAuthor().getId();
        if (!authorRepository.existsById(authorId)) {
            String error = "Author not found with ID: " + authorId;
            logger.logError(error);
            throw new RuntimeException(error);
        }

        // Check if ISBN already exists
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            String error = "Book with ISBN " + book.getIsbn() + " already exists";
            logger.logError(error);
            throw new RuntimeException(error);
        }

        book.validate();
        Book savedBook = bookRepository.save(book);

        // Инвалидация кэша
        cache.remove(ALL_BOOKS_KEY);
        logger.logInfo("Cache invalidated after creating book");

        logger.logInfo("Book created successfully with ID: " + savedBook.getId());
        return savedBook;
    }

    public Book updateBook(int id, Book book) {
        logger.logInfo("Updating book with ID: " + id);

        if (!bookRepository.existsById(id)) {
            String error = "Book not found with ID: " + id;
            logger.logError(error);
            throw new RuntimeException(error);
        }

        // Check if author exists
        int authorId = book.getAuthor().getId();
        if (!authorRepository.existsById(authorId)) {
            String error = "Author not found with ID: " + authorId;
            logger.logError(error);
            throw new RuntimeException(error);
        }

        book.setId(id);
        book.validate();
        Book updatedBook = bookRepository.save(book);

        // Инвалидация кэша
        cache.remove(ALL_BOOKS_KEY);
        logger.logInfo("Cache invalidated after updating book");

        logger.logInfo("Book updated successfully with ID: " + id);
        return updatedBook;
    }

    public void deleteBook(int id) {
        logger.logInfo("Deleting book with ID: " + id);

        if (!bookRepository.existsById(id)) {
            String error = "Book not found with ID: " + id;
            logger.logError(error);
            throw new RuntimeException(error);
        }

        bookRepository.delete(id);

        // Инвалидация кэша
        cache.remove(ALL_BOOKS_KEY);
        logger.logInfo("Cache invalidated after deleting book");

        logger.logInfo("Book deleted successfully with ID: " + id);
    }

    public List<Book> getBooksByAuthor(int authorId) {
        logger.logInfo("Fetching books by author ID: " + authorId);
        return bookRepository.findByAuthorId(authorId);
    }

    public List<Book> searchBooks(String keyword) {
        logger.logInfo("Searching books with keyword: " + keyword);
        return bookRepository.search(keyword);
    }

    public int getBookCount() {
        return bookRepository.count();
    }

    // Factory pattern example
    public Book createBookUsingFactory(BookFactory.BookType type, String title,
                                       int authorId, String isbn, double price, int year) {
        logger.logInfo("Creating book using Factory Pattern, type: " + type);

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> {
                    String error = "Author not found with ID: " + authorId;
                    logger.logError(error);
                    return new RuntimeException(error);
                });

        Book book = BookFactory.createBook(type, title, author, isbn, price, year);
        book.validate();

        // Check if ISBN already exists
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            String error = "Book with ISBN " + book.getIsbn() + " already exists";
            logger.logError(error);
            throw new RuntimeException(error);
        }

        Book savedBook = bookRepository.save(book);
        // Инвалидация кэша, так как создана новая книга
        cache.remove(ALL_BOOKS_KEY);
        logger.logInfo("Cache invalidated after factory book creation");
        return savedBook;
    }

    // Builder pattern example
    public Book createBookUsingBuilder(String title, int authorId, String isbn,
                                       double price, int year, String description,
                                       String genre, int pageCount) {
        logger.logInfo("Creating book using Builder Pattern");

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> {
                    String error = "Author not found with ID: " + authorId;
                    logger.logError(error);
                    return new RuntimeException(error);
                });

        Book book = BookBuilder.builder()
                .setTitle(title)
                .setAuthor(author)
                .setIsbn(isbn)
                .setPrice(price)
                .setPublicationYear(year)
                .setDescription(description)
                .setPageCount(pageCount)
                .setPublisher("Library Publishing")
                .setGenre(genre)
                .setLanguage("English")
                .build();

        book.validate();

        // Check if ISBN already exists
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            String error = "Book with ISBN " + book.getIsbn() + " already exists";
            logger.logError(error);
            throw new RuntimeException(error);
        }

        Book savedBook = bookRepository.save(book);
        // Инвалидация кэша
        cache.remove(ALL_BOOKS_KEY);
        logger.logInfo("Cache invalidated after builder book creation");
        return savedBook;
    }
}