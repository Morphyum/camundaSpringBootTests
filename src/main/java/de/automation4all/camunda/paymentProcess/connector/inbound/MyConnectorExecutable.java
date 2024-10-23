package de.automation4all.camunda.paymentProcess.connector.inbound;

import de.automation4all.camunda.paymentProcess.connector.inbound.subscription.WatchServiceSubscription;
import de.automation4all.camunda.paymentProcess.connector.inbound.subscription.WatchServiceSubscriptionEvent;
import io.camunda.connector.api.annotation.InboundConnector;
import io.camunda.connector.api.inbound.InboundConnectorContext;
import io.camunda.connector.api.inbound.InboundConnectorExecutable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.stereotype.Component;

@InboundConnector(
    name = "Watch Service Inbound Connector",
    type = "io.camunda:watchserviceinbound:1"
)
public class MyConnectorExecutable implements InboundConnectorExecutable<InboundConnectorContext> {

  private WatchServiceSubscription subscription;
  private InboundConnectorContext context;
  private ExecutorService executorService;
  public CompletableFuture<?> future;

  @Override
  public void activate(InboundConnectorContext context) {
    MyConnectorProperties props = context.bindProperties(MyConnectorProperties.class);
    this.context = context;
    this.executorService = Executors.newSingleThreadExecutor();

    this.future =
        CompletableFuture.runAsync(
            () -> {
              new WatchServiceSubscription(
                  props.eventToMonitor(),
                  props.directory(),
                  props.pollingInterval(),
                  this::onEvent);
            },
            this.executorService);
  }

  private void onEvent(WatchServiceSubscriptionEvent rawEvent) {
    MyConnectorEvent connectorEvent = new MyConnectorEvent(rawEvent);
    context.correlateWithResult(connectorEvent);
  }

  @Override
  public void deactivate() {
    subscription.stop();
  }
}
