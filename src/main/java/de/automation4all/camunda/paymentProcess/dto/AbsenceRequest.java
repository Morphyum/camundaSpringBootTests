package de.automation4all.camunda.paymentProcess.dto;

import lombok.Data;

@Data
public class AbsenceRequest {
    private String absenceEndDate;
    private String absenceStartDate;
    private String employeeName;
    private String policy;

    @Override
    public String toString() {
        return employeeName + " is away from " + absenceStartDate + " till " + absenceEndDate + " because of " + policy;
    }
}
