package com.library.managementapi.controller;

import com.library.managementapi.dto.LoanRequest; // IMPORT BARU
import com.library.managementapi.model.Book;
import com.library.managementapi.model.Borrower;
import com.library.managementapi.model.Loan;
import com.library.managementapi.service.BookService;
import com.library.managementapi.service.BorrowerService;
import com.library.managementapi.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// import java.util.Map; <--- TIDAK PERLU LAGI

// Menandakan kelas ini sebagai Pengawal REST
@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {

    private final BookService bookService;
    private final BorrowerService borrowerService;
    private final LoanService loanService;

    @Autowired
    public LibraryController(BookService bookService, BorrowerService borrowerService, LoanService loanService) {
        this.bookService = bookService;
        this.borrowerService = borrowerService;
        this.loanService = loanService;
    }

    // --------------------------------------------------------------------------------
    // ENDPOINTS PENDAFTARAN & SENARAI BUKU
    // --------------------------------------------------------------------------------

    // 1. Register a new borrower
    @PostMapping("/borrowers")
    public ResponseEntity<Borrower> registerBorrower(@Valid @RequestBody Borrower borrower) {
        Borrower newBorrower = borrowerService.registerNewBorrower(borrower);
        return new ResponseEntity<>(newBorrower, HttpStatus.CREATED);
    }

    // 2. Register a new book
    @PostMapping("/books")
    public ResponseEntity<Book> registerBook(@Valid @RequestBody Book book) {
        Book newBook = bookService.registerNewBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    // 3. Get a list of all books
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // --------------------------------------------------------------------------------
    // ENDPOINTS TRANSAKSI (Borrow, Return)
    // --------------------------------------------------------------------------------

    // 4. Borrow a book
    @PostMapping("/borrow")
    public ResponseEntity<Loan> borrowBook(@Valid @RequestBody LoanRequest request) { // Guna DTO
        Long borrowerId = request.getBorrowerId();
        Long bookId = request.getBookId();

        Loan loan = loanService.borrowBook(borrowerId, bookId);
        return ResponseEntity.ok(loan);
    }

    // 5. Return a borrowed book
    @PostMapping("/return")
    public ResponseEntity<Loan> returnBook(@Valid @RequestBody LoanRequest request) { // Guna DTO
        Long borrowerId = request.getBorrowerId();
        Long bookId = request.getBookId();

        Loan loan = loanService.returnBook(borrowerId, bookId);
        return ResponseEntity.ok(loan);
    }
}