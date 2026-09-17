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
                Book book = Book.fromCsv(line);
                if (book != null) {
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
        
        defaultBooks.add(new Book("101", "Effective Java", "Joshua Bloch", false));
        defaultBooks.add(new Book("102", "Clean Code", "Robert C. Martin", false));
        defaultBooks.add(new Book("103", "Design Patterns", "Erich Gamma", false));

        saveBooks(defaultBooks);
        return defaultBooks;
    }

    public void saveBooks(Collection<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.write(book.toCsv() + "\n");
            }
            System.out.println("Catalog state saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
