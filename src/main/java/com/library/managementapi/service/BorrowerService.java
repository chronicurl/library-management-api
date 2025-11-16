package com.library.managementapi.service;

import com.library.managementapi.model.Borrower;
import com.library.managementapi.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service // Menandakan kelas ini sebagai Komponen Service Spring
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    @Autowired 
    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    /**
     * Mendaftar peminjam baru.
     * (Memenuhi Requirement 4: Register a new borrower to the library)
     */
    public Borrower registerNewBorrower(Borrower borrower) {
        // --- VALIDATION: Pastikan emel unik (Requirement 20) ---
        if (borrowerRepository.existsByEmail(borrower.getEmail())) {
            // Jika emel sudah wujud, campakkan Exception khas
            throw new DuplicateResourceException("Borrower with email " + borrower.getEmail() + " already exists.");
        }
        
        return borrowerRepository.save(borrower);
    }

    /**
     * Mendapatkan peminjam berdasarkan ID. Digunakan oleh LoanService.
     */
    public Borrower findBorrowerById(Long borrowerId) {
        // Menggunakan ResourceNotFoundException yang kita cipta tadi (Error Handling 404)
        return borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with ID: " + borrowerId));
    }
}