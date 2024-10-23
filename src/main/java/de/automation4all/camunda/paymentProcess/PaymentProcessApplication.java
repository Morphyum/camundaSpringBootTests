package de.automation4all.camunda.paymentProcess;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
public class PaymentProcessApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentProcessApplication.class, args);

	}
}
