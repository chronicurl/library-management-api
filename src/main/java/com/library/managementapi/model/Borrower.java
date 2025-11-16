package com.library.managementapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// Lombok annotations for boilerplate code
@Data // Creates getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Creates a constructor with no arguments
@AllArgsConstructor // Creates a constructor with all arguments

// JPA annotations
@Entity // Marks this class as a JPA entity (a database table)
@Table(name = "borrowers") // Defines the table name in the database
public class Borrower {

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID
    private Long id;

    @Column(nullable = false) // Ensures the name field cannot be null
    private String name;

    @Column(nullable = false, unique = true) // Ensures email is required and UNIQUE
    private String email;

    // Catatan: Jika anda tidak menggunakan Lombok, anda perlu menulis secara manual
    // Getter, Setter, dan Constructors di sini.
}