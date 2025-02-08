package com.example.appointmentservice.service;

import io.micrometer.tracing.annotation.NewSpan;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AppointmentService {

    private final Map<Long, String> appointments = new HashMap<>();

    public AppointmentService() {
        appointments.put(1001L, "Patient 1 - Dr. John Doe - 10:00 AM");
        appointments.put(1002L, "Patient 2 - Dr. Jane Smith - 11:00 AM");
        appointments.put(1003L, "Patient 3 - Dr. Emily Johnson - 12:00 PM");
    }

    @NewSpan("fetch-appointment-details")
    public String getAppointmentById(Long id) {
        // Simulating a database call
        return appointments.getOrDefault(id, "Appointment not found");
    }
}
