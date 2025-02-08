package com.example.appointmentservice.controller;

import com.example.appointmentservice.feign.PatientClient;
import com.example.appointmentservice.feign.DoctorClient;
import com.example.appointmentservice.dto.PatientResponse;
import com.example.appointmentservice.dto.DoctorResponse;
import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import feign.FeignException;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final PatientClient patientClient; // Inject Feign client
    private final DoctorClient doctorClient;   // Inject Doctor Feign client

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @RestController
    public class HomeController {

        @GetMapping("/")
        public String home() {
            return "This is the Patient Service!";
        }
}

    @GetMapping("/{id}")
    public Appointment getAppointment(@PathVariable Long id) {
        return appointmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        try {
            // Validate patient ID
            PatientResponse patient = patientClient.getPatientById(appointment.getPatientId());
            if (patient == null) {
                throw new RuntimeException("Patient not found with ID " + appointment.getPatientId());
            }
        } catch (FeignException e) {
            throw new RuntimeException("Failed to fetch patient details. Service might be unavailable.");
        }

        try {
            // Validate doctor ID
            DoctorResponse doctor = doctorClient.getDoctorById(appointment.getDoctorId());
            if (doctor == null) {
                throw new RuntimeException("Doctor not found with ID " + appointment.getDoctorId());
            }
        } catch (FeignException e) {
            throw new RuntimeException("Failed to fetch doctor details. Service might be unavailable.");
        }

        // Save the appointment
        return appointmentRepository.save(appointment);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment updated) {
        Appointment existing = appointmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
        existing.setPatientId(updated.getPatientId());
        existing.setDoctorId(updated.getDoctorId());
        existing.setAppointmentTime(updated.getAppointmentTime());
        existing.setDescription(updated.getDescription());
        return appointmentRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
    }
}
