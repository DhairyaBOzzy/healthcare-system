package com.example.patientservice.repository;

import com.example.patientservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The PatientRepository interface provides all CRUD operations
 * (create, read, update, delete) for the 'Patient' entity by 
 * extending JpaRepository.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // No extra methods needed for basic CRUD.
    // Spring Data JPA implements them at runtime.
}
