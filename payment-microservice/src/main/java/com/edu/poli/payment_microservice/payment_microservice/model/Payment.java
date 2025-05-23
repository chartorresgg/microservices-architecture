package com.edu.poli.payment_microservice.payment_microservice.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Payment entity class representing a payment record in the database.
 * This class is annotated with JPA annotations to map it to the "payment"
 * table.
 */
@Entity // Indicates that this class is a JPA entity
@Table(name = "payment") // Specifies the name of the table in the database
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id; // Unique identifier for the payment
    private Long bookingId; // ID of the booking associated with the payment
    private Long amount; // Amount to be paid
    private String currency; // Currency of the payment
    private String paymentMethodId; // ID of the payment method to be used
    private String status; // Status of the payment (e.g., "succeeded", "failed")
    private LocalDateTime createdAt; // Timestamp of when the payment was created

    // Default constructor
    public Payment() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Constructor to create a Payment object with the specified parameters.
     *
     * @param bookingId       ID of the booking associated with the payment
     * @param amount          Amount to be paid
     * @param currency        Currency of the payment
     * @param paymentMethodId ID of the payment method to be used
     * @param status          Status of the payment
     */
    public Payment(Long bookingId, Long amount, String currency, String paymentMethodId, String status) {
        this.bookingId = bookingId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethodId = paymentMethodId;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    // ============================ Getters and Setters ============================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
