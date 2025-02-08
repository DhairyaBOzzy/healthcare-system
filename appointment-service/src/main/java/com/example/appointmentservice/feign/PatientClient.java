package com.example.appointmentservice.feign;

import com.example.appointmentservice.dto.PatientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PATIENT-SERVICE", url = "http://localhost:8081") 
public interface PatientClient {
    @GetMapping("/patient/{id}")
    PatientResponse getPatientById(@PathVariable("id") Long id);
}
