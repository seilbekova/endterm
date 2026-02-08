package com.library.controller;

import com.library.dto.BookDTO;
import com.library.dto.BookRequest;
import com.library.model.Author;
import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDTO> bookDTOs = books.stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor().getId(),
                        book.getAuthor().getName(),
                        book.getIsbn(),
                        book.getPrice(),
                        book.getPublicationYear()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        BookDTO bookDTO = new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getId(),
                book.getAuthor().getName(),
                book.getIsbn(),
                book.getPrice(),
                book.getPublicationYear()
        );
        return ResponseEntity.ok(bookDTO);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookRequest request) {
        Author author = new Author();
        author.setId(request.getAuthorId());

        Book book = new Book(
                0,
                request.getTitle(),
                author,
                request.getIsbn(),
                request.getPrice(),
                request.getPublicationYear()
        );

        Book createdBook = bookService.createBook(book);

        BookDTO response = new BookDTO(
                createdBook.getId(),
                createdBook.getTitle(),
                createdBook.getAuthor().getId(),
                createdBook.getAuthor().getName(),
                createdBook.getIsbn(),
                createdBook.getPrice(),
                createdBook.getPublicationYear()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable int id, @RequestBody BookRequest request) {
        Author author = new Author();
        author.setId(request.getAuthorId());

        Book book = new Book(
                id,
                request.getTitle(),
                author,
                request.getIsbn(),
                request.getPrice(),
                request.getPublicationYear()
        );

        Book updatedBook = bookService.updateBook(id, book);

        BookDTO response = new BookDTO(
                updatedBook.getId(),
                updatedBook.getTitle(),
                updatedBook.getAuthor().getId(),
                updatedBook.getAuthor().getName(),
                updatedBook.getIsbn(),
                updatedBook.getPrice(),
                updatedBook.getPublicationYear()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable int authorId) {
        List<Book> books = bookService.getBooksByAuthor(authorId);
        List<BookDTO> bookDTOs = books.stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor().getId(),
                        book.getAuthor().getName(),
                        book.getIsbn(),
                        book.getPrice(),
                        book.getPublicationYear()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam String keyword) {
        List<Book> books = bookService.searchBooks(keyword);
        List<BookDTO> bookDTOs = books.stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor().getId(),
                        book.getAuthor().getName(),
                        book.getIsbn(),
                        book.getPrice(),
                        book.getPublicationYear()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getBookCount() {
        int count = bookService.getBookCount();
        return ResponseEntity.ok(count);
    }
}






