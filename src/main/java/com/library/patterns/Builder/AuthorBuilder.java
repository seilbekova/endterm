package com.library.patterns.Builder;

import com.library.model.Author;

public class AuthorBuilder {
    private int id;
    private String name;
    private String nationality;
    private String biography;
    private int birthYear;
    private String website;

    public AuthorBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public AuthorBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AuthorBuilder setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public AuthorBuilder setBiography(String biography) {
        this.biography = biography;
        return this;
    }

    public AuthorBuilder setBirthYear(int birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public AuthorBuilder setWebsite(String website) {
        this.website = website;
        return this;
    }

    public Author build() {
        if (name == null) {
            throw new IllegalStateException("Author name is required");
        }
        if (nationality == null) {
            throw new IllegalStateException("Author nationality is required");
        }

        Author author = new Author(id, name, nationality);
        System.out.println("Built author: " + name + " from " + nationality);
        System.out.println("Additional info - Birth Year: " + birthYear +
                ", Website: " + (website != null ? website : "N/A"));

        return author;
    }

    public static AuthorBuilder builder() {
        return new AuthorBuilder();
    }
}






