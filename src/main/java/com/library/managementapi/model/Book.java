package com.library.managementapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// Lombok annotations
@Data
@NoArgsConstructor
@AllArgsConstructor

// JPA annotations
@Entity // Mapped to a database table
@Table(name = "books")
public class Book {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Unique ID for EACH copy (Requirement 24)
    private Long id;

    @Column(nullable = false)
    private String isbn; // ISBN number (Requirement 11)

    @Column(nullable = false)
    private String title; // Title (Requirement 11)

    @Column(nullable = false)
    private String author; // Author (Requirement 11)

    // Catatan: Walaupun banyak salinan boleh mempunyai ISBN yang sama,
    // setiap objek Book ini mempunyai ID yang unik[cite: 24].
}