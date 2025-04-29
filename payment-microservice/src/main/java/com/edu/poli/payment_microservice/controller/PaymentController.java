package com.edu.poli.payment_microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.edu.poli.payment_microservice.client.StripeClient;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private StripeClient stripeClient;

    // Endpoint para procesar un pago
    @PostMapping("/process")
    public String processPayment(@RequestBody String chargeDetails) {
        // Aquí llamarás al Feign Client para procesar el pago en Stripe
        return stripeClient.createCharge(chargeDetails);
    }
}
