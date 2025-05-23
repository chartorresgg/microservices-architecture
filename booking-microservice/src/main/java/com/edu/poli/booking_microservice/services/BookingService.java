package com.edu.poli.booking_microservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.poli.booking_microservice.clients.ProductClient;
import com.edu.poli.booking_microservice.model.Booking;
import com.edu.poli.booking_microservice.model.ProductResponse;
import com.edu.poli.booking_microservice.repositories.BookingRepository;

/**
 * BookingService class responsible
 * for handling booking-related operations.
 * It interacts with the BookingRepository to perform CRUD operations and with the ProductClient
 * to retrieve product information.
 */
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository; // Repository for booking data

    @Autowired
    private ProductClient productClient; // Client for product service

    /**
     * Method to retrieve all bookings from the database.
     * @return List of all bookings
     */
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Method to retrieve a booking by its ID.
     * @param id the ID of the booking to be retrieved
     * @return the booking with the specified ID, or null if not found
     */
    public Booking getBookingById(String id) {
        return bookingRepository.findById(id).orElse(null);
    }

    /**
     * Method to retrieve a booking by its user ID.
     * @param userId the user ID of the booking to be retrieved
     * @return the booking with the specified user ID, or null if not found
     */
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

    /**
     * Method to delete a booking by its ID.
     * @param id the ID of the booking to be deleted
     * @return the deleted booking, or null if not found
     */
    public Booking deleteBooking(String id) {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking != null) {
            bookingRepository.delete(existingBooking);
            return existingBooking;
        }

        return null;
    }

    /**
     * Method to create a new booking.
     * @param booking the booking to be created
     * @param productId the ID of the product associated with the booking
     * @return the created booking
     */
    public Booking createBooking(Booking booking, String productId) {
        // Consultar el producto en Product-Microservice
        ProductResponse product = productClient.getProductById(productId);

        if (product == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        // Llenar los datos del booking con la información del producto
        booking.setProductId(productId); // Asociar el productId a la reserva
        booking.setPrice(product.getUnitPrice()); // Establecer el precio del producto

        // Si tienes otros campos adicionales que deseas establecer en Booking, como
        // quantity o status, puedes hacerlo aquí.
        booking.setQuantity(1); // Si no se envía cantidad, establecer un valor predeterminado
        booking.setStatus("Pending"); // Establecer el estado predeterminado

        // Guardar el booking en MySQL
        return bookingRepository.save(booking);
    }

    /**
     * Method to update the status of a booking.
     * @param id the ID of the booking to be updated
     * @param newStatus the new status to be set
     * @return true if the status was updated successfully, false otherwise
     */
    public boolean updateBookingStatus(String id, String newStatus) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setStatus(newStatus);
            bookingRepository.save(booking);
            return true;
        }
        return false;
    }

}