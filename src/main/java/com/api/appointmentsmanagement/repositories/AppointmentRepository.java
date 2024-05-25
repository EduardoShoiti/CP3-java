package com.api.appointmentsmanagement.repositories;

import com.api.appointmentsmanagement.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
