package com.library.controller;

import com.library.dto.AuthorDTO;
import com.library.dto.AuthorRequest;
import com.library.model.Author;
import com.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "*")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        List<AuthorDTO> authorDTOs = authors.stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName(), author.getNationality()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        AuthorDTO authorDTO = new AuthorDTO(author.getId(), author.getName(), author.getNationality());
        return ResponseEntity.ok(authorDTO);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorRequest request) {
        Author author = new Author(0, request.getName(), request.getNationality());
        Author createdAuthor = authorService.createAuthor(author);
        AuthorDTO response = new AuthorDTO(createdAuthor.getId(), createdAuthor.getName(), createdAuthor.getNationality());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable int id, @RequestBody AuthorRequest request) {
        Author author = new Author(id, request.getName(), request.getNationality());
        Author updatedAuthor = authorService.updateAuthor(id, author);
        AuthorDTO response = new AuthorDTO(updatedAuthor.getId(), updatedAuthor.getName(), updatedAuthor.getNationality());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AuthorDTO>> searchAuthors(@RequestParam String name) {
        List<Author> authors = authorService.searchAuthors(name);
        List<AuthorDTO> authorDTOs = authors.stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName(), author.getNationality()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOs);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getAuthorCount() {
        int count = authorService.getAuthorCount();
        return ResponseEntity.ok(count);
    }
}








