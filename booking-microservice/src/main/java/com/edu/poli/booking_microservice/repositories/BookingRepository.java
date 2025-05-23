package com.edu.poli.booking_microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edu.poli.booking_microservice.model.Booking;

/**
 * BookingRepository interface for managing Booking entities in the database.
 * It extends JpaRepository to provide CRUD operations and custom query methods.
 */
public interface BookingRepository extends JpaRepository<Booking, String> {
}