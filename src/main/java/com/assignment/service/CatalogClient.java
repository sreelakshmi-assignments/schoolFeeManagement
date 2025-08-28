package com.assignment.service;

import com.assignment.dto.CatalogItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogClient {

    private final WebClient.Builder webClientBuilder;

    public Mono<List<CatalogItem>> getFeeCatalog(String studentId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8084/catalogs/{studentId}", studentId)
                .retrieve()
                .bodyToFlux(CatalogItem.class)
                .collectList();
    }
}
