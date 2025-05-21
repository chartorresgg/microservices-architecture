package com.edu.poli.payment_microservice.payment_microservice.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

@Service
public class BookingClient {

    private final RestTemplate restTemplate;

    public BookingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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
