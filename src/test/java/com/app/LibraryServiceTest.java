package com.app;

import com.app.model.Book;
import com.app.service.FileStorageService;
import com.app.service.LibraryService;
import com.app.util.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryServiceTest {
    private LibraryService service;
    private final String TEST_FILE = "test_books.csv";

    @BeforeEach
    void setUp() {
        File f = new File(TEST_FILE);
        if (f.exists()) f.delete();

        FileStorageService storage = new FileStorageService(TEST_FILE);
        service = new LibraryService(storage);
    }

    @Test
    void testAddAndRetrieveBook() throws InvalidInputException {
        service.addBook("B101", "Clean Code", "Robert C Martin");
        List<Book> books = service.getAllBooks();
        assertEquals(1, books.size());
        assertEquals("Clean Code", books.get(0).getTitle());
    }

    @Test
    void testDuplicateBookException() throws InvalidInputException {
        service.addBook("B101", "Clean Code", "Robert C Martin");
        assertThrows(InvalidInputException.class, () -> {
            service.addBook("B101", "Design Patterns", "Gang of Four");
        });
    }

    @Test
    void testCheckoutAndReturnFlow() throws InvalidInputException {
        service.addBook("B102", "Effective Java", "Joshua Bloch");
        assertTrue(service.checkoutBook("B102"));
        assertFalse(service.checkoutBook("B102"));
        assertTrue(service.returnBook("B102"));
    }
}
