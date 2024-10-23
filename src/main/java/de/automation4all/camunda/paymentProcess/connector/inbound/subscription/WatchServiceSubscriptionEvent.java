package de.automation4all.camunda.paymentProcess.connector.inbound.subscription;

public record WatchServiceSubscriptionEvent(
    String monitoredEvent,
    String directory,
    String fileName
){}
