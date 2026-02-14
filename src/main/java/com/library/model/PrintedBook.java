package com.library.model;

public class PrintedBook extends Book {
    private int pageCount;
    private String coverType;

    public PrintedBook() {}

    public PrintedBook(int id, String title, Author author, String isbn,
                       double price, int publicationYear) {
        super(id, title, author, isbn, price, publicationYear);
        this.pageCount = 300;
        this.coverType = "Hardcover";
    }

    public PrintedBook(int id, String title, Author author, String isbn,
                       double price, int publicationYear, int pageCount, String coverType) {
        super(id, title, author, isbn, price, publicationYear);
        this.pageCount = pageCount;
        this.coverType = coverType;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + String.format(" | Pages: %d | Cover: %s",
                pageCount, coverType);
    }

    @Override
    public void printBasicInfo() {
        super.printBasicInfo();
        System.out.printf("  Pages: %d | Cover: %s%n", pageCount, coverType);
    }

    // Getters and Setters
    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }
}







