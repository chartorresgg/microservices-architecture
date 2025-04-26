package com.edu.poli.booking_microservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.poli.booking_microservice.model.Booking;
import com.edu.poli.booking_microservice.repositories.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(String id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Booking booking) {
        Booking existingBooking = bookingRepository.findById(booking.getId()).orElse(null);
        if (existingBooking != null) {
            existingBooking.setUserId(booking.getUserId());
            existingBooking.setProductId(booking.getProductId());
            existingBooking.setQuantity(booking.getQuantity());
            existingBooking.setPrice(booking.getPrice());
            existingBooking.setBookingDate(booking.getBookingDate());
            existingBooking.setStatus(booking.getStatus());

            return bookingRepository.save(existingBooking);
        }

        return null;
    }

    public Booking deleteBooking(String id) {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking != null) {
            bookingRepository.delete(existingBooking);
            return existingBooking;
        }

        return null;
    }

}
