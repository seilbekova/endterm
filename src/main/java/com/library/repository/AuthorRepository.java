package com.library.repository;

import com.library.model.Author;
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
public class AuthorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("nationality")
            );
        }
    }

    public List<Author> findAll() {
        String sql = "SELECT * FROM authors ORDER BY name";
        return jdbcTemplate.query(sql, new AuthorRowMapper());
    }

    public Optional<Author> findById(int id) {
        try {
            String sql = "SELECT * FROM authors WHERE id = ?";
            Author author = jdbcTemplate.queryForObject(sql, new AuthorRowMapper(), id);
            return Optional.ofNullable(author);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Author save(Author author) {
        if (author.getId() == 0) {
            // Insert new author
            String sql = "INSERT INTO authors (name, nationality) VALUES (?, ?)";
            jdbcTemplate.update(sql, author.getName(), author.getNationality());

            // Get the generated ID
            Integer id = jdbcTemplate.queryForObject(
                    "SELECT currval('authors_id_seq')", Integer.class
            );
            author.setId(id != null ? id : 0);
        } else {
            // Update existing author
            String sql = "UPDATE authors SET name = ?, nationality = ? WHERE id = ?";
            jdbcTemplate.update(sql, author.getName(), author.getNationality(), author.getId());
        }
        return author;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM authors WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM authors WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    public List<Author> findByNameContaining(String name) {
        String sql = "SELECT * FROM authors WHERE LOWER(name) LIKE LOWER(?) ORDER BY name";
        return jdbcTemplate.query(sql, new AuthorRowMapper(), "%" + name + "%");
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM authors";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }
}






