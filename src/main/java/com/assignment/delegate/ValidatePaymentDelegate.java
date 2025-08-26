package com.assignment.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("validatePaymentDelegate")
public class ValidatePaymentDelegate implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        String studentId = (String) execution.getVariable("studentId");
        Double amount = Double.parseDouble((String)execution.getVariable("amount"));
        // Simulate validation logic
        execution.setVariable("isValid", true);
    }
}
