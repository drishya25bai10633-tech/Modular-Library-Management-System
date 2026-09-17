package com.app;

import com.app.model.Book;
import com.app.service.FileStorageService;
import com.app.service.LibraryService;
import com.app.util.InvalidInputException;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileStorageService storage = new FileStorageService("library_data.csv");
        LibraryService service = new LibraryService(storage);
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Terminal Library Manager ===");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. List all books");
            System.out.println("2. Add new book");
            System.out.println("3. Search catalog");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Exit");
            System.out.print("> ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    List<Book> books = service.getAllBooks();
                    if (books.isEmpty()) {
                        System.out.println("No records found in inventory.");
                    } else {
                        System.out.println("\nCurrent Inventory:");
                        books.forEach(System.out::println);
                    }
                    break;

                case "2":
                    try {
                        System.out.print("Enter ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Author: ");
                        String author = sc.nextLine();

                        service.addBook(id, title, author);
                        System.out.println("Book added successfully!");
                    } catch (InvalidInputException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "3":
                    System.out.print("Enter search keyword: ");
                    String term = sc.nextLine();
                    List<Book> results = service.search(term);
                    if (results.isEmpty()) {
                        System.out.println("No matching books found.");
                    } else {
                        System.out.println("\nMatching Books:");
                        results.forEach(System.out::println);
                    }
                    break;

                case "4":
                    System.out.print("Enter Book ID to borrow: ");
                    String bId = sc.nextLine();
                    try {
                        if (service.checkoutBook(bId)) {
                            System.out.println("Success! Book checked out.");
                        } else {
                            System.out.println("Notice: Book is already borrowed.");
                        }
                    } catch (InvalidInputException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "5":
                    System.out.print("Enter Book ID to return: ");
                    String rId = sc.nextLine();
                    try {
                        if (service.returnBook(rId)) {
                            System.out.println("Success! Book returned.");
                        } else {
                            System.out.println("Notice: Book was not checked out.");
                        }
                    } catch (InvalidInputException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "6":
                    System.out.println("Exiting application.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid selection. Please choose 1-6.");
            }
        }
    }
}
