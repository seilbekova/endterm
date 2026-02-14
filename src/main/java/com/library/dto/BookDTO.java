package com.library.dto;

public class BookDTO {
    private int id;
    private String title;
    private int authorId;
    private String authorName;
    private String isbn;
    private double price;
    private int publicationYear;

    public BookDTO() {}

    public BookDTO(int id, String title, int authorId, String authorName,
                   String isbn, double price, int publicationYear) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.authorName = authorName;
        this.isbn = isbn;
        this.price = price;
        this.publicationYear = publicationYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}








