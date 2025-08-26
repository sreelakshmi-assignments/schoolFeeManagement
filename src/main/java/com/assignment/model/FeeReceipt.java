package com.assignment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "fee_receipts")
@Data
public class FeeReceipt {
    @Id
    private String id;
    private String providerOrderId;
    private String studentId;
    private Double amount;
    private LocalDateTime paymentDate;
    private String status; // e.g., PAID, FAILED
}
