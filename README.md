# Modular Library Management System

A robust, modular, command-line Java Library Management System built using core Object-Oriented Programming (OOP) principles, Maven build automation, File I/O persistence, and JUnit 5 unit testing. 

This project was developed as part of the **VITyarthi - Build Your Own Project** coursework evaluation[cite: 1].

---

## 📌 Table of Contents
- [Project Overview](#-project-overview)
- [Features](#-features)
- [Technical Architecture](#-technical-architecture)
- [Technologies & Tools Used](#-technologies--tools-used)
- [Directory Structure](#-directory-structure)
- [Installation & Setup](#-installation-&-setup)
- [How to Run](#-how-to-run)
- [Running Unit Tests](#-running-unit-tests)
- [Data Storage Format](#-data-storage-format)

---

## 📖 Project Overview

The **Modular Library Management System** is a terminal-based Java application designed to streamline library operations including book catalog management, student registration, issuing/returning books, and persistent record storage using CSV File I/O.

### Core Objectives:
1. Provide an intuitive command-line interface for managing library inventories and student transactions.
2. Ensure strict data integrity using comprehensive exception handling and input validation.
3. Validate application logic through JUnit 5 unit testing.
4. Maintain high modularity through clear separation of concerns (Models, Services, Utilities, and UI Driver).

---

## ✨ Features

- **Book Inventory Management:** Add new books, search by title/author/ISBN, view all books, and check real-time availability status.
- **Student Management:** Register students and track book issue histories.
- **Transaction Processing:** Issue and return books with automated availability updates and state verification.
- **Persistent Data Storage:** File I/O service supporting automated saving and loading of library catalogs via `.csv` storage (`test_books.csv`).
- **Robust Error Handling:** Custom runtime exception classes (`InvalidInputException`) for handling illegal inputs and operational edge cases.

---

## 🛠 Technical Architecture

### Functional Requirements
1. **Catalog & Inventory Control:** Full lifecycle management of library books.
2. **Student Activity Tracking:** Association of issued books with student registration IDs.
3. **Data Persistence:** Automated reading and writing to structured file storage.

### Non-Functional Requirements
1. **Maintainability:** Layered package architecture separating data models (`com.app.model`), business services (`com.app.service`), utilities (`com.app.util`), and execution driver (`com.app.Main`).
2. **Usability:** Structured, interactive command-line interface with intuitive menus and status feedback.
3. **Reliability:** Automated unit test suites ensuring core business logic reliability under boundary conditions.
4. **Error Handling & Resilience:** Prevention of invalid file states, non-existent record searches, and double-issue operations.

---

## 🧰 Technologies & Tools Used

- **Programming Language:** Java 17
- **Build & Dependency Management:** Apache Maven 3.x
- **Testing Framework:** JUnit 5 (Jupiter)
- **Version Control:** Git & GitHub

---

## 📂 Directory Structure

```text
Modular-Library-Management-System/
├── pom.xml
├── README.md
├── statement.md
├── test_books.csv
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── app/
    │               ├── Main.java
    │               ├── model/
    │               │   ├── Book.java
    │               │   └── Student.java
    │               ├── service/
    │               │   ├── FileStorageService.java
    │               │   └── LibraryService.java
    │               └── util/
    │                   └── InvalidInputException.java
    └── test/
        └── java/
            └── com/
                └── app/
                    └── LibraryServiceTest.java
```
## ⚙️ Installation & Setup

### Prerequisites:
1. **JDK 17 or higher** 
2. **Apache Maven 3.6+** 
3. **Clone the Repository:**
   ```text
   git clone [https://github.com/drishya25bai10633-tech/Modular-Library-Management-System.git](https://github.com/drishya25bai10633-tech/Modular-Library-Management-System.git)
   cd Modular-Library-Management-System
   ```
4. **Build the project:** Compile source files and build the package using Maven:
   ```text
   mvn clean compile
   ```
## 🚀 How to Run
**Option 1: Run via Maven Execution Plugin**
   ```text
   mvn exec:java -Dexec.mainClass="com.app.Main"
   ```
**Option 2: Run directly using Java CLI** Compile classes and execute:
   ```text
   mvn clean package
   java -cp target/classes com.app.Main
   ```
## 🧪 Running Unit Tests
**The test suite validates LibraryService business logic including catalog operations, issue/return limits, and custom exception handling.**

**To execute all JUnit 5 tests:**
   ```text
   mvn test
   ```
**Expected Output**
   ```text
   [INFO] Running com.app.LibraryServiceTest
   [INFO] Tests run: X, Failures: 0, Errors: 0, Skipped: 0
   [INFO] BUILD SUCCESS
   ```
## 💾 Data Storage Format
**The library inventory is stored locally in ```test_books.csv``` using the following comma-separated structure:**
   ```text
   ISBN,Title,Author,IsIssued
   978-0134685991,Effective Java,Joshua Bloch,false
   978-0596009205,Head First Java,Kathy Sierra,false
   ```
