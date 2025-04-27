package com.edu.poli.booking_microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.edu.poli.booking_microservice.model.Booking;
import com.edu.poli.booking_microservice.services.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable String id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable String id) {
        Booking deletedBooking = bookingService.deleteBooking(id);
        if (deletedBooking != null) {
            return ResponseEntity.ok(deletedBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking, @RequestParam String productId) {
        return bookingService.createBooking(booking, productId);
    }

}