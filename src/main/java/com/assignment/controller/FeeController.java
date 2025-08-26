package com.assignment.controller;

import com.assignment.dto.FeeRequest;
import com.assignment.model.FeeReceipt;
import com.assignment.repository.FeeReceiptRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
public class FeeController {
    private final RuntimeService runtimeService;
    private final FeeReceiptRepository repository;

    @PostMapping("/collect")
    @Operation(
            summary = "Initiate fee collection workflow",
            description = "Starts the Camunda BPMN process for collecting school fees"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fee collection initiated"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    public ResponseEntity<String> collectFee(@RequestBody FeeRequest request) {
        Map<String, Object> variables = Map.of(
                "providerOrderId", request.getProviderOrderId(),
                "studentId", request.getStudentId(),
                "amount", request.getAmount().toString()
        );
        runtimeService.startProcessInstanceByKey("Process_1vn6fs5", variables);
        return ResponseEntity.ok("Fee collection initiated");
    }

    @GetMapping("/receipt")
    @Operation(
            summary = "Fetch fee receipt by provider order ID",
            description = "Retrieves the stored fee receipt details from MongoDB"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receipt found"),
            @ApiResponse(responseCode = "404", description = "Receipt not found")
    })
    public ResponseEntity<FeeReceipt> getReceipt(@Parameter(description = "Provider order ID", required = true)
                                                     @RequestParam("providerOrderId") String orderId) {
        return repository.findByProviderOrderId(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}