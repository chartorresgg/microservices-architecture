package com.edu.poli.booking_microservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.poli.booking_microservice.clients.ProductClient;
import com.edu.poli.booking_microservice.model.Booking;
import com.edu.poli.booking_microservice.model.ProductResponse;
import com.edu.poli.booking_microservice.repositories.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ProductClient productClient;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(String id) {
        return bookingRepository.findById(id).orElse(null);
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

}
