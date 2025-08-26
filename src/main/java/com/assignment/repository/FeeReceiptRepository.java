package com.assignment.repository;

import com.assignment.model.FeeReceipt;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeeReceiptRepository extends MongoRepository<FeeReceipt, String> {
    Optional<FeeReceipt> findByProviderOrderId(String providerOrderId);
}
