package com.assignment.delegate;

import com.assignment.model.FeeReceipt;
import com.assignment.repository.FeeReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("persistReceiptDelegate")
@RequiredArgsConstructor
public class PersistReceiptDelegate implements JavaDelegate {
    private final FeeReceiptRepository repository;

    public void execute(DelegateExecution execution) {
        FeeReceipt receipt = new FeeReceipt();
        receipt.setProviderOrderId((String) execution.getVariable("providerOrderId"));
        receipt.setStudentId((String) execution.getVariable("studentId"));
        receipt.setAmount(Double.parseDouble((String)execution.getVariable("amount")));
        receipt.setPaymentDate(LocalDateTime.now());
        receipt.setStatus("PAID");
        repository.save(receipt);
    }
}
