package com.library.repository;

import com.library.model.Author;
import com.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AuthorRepository authorRepository;

    private static final class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author(
                    rs.getInt("author_id"),
                    rs.getString("author_name"),
                    rs.getString("author_nationality")
            );

            return new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    author,
                    rs.getString("isbn"),
                    rs.getDouble("price"),
                    rs.getInt("publication_year")
            );
        }
    }

    public List<Book> findAll() {
        String sql = "SELECT b.*, a.name as author_name, a.nationality as author_nationality " +
                "FROM books b JOIN authors a ON b.author_id = a.id " +
                "ORDER BY b.title";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    public Optional<Book> findById(int id) {
        try {
            String sql = "SELECT b.*, a.name as author_name, a.nationality as author_nationality " +
                    "FROM books b JOIN authors a ON b.author_id = a.id " +
                    "WHERE b.id = ?";
            Book book = jdbcTemplate.queryForObject(sql, new BookRowMapper(), id);
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Book save(Book book) {
        if (book.getId() == 0) {
            // Insert new book
            String sql = "INSERT INTO books (title, author_id, isbn, price, publication_year) " +
                    "VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    book.getTitle(),
                    book.getAuthor().getId(),
                    book.getIsbn(),
                    book.getPrice(),
                    book.getPublicationYear()
            );

            // Get the generated ID
            Integer id = jdbcTemplate.queryForObject(
                    "SELECT currval('books_id_seq')", Integer.class
            );
            book.setId(id != null ? id : 0);
        } else {
            // Update existing book
            String sql = "UPDATE books SET title = ?, author_id = ?, isbn = ?, " +
                    "price = ?, publication_year = ? WHERE id = ?";
            jdbcTemplate.update(sql,
                    book.getTitle(),
                    book.getAuthor().getId(),
                    book.getIsbn(),
                    book.getPrice(),
                    book.getPublicationYear(),
                    book.getId()
            );
        }
        return book;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM books WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    public boolean existsByIsbn(String isbn) {
        String sql = "SELECT COUNT(*) FROM books WHERE isbn = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        return count != null && count > 0;
    }

    public List<Book> findByAuthorId(int authorId) {
        String sql = "SELECT b.*, a.name as author_name, a.nationality as author_nationality " +
                "FROM books b JOIN authors a ON b.author_id = a.id " +
                "WHERE b.author_id = ? ORDER BY b.publication_year";
        return jdbcTemplate.query(sql, new BookRowMapper(), authorId);
    }

    public List<Book> findByTitleContaining(String title) {
        String sql = "SELECT b.*, a.name as author_name, a.nationality as author_nationality " +
                "FROM books b JOIN authors a ON b.author_id = a.id " +
                "WHERE LOWER(b.title) LIKE LOWER(?) ORDER BY b.title";
        return jdbcTemplate.query(sql, new BookRowMapper(), "%" + title + "%");
    }

    public List<Book> search(String keyword) {
        String sql = "SELECT b.*, a.name as author_name, a.nationality as author_nationality " +
                "FROM books b JOIN authors a ON b.author_id = a.id " +
                "WHERE LOWER(b.title) LIKE LOWER(?) OR LOWER(a.name) LIKE LOWER(?) " +
                "OR LOWER(b.isbn) LIKE LOWER(?) ORDER BY b.title";
        String likeKeyword = "%" + keyword + "%";
        return jdbcTemplate.query(sql, new BookRowMapper(), likeKeyword, likeKeyword, likeKeyword);
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM books";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }
}









