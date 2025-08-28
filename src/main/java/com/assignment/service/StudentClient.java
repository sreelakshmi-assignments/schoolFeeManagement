package com.assignment.service;

import com.assignment.dto.StudentDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentClient {

    private final WebClient.Builder webClientBuilder;

    public Mono<StudentDetails> getStudentDetails(String studentId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/students/{id}", studentId)
                .retrieve()
                .bodyToMono(StudentDetails.class);
    }
}

