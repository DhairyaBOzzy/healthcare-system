package com.example.appointmentservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientResponse {
    private Long id;
    private String name;
    private String address;
    private LocalDate dob;
}
