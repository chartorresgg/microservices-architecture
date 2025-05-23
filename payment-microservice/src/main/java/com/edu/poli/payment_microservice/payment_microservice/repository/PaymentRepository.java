package com.edu.poli.payment_microservice.payment_microservice.repository;

import com.edu.poli.payment_microservice.payment_microservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PaymentRepository interface for managing Payment entities in the database.
 * It extends JpaRepository to provide CRUD operations and custom query methods.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}