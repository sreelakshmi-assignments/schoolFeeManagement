package com.assignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentClient {

    private final WebClient.Builder webClientBuilder;

    public Mono<Boolean> validatePayment(String orderId, Double amount,String studentId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("orderId", orderId);
        payload.put("amount", amount);
        payload.put("studentId",studentId);


        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/payments/validate")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    public Mono<String> getPaymentStatus(String orderId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/payments/status/{id}", orderId)
                .retrieve()
                .bodyToMono(String.class);
    }
}
