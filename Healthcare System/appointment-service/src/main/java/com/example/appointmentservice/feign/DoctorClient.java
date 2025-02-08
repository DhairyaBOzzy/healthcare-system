package com.example.appointmentservice.feign;

import com.example.appointmentservice.dto.DoctorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DOCTOR-SERVICE", url = "http://localhost:8081") 
public interface DoctorClient {
    @GetMapping("/doctor/{id}")
    DoctorResponse getDoctorById(@PathVariable("id") Long id);
}