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
        List<Book> list = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return list;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Book b = Book.fromCsv(line);
                if (b != null) {
                    list.add(b);
                }
            }
        } catch (IOException e) {
            System.err.println("Warning: Couldn't read storage file: " + e.getMessage());
        }
        return list;
    }

    public void saveBooks(Collection<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book b : books) {
                writer.write(b.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving state to disk: " + e.getMessage());
        }
    }
}
