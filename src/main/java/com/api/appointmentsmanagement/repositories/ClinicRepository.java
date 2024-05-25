package com.api.appointmentsmanagement.repositories;

import com.api.appointmentsmanagement.models.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
