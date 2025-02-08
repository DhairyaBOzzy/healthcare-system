package com.example.patientservice.controller;

import com.example.patientservice.entity.Patient;
import com.example.patientservice.service.PatientService;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final Tracer tracer;
    private final PatientService patientService;
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    public PatientController(Tracer tracer, PatientService patientService) {
        this.tracer = tracer;
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        Span span = tracer.nextSpan().name("getAllPatients").start();
        try {
            logger.info("Fetching all patients");
            return patientService.getAllPatients();
        } finally {
            span.end();
        }
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        Span span = tracer.nextSpan().name("getPatientById").start();
        try {
            logger.info("Fetching patient with ID: {}", id);
            return patientService.getPatientById(id);
        } finally {
            span.end();
        }
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        Span span = tracer.nextSpan().name("createPatient").start();
        try {
            logger.info("Creating new patient: {}", patient.getName());
            return patientService.savePatient(patient);
        } finally {
            span.end();
        }
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient updated) {
        Span span = tracer.nextSpan().name("updatePatient").start();
        try {
            logger.info("Updating patient with ID: {}", id);
            Optional<Patient> existingOptional = patientService.getPatientById(id);
            if (existingOptional.isPresent()) {
                Patient existing = existingOptional.get();
                existing.setName(updated.getName());
                existing.setAddress(updated.getAddress());
                existing.setDob(updated.getDob());
                return patientService.savePatient(existing);
            } else {
                throw new RuntimeException("Patient not found: " + id);
            }
        } finally {
            span.end();
        }
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        Span span = tracer.nextSpan().name("deletePatient").start();
        try {
            logger.info("Deleting patient with ID: {}", id);
            patientService.deletePatient(id);
        } finally {
            span.end();
        }
    }

    @GetMapping("/trace-test")
    public String traceTest() {
        Span span = tracer.nextSpan().name("manualTraceTest").start();
        try {
            logger.info("Manually creating trace span: manualTraceTest");
            return "Tracing Test - Span Created!";
        } finally {
            span.end();
        }
    }
}
