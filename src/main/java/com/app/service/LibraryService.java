package com.app.service;

import com.app.model.Book;
import com.app.util.InvalidInputException;

import java.util.*;

public class LibraryService {
    private final Map<String, Book> catalog = new HashMap<>();
    private final FileStorageService storage;

    public LibraryService(FileStorageService storage) {
        this.storage = storage;
        for (Book b : storage.loadBooks()) {
            catalog.put(b.getId(), b);
        }
    }

    public void addBook(String id, String title, String author) throws InvalidInputException {
        if (id == null || id.isBlank() || title == null || title.isBlank()) {
            throw new InvalidInputException("Book ID and Title cannot be empty.");
        }
        if (catalog.containsKey(id)) {
            throw new InvalidInputException("Book with ID " + id + " already exists.");
        }

        Book b = new Book(id.trim(), title.trim(), author.trim(), false);
        catalog.put(b.getId(), b);
        storage.saveBooks(catalog.values());
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>(catalog.values());
        books.sort(Comparator.comparing(Book::getId));
        return books;
    }

    public List<Book> search(String query) {
        String q = query.toLowerCase();
        List<Book> res = new ArrayList<>();
        for (Book b : catalog.values()) {
            if (b.getTitle().toLowerCase().contains(q) || b.getAuthor().toLowerCase().contains(q)) {
                res.add(b);
            }
        }
        return res;
    }

    public boolean checkoutBook(String id) throws InvalidInputException {
        Book b = catalog.get(id);
        if (b == null) {
            throw new InvalidInputException("Book ID not found.");
        }
        if (b.isBorrowed()) {
            return false;
        }
        b.setBorrowed(true);
        storage.saveBooks(catalog.values());
        return true;
    }

    public boolean returnBook(String id) throws InvalidInputException {
        Book b = catalog.get(id);
        if (b == null) {
            throw new InvalidInputException("Book ID not found.");
        }
        if (!b.isBorrowed()) {
            return false;
        }
        b.setBorrowed(false);
        storage.saveBooks(catalog.values());
        return true;
    }
}
