package com.library.model;

public class EBook extends Book {
    private String format;
    private double fileSize;

    public EBook() {}

    public EBook(int id, String title, Author author, String isbn,
                 double price, int publicationYear) {
        super(id, title, author, isbn, price, publicationYear);
        this.format = "PDF";
        this.fileSize = 2.5;
    }

    public EBook(int id, String title, Author author, String isbn,
                 double price, int publicationYear, String format, double fileSize) {
        super(id, title, author, isbn, price, publicationYear);
        this.format = format;
        this.fileSize = fileSize;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + String.format(" | Format: %s | File Size: %.1fMB",
                format, fileSize);
    }

    @Override
    public void printBasicInfo() {
        super.printBasicInfo();
        System.out.printf("  Format: %s | File Size: %.1fMB%n", format, fileSize);
    }

    // Getters and Setters
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }
}







