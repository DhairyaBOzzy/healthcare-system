package com.example.appointmentservice.dto;

import lombok.Data;

@Data
public class DoctorResponse {
    private Long id;
    private String name;
    private String specialty;
}
