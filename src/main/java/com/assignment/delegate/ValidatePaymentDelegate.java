package com.assignment.delegate;

import com.assignment.dto.CatalogItem;
import com.assignment.dto.Receipt;
import com.assignment.dto.StudentDetails;
import com.assignment.repository.ReceiptRepository;
import com.assignment.service.CatalogClient;
import com.assignment.service.PaymentClient;
import com.assignment.service.StudentClient;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("validatePaymentDelegate")
@AllArgsConstructor
public class ValidatePaymentDelegate implements JavaDelegate {
    private final PaymentClient paymentClient;
    private final StudentClient studentClient;
    private final CatalogClient catalogClient;
    private final ReceiptRepository receiptRepository;

    @Override
    public void execute(DelegateExecution execution) {
        String orderId = (String) execution.getVariable("orderId");
        Double amount = (Double) execution.getVariable("amount");
        String studentId = (String) execution.getVariable("studentId");

        boolean isValid = Boolean.TRUE.equals(paymentClient.validatePayment(orderId, amount,studentId).block());
        StudentDetails student = studentClient.getStudentDetails(studentId).block();
        List<CatalogItem> catalog = catalogClient.getFeeCatalog(student.getGrade()).block();

        assert catalog != null;
        double total = catalog.stream()
                .mapToDouble(CatalogItem::getAmount)
                .sum();
        Receipt receipt = new Receipt(orderId, student.getName(), catalog, total);
        receiptRepository.save(receipt);

        execution.setVariable("isValid", isValid);
    }
}
