package com.assignment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request payload for fee collection")
public class FeeRequest {
    @Schema(description = "Unique order ID from payment provider", example = "ORD123456")
    private String providerOrderId;

    @Schema(description = "Student ID", example = "STU98765")
    private String studentId;

    @Schema(description = "Fee amount", example = "1500.00")
    private Double amount;
}
