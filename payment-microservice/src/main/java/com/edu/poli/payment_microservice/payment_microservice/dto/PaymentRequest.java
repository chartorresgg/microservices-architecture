package com.edu.poli.payment_microservice.payment_microservice.dto;

/**
 * PaymentRequest class representing a payment request.
 * This class is used to encapsulate the payment details required for processing a payment.
 */
public class PaymentRequest {

    private Long bookingId; // ID of the booking associated with the payment
    private Long amount; // Amount to be paid
    private String currency; // Currency of the payment
    private String paymentMethodId; // ID of the payment method to be used

    /**
     * Default constructor for PaymentRequest.
     * This constructor is used to create an empty PaymentRequest object.
     */
    public PaymentRequest() {
    }

    /**
     * Constructor to create a PaymentRequest object with the specified parameters.
     *
     * @param bookingId       ID of the booking associated with the payment
     * @param amount          Amount to be paid
     * @param currency        Currency of the payment
     * @param paymentMethodId ID of the payment method to be used
     */
    public PaymentRequest(Long bookingId, Long amount, String currency, String paymentMethodId) {
        this.bookingId = bookingId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethodId = paymentMethodId;
    }


    // ==================== Getters and Setters ====================

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

}