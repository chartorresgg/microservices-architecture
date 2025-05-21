package com.edu.poli.payment_microservice.payment_microservice.service;

import com.edu.poli.payment_microservice.payment_microservice.client.BookingClient;
import com.edu.poli.payment_microservice.payment_microservice.dto.PaymentRequest;
import com.edu.poli.payment_microservice.payment_microservice.model.Payment;
import com.edu.poli.payment_microservice.payment_microservice.repository.PaymentRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingClient bookingClient;

    public PaymentService(PaymentRepository paymentRepository, BookingClient bookingClient) {
        this.paymentRepository = paymentRepository;
        this.bookingClient = bookingClient;
    }

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

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

}