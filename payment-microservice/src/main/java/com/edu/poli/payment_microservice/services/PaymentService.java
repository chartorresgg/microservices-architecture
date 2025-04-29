package com.edu.poli.payment_microservice.services;

import org.springframework.stereotype.Service;

import com.edu.poli.payment_microservice.Repository.PaymentRepository;
import com.edu.poli.payment_microservice.model.Payment;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment processPayment(Payment payment) {
        payment.setId(UUID.randomUUID().toString());
        payment.setStatus("PROCESSED");
        payment.setPaymentDate(new Date());
        return repository.save(payment);
    }

    public Optional<Payment> getPaymentById(String id) {
        return repository.findById(id);
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    public void deletePayment(String id) {
        repository.deleteById(id);
    }
}