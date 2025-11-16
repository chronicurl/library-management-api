package com.library.managementapi.repository;

import com.library.managementapi.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    // Spring Data JPA secara automatik akan menyediakan:
    // save(), findById(), findAll(), deleteById(), dll.
    
    // Kita juga akan tambah fungsi ini untuk pengesahan emel unik dalam lapisan Service
    boolean existsByEmail(String email);
}