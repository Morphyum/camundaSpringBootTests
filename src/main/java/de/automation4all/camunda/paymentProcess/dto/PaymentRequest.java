package de.automation4all.camunda.paymentProcess.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String reference;
    private double amount;
    private String cardNumber;
    private String cardExpiry;
    private String cardCVC;
}
/*

{
"paymentRequest":{
    "reference": "C8_12345",
    "amount": 100.00,
    "cardNumber": "1234567812345678",
    "cardExpiry": "12/2023",
    "cardCVC": "123"
  }
}


 */
