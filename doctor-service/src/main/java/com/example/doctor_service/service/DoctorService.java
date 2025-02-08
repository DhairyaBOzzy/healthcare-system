package com.example.doctor_service.service;

import io.micrometer.tracing.annotation.NewSpan;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class DoctorService {
    
    private final Map<Long, String> doctors = new HashMap<>();

    public DoctorService() {
        doctors.put(1L, "Dr. John Doe - Cardiologist");
        doctors.put(2L, "Dr. Jane Smith - Neurologist");
        doctors.put(3L, "Dr. Emily Johnson - Orthopedic");
    }

    @NewSpan("fetch-doctor-details")
    public String getDoctorById(Long id) {
        // Simulating a database call
        return doctors.getOrDefault(id, "Doctor not found");
    }
}
