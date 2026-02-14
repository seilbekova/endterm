// src/main/java/com/library/model/Author.java
package com.library.model;

public class Author extends BaseEntity {
    private String name;
    private String nationality;

    public Author() {}

    public Author(int id, String name, String nationality) {
        super(id);
        this.name = name;
        this.nationality = nationality;
    }

    @Override
    public String getDetails() {
        return String.format("Author: %s (ID: %d, Nationality: %s)",
                name, id, nationality);
    }

    @Override
    public void printBasicInfo() {
        System.out.printf("Author ID: %d | Name: %s | Nationality: %s%n",
                id, name, nationality);
    }

    @Override
    public void validate() {
        super.validate();
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty");
        }
        if (nationality == null || nationality.trim().isEmpty()) {
            throw new IllegalArgumentException("Author nationality cannot be empty");
        }
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}








