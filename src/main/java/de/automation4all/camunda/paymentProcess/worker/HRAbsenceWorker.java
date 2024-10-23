package de.automation4all.camunda.paymentProcess.worker;

import de.automation4all.camunda.paymentProcess.dto.AbsenceRequest;
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
public class HRAbsenceWorker {
    private static final Logger log = LoggerFactory.getLogger(HRAbsenceWorker.class);
    private final ZeebeClient zeebeClient;

    @JobWorker(type = "logAbsence" )
    public void chargeCreditCard(@VariablesAsType AbsenceRequest absenceRequest) {
        log.info(absenceRequest.toString());
        log.info("Entered absence into HR System.");
    }
}
