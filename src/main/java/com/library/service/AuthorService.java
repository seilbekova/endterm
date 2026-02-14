package com.library.service;

import com.library.model.Author;
import com.library.patterns.Builder.AuthorBuilder;
import com.library.patterns.Singleton.LoggerService;
import com.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    private final LoggerService logger = LoggerService.getInstance();

    public List<Author> getAllAuthors() {
        logger.logInfo("Fetching all authors");
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id) {
        logger.logInfo("Fetching author with ID: " + id);
        return authorRepository.findById(id)
                .orElseThrow(() -> {
                    String error = "Author not found with ID: " + id;
                    logger.logError(error);
                    return new RuntimeException(error);
                });
    }

    public Author createAuthor(Author author) {
        logger.logInfo("Creating new author: " + author.getName());
        author.validate();
        Author savedAuthor = authorRepository.save(author);
        logger.logInfo("Author created successfully with ID: " + savedAuthor.getId());
        return savedAuthor;
    }

    public Author updateAuthor(int id, Author author) {
        logger.logInfo("Updating author with ID: " + id);

        if (!authorRepository.existsById(id)) {
            String error = "Author not found with ID: " + id;
            logger.logError(error);
            throw new RuntimeException(error);
        }

        author.setId(id);
        author.validate();
        Author updatedAuthor = authorRepository.save(author);
        logger.logInfo("Author updated successfully with ID: " + id);
        return updatedAuthor;
    }

    public void deleteAuthor(int id) {
        logger.logInfo("Deleting author with ID: " + id);

        if (!authorRepository.existsById(id)) {
            String error = "Author not found with ID: " + id;
            logger.logError(error);
            throw new RuntimeException(error);
        }

        authorRepository.delete(id);
        logger.logInfo("Author deleted successfully with ID: " + id);
    }

    public List<Author> searchAuthors(String name) {
        logger.logInfo("Searching authors with name containing: " + name);
        return authorRepository.findByNameContaining(name);
    }

    public int getAuthorCount() {
        return authorRepository.count();
    }

    // Builder pattern example
    public Author createAuthorUsingBuilder(String name, String nationality,
                                           String biography, int birthYear) {
        logger.logInfo("Creating author using Builder pattern: " + name);

        Author author = AuthorBuilder.builder()
                .setName(name)
                .setNationality(nationality)
                .setBiography(biography)
                .setBirthYear(birthYear)
                .build();

        author.validate();
        return authorRepository.save(author);
    }
}








