package com.paybizz.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PaymentTransferApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentTransferApplication.class, args);
    }
}