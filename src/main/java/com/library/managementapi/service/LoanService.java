package com.library.managementapi.service;

import com.library.managementapi.model.Book;
import com.library.managementapi.model.Borrower;
import com.library.managementapi.model.Loan;
import com.library.managementapi.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final BorrowerService borrowerService;

    // Dependency Injection
    @Autowired
    public LoanService(LoanRepository loanRepository, BookService bookService, BorrowerService borrowerService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.borrowerService = borrowerService;
    }

    /**
     * Logik untuk meminjam buku.
     * (Memenuhi Requirement: Borrow a book with a particular book id )
     */
    public Loan borrowBook(Long borrowerId, Long bookId) {
        // 1. Dapatkan Entiti (Pengesahan wujud - 404 Error Handling [cite: 20])
        Borrower borrower = borrowerService.findBorrowerById(borrowerId);
        Book book = bookService.findBookById(bookId);

        // 2. VALIDASI: Semak buku sedang dipinjam (Requirement 7 )
        if (loanRepository.findByBookAndReturnDateIsNull(book).isPresent()) {
            // Jika hasilnya ada, maknanya buku sedang dipinjam.
            throw new DuplicateResourceException("Book with ID " + bookId + " is currently borrowed and unavailable.");
        }

        // 3. Cipta transaksi Loan baru
        Loan newLoan = new Loan();
        newLoan.setBorrower(borrower);
        newLoan.setBook(book);
        newLoan.setLoanDate(LocalDate.now());
        // returnDate kekal NULL (menandakan pinjaman AKTIF)

        return loanRepository.save(newLoan);
    }

    /**
     * Logik untuk memulangkan buku.
     * (Memenuhi Requirement: Return a borrowed book )
     */
    public Loan returnBook(Long borrowerId, Long bookId) {
        // 1. Dapatkan Entiti (Pengesahan wujud - 404 Error Handling)
        borrowerService.findBorrowerById(borrowerId);
        bookService.findBookById(bookId);

        // 2. Cari pinjaman AKTIF (returnDate IS NULL) untuk peminjam dan buku ini (Requirement 20)
        Loan activeLoan = loanRepository.findByBorrowerIdAndBookIdAndReturnDateIsNull(borrowerId, bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book ID " + bookId + " is not currently borrowed by Borrower ID " + borrowerId + "."));

        // 3. Kemas kini transaksi Loan: Tetapkan returnDate
        activeLoan.setReturnDate(LocalDate.now());

        return loanRepository.save(activeLoan);
    }
}