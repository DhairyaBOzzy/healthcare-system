package com.example.patientservice.service;

import com.example.patientservice.entity.Patient;
import com.example.patientservice.repository.PatientRepository;
import io.micrometer.tracing.annotation.NewSpan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @NewSpan("fetch-all-patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @NewSpan("fetch-patient-by-id")
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @NewSpan("save-patient")
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @NewSpan("delete-patient")
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
