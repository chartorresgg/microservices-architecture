package com.edu.poli.payment_microservice.payment_microservice.dto;

public class PaymentRequest {
    private Long bookingId;
    private Long amount;
    private String currency;
    private String paymentMethodId;

    public PaymentRequest() {
    }

    public PaymentRequest(Long bookingId, Long amount, String currency, String paymentMethodId) {
        this.bookingId = bookingId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethodId = paymentMethodId;
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

}