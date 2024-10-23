package de.automation4all.camunda.paymentProcess.connector.inbound;

public record MyConnectorProperties(
    String eventToMonitor,
    String directory,
    String pollingInterval
) {}
