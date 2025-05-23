package com.edu.poli.booking_microservice.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Booking entity class representing a booking record in the database.
 * This class is annotated with JPA annotations to map it to the "bookings"
 * table.
 */
@Entity // Indicates that this class is a JPA entity
@Table(name = "bookings") // Specifies the name of the table in the database
public class Booking {

    @Id
    private String id; // Unique identifier for the booking
    private String userId; // ID of the user who made the booking
    private String productId; // ID of the product being booked
    private Integer quantity; // Quantity of the product being booked
    private Double price; // Total price of the booking
    private Date bookingDate; // Date when the booking was made
    private String status; // Status of the booking (e.g., "confirmed", "cancelled")

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}