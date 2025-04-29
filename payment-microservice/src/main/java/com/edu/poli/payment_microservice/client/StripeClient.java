package com.edu.poli.payment_microservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stripeClient", url = "https://api.stripe.com/v1")
public interface StripeClient {

    // Ejemplo de método para crear un pago en Stripe (puedes adaptarlo según tu
    // necesidad)
    @PostMapping("/charges")
    public String createCharge(@RequestBody String chargeDetails);
}