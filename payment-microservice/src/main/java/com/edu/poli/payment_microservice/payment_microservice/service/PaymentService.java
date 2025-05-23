package com.edu.poli.payment_microservice.payment_microservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edu.poli.payment_microservice.payment_microservice.client.BookingClient;
import com.edu.poli.payment_microservice.payment_microservice.dto.PaymentRequest;
import com.edu.poli.payment_microservice.payment_microservice.model.Payment;
import com.edu.poli.payment_microservice.payment_microservice.repository.PaymentRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

/**
 * PaymentService class responsible for handling payment-related operations.
 * It interacts with the PaymentRepository to perform CRUD operations
 * and with the BookingClient
 * to update booking status after payment processing.
 */
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository; // Repository for payment data
    private final BookingClient bookingClient; // Client for booking service

    /**
     * Constructor for PaymentService.
     *
     * @param paymentRepository Repository for payment data
     * @param bookingClient     Client for booking service
     */
    public PaymentService(PaymentRepository paymentRepository, BookingClient bookingClient) {
        this.paymentRepository = paymentRepository;
        this.bookingClient = bookingClient;
    }


    /**
     * Processes a payment using Stripe's PaymentIntent API.
     *
     * @param request PaymentRequest object containing payment details
     * @return PaymentIntent object representing the created payment intent
     * @throws StripeException if there is an error processing the payment
     */
    public PaymentIntent processPayment(PaymentRequest request) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", request.getAmount());
        params.put("currency", request.getCurrency());
        params.put("payment_method", request.getPaymentMethodId());
        params.put("confirm", true);

        Map<String, Object> automaticPaymentMethods = new HashMap<>();
        automaticPaymentMethods.put("enabled", true);
        automaticPaymentMethods.put("allow_redirects", "never");
        params.put("automatic_payment_methods", automaticPaymentMethods);

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        if ("succeeded".equals(paymentIntent.getStatus())) {
            // Guardar en BD
            Payment payment = new Payment(
                    request.getBookingId(),
                    request.getAmount(),
                    request.getCurrency(),
                    request.getPaymentMethodId(),
                    paymentIntent.getStatus());
            paymentRepository.save(payment);

            // Llamar a Booking-Microservice para actualizar estado
            bookingClient.updateBookingStatus(String.valueOf(request.getBookingId()), "pagado");
        }

        return paymentIntent;
    }

    // ============================ CRUD Operations ============================
    
    /**
     * Creates a new payment record in the database.
     *
     * @param payment Payment object to be saved
     * @return The saved Payment object
     */
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    /**
     * Retrieves a payment record by its ID.
     *
     * @param id ID of the payment to be retrieved
     * @return Optional containing the Payment object if found, otherwise empty
     */
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    /**
     * Updates an existing payment record in the database.
     *
     * @param id      ID of the payment to be updated
     * @param payment Payment object with updated details
     * @return The updated Payment object
     */
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    /**
     * Deletes a payment record by its ID.
     *
     * @param id ID of the payment to be deleted
     */
    public void deletePaymentById(Long id) {
        paymentRepository.deleteById(id);

}

}