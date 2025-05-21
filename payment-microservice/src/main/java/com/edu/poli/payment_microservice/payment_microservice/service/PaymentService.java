package com.edu.poli.payment_microservice.payment_microservice.service;

import com.edu.poli.payment_microservice.payment_microservice.dto.PaymentRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    public PaymentIntent processPayment(PaymentRequest request) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", request.getAmount());
        params.put("currency", request.getCurrency());
        params.put("payment_method", request.getPaymentMethodId());
        params.put("confirm", true);

        Map<String, Object> automaticPaymentMethods = new HashMap<>();
        automaticPaymentMethods.put("enabled", true);
        automaticPaymentMethods.put("allow_redirects", "never"); // 👈 solución clave

        params.put("automatic_payment_methods", automaticPaymentMethods);

        return PaymentIntent.create(params);
    }
}