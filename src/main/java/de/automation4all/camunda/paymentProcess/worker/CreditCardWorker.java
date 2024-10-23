package de.automation4all.camunda.paymentProcess.worker;

import de.automation4all.camunda.paymentProcess.dto.PaymentRequest;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreditCardWorker {
    private static final Logger log = LoggerFactory.getLogger(CreditCardWorker.class);
    private final ZeebeClient zeebeClient;

    @JobWorker(type = "chargeCreditCard" )
    public Map<String, Object> chargeCreditCard(@VariablesAsType PaymentRequest paymentRequest) {
        log.info("Charging credit card: {}", paymentRequest.getCardNumber());
        final Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("confirmation", String.valueOf(System.currentTimeMillis()));
        return variables;
    }

    public void requestCreditCardPayment(){
        final Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("reference", "C8_12345");
        variables.put("amount", Double.valueOf(100.00));
        variables.put("cardNumber", "1234567812345678");
        variables.put("cardExpiry", "12/2023");
        variables.put("cardCVC", "123");

        zeebeClient.newCreateInstanceCommand()
            .bpmnProcessId("Process_Payment")
            .latestVersion()
            .variables(variables)
            .send()
            .join();
    }
}
