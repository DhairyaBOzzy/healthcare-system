package com.example.patientservice;

import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.exporter.SpanReporter;
import io.micrometer.tracing.exporter.zipkin.ZipkinSpanExporter;
import io.zipkin.reporter2.Sender;
import io.zipkin.reporter2.okhttp3.OkHttpSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient  // This microservice registers with Eureka
public class PatientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientServiceApplication.class, args);
    }
}

@Configuration
class TracingConfig {

    @Bean
    public Sender zipkinSender() {
        return OkHttpSender.create("http://zipkin:9411/api/v2/spans");
    }

    @Bean
    public SpanReporter spanReporter(Sender sender) {
        return new ZipkinSpanExporter(sender);
    }
}
