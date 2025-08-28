package com.assignment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Receipt {
    @Schema(description = "providerOrderId", example = "ORD123")
    private String providerOrderId;
    @Schema(description = "studentName", example = "Ravi")
    private String studentName;
    @Schema(description = "items", example = "Tution")
    private List<CatalogItem> items;
    @Schema(description = "total", example = "1200.00")
    private double total;
}

