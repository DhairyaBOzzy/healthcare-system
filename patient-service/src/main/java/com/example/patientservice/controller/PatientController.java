package com.example.patientservice.controller;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final Tracer tracer;
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    public PatientController(Tracer tracer) {
        this.tracer = tracer;
    }

    @GetMapping("/trace-test")
    public String traceTest() {
        Span span = tracer.spanBuilder("manualTraceTest").startSpan();
        try {
            logger.info("Manually creating trace span: manualTraceTest");
            return "OpenTelemetry Trace Test!";
        } finally {
            span.end();
        }
    }
}
