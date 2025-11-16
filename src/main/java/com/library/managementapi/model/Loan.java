package com.library.managementapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

// Lombok annotations
@Data
@NoArgsConstructor
@AllArgsConstructor

// JPA annotations
@Entity
@Table(name = "loans") // Jadual ini merekodkan semua transaksi pinjaman
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Hubungan ManyToOne: Ramai pinjaman boleh merujuk kepada satu Borrower
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id", nullable = false)
    private Borrower borrower;

    // Hubungan OneToOne Logik: Satu buku (Book ID) hanya boleh dipinjam sekali pada satu masa.
    // Kita gunakan ManyToOne kerana Buku boleh dipinjam berulang kali (tetapi pada masa yang berbeza).
    // Penting: Pastikan buku yang dirujuk tidak sedang dipinjam (returnDate=NULL).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private LocalDate loanDate; // Tarikh pinjaman

    // Tarikh Pemulangan: NULL jika buku masih dipinjam
    private LocalDate returnDate;

    // Catatan: Constraint untuk memastikan buku TIDAK dipinjam dua kali akan dikendalikan dalam SERVICE layer.
}