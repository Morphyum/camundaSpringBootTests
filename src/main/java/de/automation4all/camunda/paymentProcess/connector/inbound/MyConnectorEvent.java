package de.automation4all.camunda.paymentProcess.connector.inbound;


import de.automation4all.camunda.paymentProcess.connector.inbound.subscription.WatchServiceSubscriptionEvent;

/**
 * Data model of an event produced by the inbound Connector
 *
 * @param event
 */
public record MyConnectorEvent(WatchServiceSubscriptionEvent event) {}