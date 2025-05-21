package com.edu.poli.payment_microservice.payment_microservice.repository;

import com.edu.poli.payment_microservice.payment_microservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
