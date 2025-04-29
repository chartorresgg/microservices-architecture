package com.edu.poli.payment_microservice.Repository;

import com.edu.poli.payment_microservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    // Puedes agregar métodos personalizados si los necesitas después
}
