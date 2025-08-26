package com.assignment.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("notifyInternalServicesDelegate")
public class NotifyInternalServicesDelegate implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        // Simulate notification logic
        System.out.println("Notifying accounting and student portal...");
    }
}
