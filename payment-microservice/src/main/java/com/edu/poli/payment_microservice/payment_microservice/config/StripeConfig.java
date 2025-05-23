package com.edu.poli.payment_microservice.payment_microservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import com.stripe.Stripe;

/**
 * Configuration class for Stripe API integration.
 * This class initializes the Stripe API key from application properties.
 */
@Configuration
public class StripeConfig {

    // Stripe API secret key
    // This key is used to authenticate requests to the Stripe API.
    @Value("${stripe.api.secret.key}")
    private String secretKey;

    /**
     * Initializes the Stripe API key after the application context is loaded.
     * This method is called automatically by Spring after the bean is created.
     */
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
}
