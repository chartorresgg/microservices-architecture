package com.edu.poli.booking_microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.edu.poli.booking_microservice.model.Booking;
import com.edu.poli.booking_microservice.services.BookingService;
import java.util.List;
import java.util.Map;

/**
 * BookingController class responsible
 * for handling HTTP requests related to bookings.
 * It provides endpoints for CRUD operations.
 */
@RestController // Indicates that this class is a REST controller
@RequestMapping("/api/bookings") // Base URL for booking-related endpoints
public class BookingController {

    @Autowired
    private BookingService bookingService; // Service for booking operations

    /**
     * Method to retrieve all bookings.
     * @return List of all bookings
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    /**
     * Method to retrieve a booking by its ID.
     * @param id the ID of the booking to be retrieved
     * @return the booking with the specified ID, or null if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable String id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Method to update a booking.
     * @param id the ID of the booking to be updated
     * @param booking the updated booking object
     * @return the updated booking, or null if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable String id, @RequestBody Booking booking) {
        booking.setId(id);
        Booking updatedBooking = bookingService.updateBooking(booking);
        if (updatedBooking != null) {
            return ResponseEntity.ok(updatedBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Method to update the status of a booking.
     * @param id the ID of the booking to be updated
     * @param payload the new status to be set for the booking
     * @return a response indicating success or failure
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateBookingStatus(@PathVariable String id, @RequestBody Map<String, String> payload) {
        String newStatus = payload.get("status");
        boolean updated = bookingService.updateBookingStatus(id, newStatus);
        if (updated) {
            return ResponseEntity.ok("Estado actualizado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada");
        }
    }

    /**
     * Method to delete a booking by its ID.
     * @param id the ID of the booking to be deleted
     * @return the deleted booking, or null if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable String id) {
        Booking deletedBooking = bookingService.deleteBooking(id);
        if (deletedBooking != null) {
            return ResponseEntity.ok(deletedBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Method to create a new booking.
     * @param booking the booking to be created
     * @param productId the ID of the product associated with the booking
     * @return the created booking
     */
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking, @RequestParam String productId) {
        return bookingService.createBooking(booking, productId);
    }

}