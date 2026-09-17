# Problem Statement & Scope Document

## 1. Problem Statement
Traditional paper-based or unorganized library cataloging systems often suffer from manual tracking errors, misplaced book records, lack of real-time availability status, and inefficient student issuing workflows. Small-scale academic and institutional libraries require a lightweight, modular, and reliable terminal-based system to manage catalog inventories, track student transactions, and ensure persistent data storage without the overhead of heavy database software.

---

## 2. Scope of the Project
The **Modular Library Management System** provides a command-line interface (CLI) to automate core library operations. 

### In-Scope:
- Full CRUD-style lifecycle management for library books (Adding, Searching, Viewing, and Updating availability).
- Student registration and mapping issued books to student IDs.
- Business rule enforcement for book issuance (e.g., preventing double-issuing or returning non-issued books).
- Persistent storage of catalog data using File I/O (`.csv` files).
- Automated unit testing of core business logic using JUnit 5.

### Out-of-Scope:
- Graphical User Interface (GUI) or Web-based dashboard.
- Automated fine calculation algorithms for overdue returns.
- SQL/NoSQL external database system integration.

---

## 3. Target Users
- **Library Administrators / Staff:** Users who manage the book catalog, register new inventory, and perform issue/return operations.
- **Students / Borrowers:** End-users whose borrowing histories and issued book statuses are tracked by the system.
- **Academic Evaluators:** Instructors verifying Object-Oriented Programming (OOP) concepts, file persistence, and unit test implementations.

---

## 4. High-Level Features
- **Book Inventory Service:** Real-time search across titles, authors, and unique ISBN identifiers.
- **Student Management:** Association of library transactions with specific student registration accounts.
- **File I/O Data Persistence:** Automatic saving and loading of inventory states using structured CSV parsing.
- **Custom Exception Handling:** Robust validation and runtime error handling (`InvalidInputException`) to prevent corrupt system states.
- **Automated Validation:** Test suite execution covering core catalog operations and transactional boundaries.
