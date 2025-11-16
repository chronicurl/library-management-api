package com.library.managementapi.repository;

import com.library.managementapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Fungsi asas CRUD sedia ada.
    // findAll() di sini akan memenuhi Requirement 3: Get a list of all books in the library.
}