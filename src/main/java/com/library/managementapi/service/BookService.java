package com.library.managementapi.service;

import com.library.managementapi.model.Book;
import com.library.managementapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Menandakan kelas ini sebagai Komponen Service Spring
public class BookService {

    private final BookRepository bookRepository;

    // Dependency Injection (DI) untuk Repository
    @Autowired 
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Mendaftar salinan buku baru ke dalam perpustakaan.
     * (Memenuhi Requirement 5: Register a new book to the library)
     */
    public Book registerNewBook(Book book) {
        // Pada lapisan ini, kita menganggap Book yang diterima (daripada Controller) 
        // sudah mempunyai ISBN, Title, dan Author yang tidak NULL (Validation akan berlaku di Controller).
        
        // Simpan buku tersebut. Spring Data JPA akan memberikannya ID yang unik.
        // (Memenuhi Requirement 24: Multiple books with the same ISBN number should be registered as books with different ids)
        return bookRepository.save(book);
    }

    /**
     * Mendapatkan senarai semua buku yang wujud di perpustakaan.
     * (Memenuhi Requirement 6: Get a list of all books in the library)
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Mendapatkan satu buku berdasarkan ID. Digunakan oleh LoanService.
     */
    public Book findBookById(Long bookId) {
        // Kami menggunakan orElseThrow di sini untuk mengendalikan ralat 404 (Not Found)
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));
    }
}