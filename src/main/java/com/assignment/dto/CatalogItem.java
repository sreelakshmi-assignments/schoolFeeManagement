package com.assignment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogItem {
    @Schema(description = "item", example = "Tution")
    private String item;
    @Schema(description = "amount", example = "123.00")
    private double amount;
}

