package de.automation4all.camunda.paymentProcess.connector.outbound;

import de.automation4all.camunda.paymentProcess.dto.ConcatenationConnectorRequest;
import de.automation4all.camunda.paymentProcess.dto.ConcatenationConnectorResult;
import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OutboundConnector(
    name = "concatenation-connector",
    inputVariables = {"input1", "input2"},
    type = "io.camunda:concatenation-api:1")
public class ConcatenationConnector implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConcatenationConnector.class);

  @Override
  public Object execute(OutboundConnectorContext context) {
    final var connectorRequest = context.bindVariables(ConcatenationConnectorRequest.class);
    return executeConnector(connectorRequest);
  }

  private ConcatenationConnectorResult executeConnector(final ConcatenationConnectorRequest connectorRequest) {
    LOGGER.info("Executing my connector with request {}", connectorRequest);
    String concatenationResult = connectorRequest.input1() + " " +connectorRequest.input2();
    var result = new ConcatenationConnectorResult(concatenationResult);
    LOGGER.info("Connector executed with result {}:", result);
    return result;
  }
}
