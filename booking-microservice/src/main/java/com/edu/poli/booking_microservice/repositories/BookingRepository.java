package com.edu.poli.booking_microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.poli.booking_microservice.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, String> {

}