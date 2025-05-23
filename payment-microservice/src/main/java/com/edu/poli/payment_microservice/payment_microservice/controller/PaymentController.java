package com.edu.poli.payment_microservice.payment_microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.edu.poli.payment_microservice.payment_microservice.dto.PaymentRequest;
import com.edu.poli.payment_microservice.payment_microservice.model.Payment;
import com.edu.poli.payment_microservice.payment_microservice.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

/**
 * PaymentController handles payment-related requests.
 * It provides endpoints for creating, retrieving, and deleting payments.
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    /**
     * PaymentService instance for handling payment operations.
     */
    @Autowired
    private PaymentService paymentService;

    /**
     * Retrieves all payments.
     */
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    /**
     * Retrieves a payment by its ID.
     *
     * @param id the ID of the payment to retrieve
     * @return a ResponseEntity containing the payment if found, or a 404 Not Found status if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new payment.
     *
     * @param request the payment request containing payment details
     * @return a ResponseEntity containing the created payment or an error message
     */
    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequest request) {
        try {
            PaymentIntent paymentIntent = paymentService.processPayment(request);
            return ResponseEntity.ok(paymentIntent.toJson());
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Deletes a payment by its ID.
     *
     * @param id the ID of the payment to delete
     * @return a ResponseEntity with no content if successful, or a 404 Not Found status if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}