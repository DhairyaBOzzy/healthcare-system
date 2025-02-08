package com.example.patientservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;



/**
 * The Patient entity represents a patient record in our system.
 * 
 * @Entity: Tells JPA/Hibernate this class maps to a database table.
 */
@Entity               // Marks this class as a JPA entity/table
@Data                 // Lombok annotation: generates getters, setters, toString, equals, etc.
@NoArgsConstructor    // Lombok: creates a no-argument constructor
@AllArgsConstructor   // Lombok: creates an all-arguments constructor
@Builder             // Lombok: for easy builder pattern creation
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          // Primary key (auto-increment)

    private String name;
    private String address;
    private LocalDate dob;    // Date of birth
}
