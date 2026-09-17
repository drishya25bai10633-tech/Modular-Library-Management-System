package com.app.service;

import com.app.model.Book;
import java.io.*;
import java.util.*;

public class FileStorageService {
    private final String filePath;

    public FileStorageService(String filePath) {
        this.filePath = filePath;
    }

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
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
                    books.add(book);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return books;
    }

    private List<Book> seedInitialData() {
        List<Book> defaultBooks = new ArrayList<>();
        
        defaultBooks.add(new Book("Effective Java", "Joshua Bloch", "101", false));
        defaultBooks.add(new Book("Clean Code", "Robert C. Martin", "102", false));
        defaultBooks.add(new Book("Design Patterns", "Erich Gamma", "103", false));

        saveBooks(defaultBooks);
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
