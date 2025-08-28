package com.assignment.repository;

import com.assignment.dto.Receipt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiptRepository extends MongoRepository<Receipt, String> {

    Optional<Receipt> findByProviderOrderId(String providerOrderId);

    List<Receipt> findByStudentName(String studentName); // Optional for batch queries
}

