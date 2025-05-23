package com.edu.poli.payment_microservice.payment_microservice.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

/**
 * BookingClient is a service that interacts with the Booking microservice.
 * It provides methods to update the status of bookings.
 */
@Service
public class BookingClient {

    /**
     * RestTemplate instance for making HTTP requests.
     */
    private final RestTemplate restTemplate;

    /**
     * Constructor for BookingClient.
     * @param restTemplate the RestTemplate instance to be used for making HTTP requests
     */
    public BookingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Updates the status of a booking in the Booking microservice.
     * @param bookingId the ID of the booking to be updated
     * @param status the new status to be set for the booking
     */
    public void updateBookingStatus(String bookingId, String status) {
        String url = "http://localhost:8082/api/bookings/" + bookingId + "/status";

        // Crear body
        Map<String, String> body = new HashMap<>();
        body.put("status", status);

        // Crear headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crear request
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

        // Ejecutar PUT
        try {
            restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        } catch (Exception e) {
            System.err.println("Error al actualizar estado en Booking: " + e.getMessage());
        }
    }
}