package com.app.model;

public class Book {
    private String id;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String id, String title, String author, boolean isBorrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isBorrowed() { return isBorrowed; }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String toCsv() {
        return id + "," + title.replace(",", " ") + "," + author.replace(",", " ") + "," + isBorrowed;
    }

    public static Book fromCsv(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) return null;
        return new Book(parts[0].trim(), parts[1].trim(), parts[2].trim(), Boolean.parseBoolean(parts[3].trim()));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s - %s", id, title, author, (isBorrowed ? "Checked Out" : "Available"));
    }
}
