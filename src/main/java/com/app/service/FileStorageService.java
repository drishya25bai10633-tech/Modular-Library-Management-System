package com.app.service;

import com.app.model.Book;
import java.io.*;
import java.util.*;

public class FileStorageService {
    private final String filePath;

    public FileStorageService(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Book> loadBooks() {
        Map<String, Book> books = new HashMap<>();
        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            System.out.println("No existing catalog found. Seeding initial library data...");
            return seedInitialData();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String isbn = parts[0].trim();
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    boolean isIssued = Boolean.parseBoolean(parts[3].trim());
                    
                    Book book = new Book(title, author, isbn, isIssued);
                    books.put(isbn, book);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return books;
    }

    private Map<String, Book> seedInitialData() {
        Map<String, Book> defaultBooks = new HashMap<>();
        
        Book b1 = new Book("Effective Java", "Joshua Bloch", "101", false);
        Book b2 = new Book("Clean Code", "Robert C. Martin", "102", false);
        Book b3 = new Book("Design Patterns", "Erich Gamma", "103", false);

        defaultBooks.put("101", b1);
        defaultBooks.put("102", b2);
        defaultBooks.put("103", b3);

        saveBooks(defaultBooks.values());
        return defaultBooks;
    }

    public void saveBooks(Collection<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.write(String.format("%s,%s,%s,%b\n",
                        book.getIsbn(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.isIssued()));
            }
            System.out.println("Catalog state saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
