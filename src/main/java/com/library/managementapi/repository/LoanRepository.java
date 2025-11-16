package com.library.managementapi.repository;

import com.library.managementapi.model.Book;
import com.library.managementapi.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Kaedah Khas: Mencari Pinjaman AKTIF untuk Buku Tertentu
    // Spring Data JPA menterjemahkan nama kaedah ini kepada query SQL:
    // "Cari satu Loan di mana book_id = :book dan returnDate adalah NULL"
    Optional<Loan> findByBookAndReturnDateIsNull(Book book);

    // Kaedah Khas: Mencari Pinjaman AKTIF untuk Peminjam dan Buku Tertentu
    Optional<Loan> findByBorrowerIdAndBookIdAndReturnDateIsNull(Long borrowerId, Long bookId);
}